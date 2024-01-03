package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Repository.ClenRepository;
import com.example.ppro_project.Service.ClenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.VlastnostController.*;
import static com.example.ppro_project.Controller.VlastnostController.vlastnostService;

@Controller
public class ClenController {

    public static ClenService clenService;
    public static Clen prihlasenyUzivatel;

    public static List<Clen> rozhodciList;
    public static List<Clen> delegatiList;

    @Value("Delegát, Rozhodčí")
    private List<String> roles;

    public static boolean jePrihlasenUzivatel(){
        return prihlasenyUzivatel != null;
    }
    @Autowired
    public ClenController(ClenService clenService) {
        this.clenService = clenService;
    }

    //  @ResponseBody
    @GetMapping("/")
    public String index(Model model){
        if(jePrihlasenUzivatel()){
            return "redirect:menu";
        }
        if (!model.containsAttribute("clen")) {
            model.addAttribute("clen", new Clen());
        }
        model.addAttribute("roles", roles);
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model){
        if(!jePrihlasenUzivatel()){
            return "redirect:/";
        }
        model.addAttribute("clen", prihlasenyUzivatel);
        return "menu";
    }

    @GetMapping("/profil")
    public String profil(Model model){
        if(!jePrihlasenUzivatel()){
            return "redirect:/";
        }
        model.addAttribute("clen", prihlasenyUzivatel);
        return "profil";
    }

    @GetMapping("/odhlasit")
    public String odhlasit(Model model){
        prihlasenyUzivatel = null;
        return "redirect:/";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("clen") Clen clen, BindingResult br, Model model){
        if (br.hasErrors()) {
            model.addAttribute("roles", roles);
            return "index";
        } else {
            if(clen.getIdFacr() == null  || clen.getHeslo() == null
                    || clen.getIdFacr().isEmpty() || clen.getHeslo().isEmpty() || clen.getIdFacr().length() > 10){
                br.rejectValue("idFacr", "error.user", "Chybné údaje");
                model.addAttribute("roles", roles);
                return "index";
            }
            Clen authenticatedUser =
                    clenService.getClenByIdFacrAndHeslo(clen.getIdFacr(), clen.getHeslo());
            if (authenticatedUser != null) {
                if(!clen.getRole().equals(authenticatedUser.getRole())){
                    br.rejectValue("idFacr", "error.user",
                            "Člen není klasifikován jako " + clen.getRole().toLowerCase());
                    model.addAttribute("roles", roles);
                    return "index";
                }
                prihlasenyUzivatel = authenticatedUser;
                rozhodciList = clenService.getRozhodci();
                delegatiList = clenService.getDelegati();
                vlastnostiListPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_APLIKACE_PF);
                vlastnostiListOT = vlastnostService.getVlastnostiByKategorie(KATEGORIE_OT);
                vlastnostiListFyzicka = vlastnostService.getVlastnostiByKategorie(KATEGORIE_FYZICKA);
                vlastnostiListSpoluprace = vlastnostService.getVlastnostiByKategorie(KATEGORIE_SPOLUPRACE);
                vlastnostiListKomentar = vlastnostService.getVlastnostiByKategorie(KATEGORIE_KOMENTAR);
                vlastnostiListARPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPF);
                vlastnostiListARPohyb = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPOHYB);
                return "redirect:menu";
            } else {
                br.rejectValue("idFacr", "error.user", "Chybné údaje");
                model.addAttribute("roles", roles);
                return "index";
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }

}
