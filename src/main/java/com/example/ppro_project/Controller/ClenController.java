package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.*;
import com.example.ppro_project.Service.ClenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.HodnoceniController.hodnoceniService;
import static com.example.ppro_project.Controller.VlastnostController.*;
import static com.example.ppro_project.Controller.UtkaniController.*;
import static com.example.ppro_project.Controller.VlastnostController.vlastnostService;
import static com.example.ppro_project.Controller.ZpravaController.kompletniZprava;
import static com.example.ppro_project.Controller.ZpravaController.zpravaService;
import static com.example.ppro_project.PDF.WordConvertor.printParts;

@Controller
public class ClenController implements ErrorController {

    public static ClenService clenService;
    public static Clen prihlasenyUzivatel;

    public static List<Clen> rozhodciList;
    public static List<Clen> delegatiList;

    public static boolean maRozpracovane;

    public static List<Zprava> zpravyClena;

    @Value("Delegát, Rozhodčí")
    private List<String> roles;

    public static boolean jePrihlasenUzivatel() {
        return prihlasenyUzivatel != null;
    }

    @Autowired
    public ClenController(ClenService clenService) {
        this.clenService = clenService;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        if (jePrihlasenUzivatel()) {
            return "redirect:menu";
        }
        if (!model.containsAttribute("clen")) {
            model.addAttribute("clen", new Clen());
        }
        model.addAttribute("roles", roles);
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        if (!jePrihlasenUzivatel()) {
            return "redirect:/";
        }
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("maRozpracovane", maRozpracovane);
        return "menu";
    }

    private List<Clen> getCleny(String typClena){
        if(typClena.equals(DELEGAT)){
            return clenService.getDelegati();
        } else {
            return clenService.getRozhodci();
        }
    }

    @GetMapping("/rozhodci")
    public String clenoveRozhodci(Model model) {
        if (!jePrihlasenUzivatel() || !Objects.equals(prihlasenyUzivatel.getRole(), ADMIN)) {
            return "redirect:/";
        }
        List<Clen> clenove = getCleny(ROZHODCI);
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("clenove", clenove);
        return "admin_clenove";
    }

    @GetMapping("/delegati")
    public String clenoveDelegati(Model model) {
        if (!jePrihlasenUzivatel() || !Objects.equals(prihlasenyUzivatel.getRole(), ADMIN)) {
            return "redirect:/";
        }
        List<Clen> clenove = getCleny(DELEGAT);
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("clenove", clenove);
        return "admin_clenove";
    }

    public void nactiProfil(Model model, Clen clen) {
        int pocetZprav = 0;
        if (Objects.equals(clen.getRole(), ROZHODCI)) {
            pocetZprav = zpravaService.getPocetZpravByIdR(clen.getId());
            List<Vlastnost> nejlepsiVlastnostiR =
                    vlastnostService.getNejcastejsiVlastnostiRByIdR(clen.getId(), 1);
            List<Vlastnost> nejhorsiVlastnostiR =
                    vlastnostService.getNejcastejsiVlastnostiRByIdR(clen.getId(), 0);
            List<Vlastnost> nejlepsiVlastnostiAR =
                    vlastnostService.getNejcastejsiVlastnostiARByIdR(clen.getId(), 1);
            List<Vlastnost> nejhorsiVlastnostiAR =
                    vlastnostService.getNejcastejsiVlastnostiARByIdR(clen.getId(), 0);
            Posudek nejlepsiZnamkaR = clenService.getNejlepsiZnamkaRByIdR(clen.getId());
            Posudek nejhorsiZnamkaR = clenService.getNejhorsiZnamkaRByIdR(clen.getId());
            Posudek nejlepsiZnamkaAR = clenService.getNejlepsiZnamkaARByIdR(clen.getId());
            Posudek nejhorsiZnamkaAR = clenService.getNejhorsiZnamkaARByIdR(clen.getId());
            model.addAttribute("nejlepsiZnamkaR", nejlepsiZnamkaR);
            model.addAttribute("nejhorsiZnamkaR", nejhorsiZnamkaR);
            model.addAttribute("nejlepsiZnamkaAR", nejlepsiZnamkaAR);
            model.addAttribute("nejhorsiZnamkaAR", nejhorsiZnamkaAR);
            model.addAttribute("nejlepsiVlastnostiR", nejlepsiVlastnostiR);
            model.addAttribute("nejhorsiVlastnostiR", nejhorsiVlastnostiR);
            model.addAttribute("nejlepsiVlastnostiAR", nejlepsiVlastnostiAR);
            model.addAttribute("nejhorsiVlastnostiAR", nejhorsiVlastnostiAR);
        } else if (Objects.equals(clen.getRole(), DELEGAT)) {
            pocetZprav = zpravaService.getPocetZpravByIdDFA(clen.getId());
        }
        model.addAttribute("clen", clen);
        model.addAttribute("pocetZprav", pocetZprav);
    }

    @GetMapping("/profil")
    public String profil(@RequestParam String id, Model model) {
        if (!jePrihlasenUzivatel()) {
            return "redirect:/";
        }
        Clen clen = clenService.getClenById(Integer.parseInt(id));
        if(clen == null){
            return "redirect:/";
        }
        nactiProfil(model, clen);
        int schovatDetaily = 1;
        if(prihlasenyUzivatel.getRole().equals(ADMIN)){
            schovatDetaily = 0;
        }
        model.addAttribute("schovatDetaily", schovatDetaily);
        return "profil";
    }

    @GetMapping("/muj_profil")
    public String profil(Model model) {
        if (!jePrihlasenUzivatel()) {
            return "redirect:/";
        }
        nactiProfil(model, prihlasenyUzivatel);
        model.addAttribute("schovatDetaily", 0);
        return "profil";
    }

    @GetMapping("/odhlasit")
    public String odhlasit(Model model) {
        prihlasenyUzivatel = null;
        return "redirect:/";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("clen") Clen clen, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("roles", roles);
            return "index";
        } else {
            if (clen.getIdFacr() == null || clen.getHeslo() == null
                    || clen.getIdFacr().isEmpty() || clen.getHeslo().isEmpty() || clen.getIdFacr().length() > 10) {
                br.rejectValue("idFacr", "error.user", "Chybné údaje");
                model.addAttribute("roles", roles);
                return "index";
            }
            Clen authenticatedUser =
                    clenService.getClenByIdFacrAndHeslo(clen.getIdFacr(), clen.getHeslo());
            if (authenticatedUser != null) {
                if (!clen.getRole().equals(authenticatedUser.getRole())
                        && !authenticatedUser.getRole().equals(ADMIN)) {
                    br.rejectValue("idFacr", "error.user",
                            "Člen není klasifikován jako " + clen.getRole().toLowerCase());
                    model.addAttribute("roles", roles);
                    return "index";
                }
                prihlasenyUzivatel = authenticatedUser;
                naplnAtributyClena();
                return "redirect:menu";
            } else {
                br.rejectValue("idFacr", "error.user", "Chybné údaje");
                model.addAttribute("roles", roles);
                return "index";
            }
        }
    }

    public void naplnAtributyClena() {
        rozhodciList = clenService.getRozhodci();
        delegatiList = clenService.getDelegati();
        kompletniZprava = new KompletniZprava();
        hledaneUtkani = new Utkani();
        maRozpracovane = false;
        if (Objects.equals(prihlasenyUzivatel.getRole(), DELEGAT)) {
            zpravyClena = zpravaService.getZpravyByIdDFA(prihlasenyUzivatel.getId());
        } else if (Objects.equals(prihlasenyUzivatel.getRole(), ADMIN)) {
            zpravyClena = new ArrayList<>();
            maRozpracovane = false;
        } else {
            zpravyClena = zpravaService.getZpravyByIdRozhodci(prihlasenyUzivatel.getId());
        }
        for (Zprava zprava : zpravyClena) {
            if (zprava.stav == 0) {
                maRozpracovane = true;
                break;
            }
        }
        vlastnostiListPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_APLIKACE_PF);
        vlastnostiListOT = vlastnostService.getVlastnostiByKategorie(KATEGORIE_OT);
        vlastnostiListFyzicka = vlastnostService.getVlastnostiByKategorie(KATEGORIE_FYZICKA);
        vlastnostiListSpoluprace = vlastnostService.getVlastnostiByKategorie(KATEGORIE_SPOLUPRACE);
        vlastnostiListKomentar = vlastnostService.getVlastnostiByKategorie(KATEGORIE_KOMENTAR);
        vlastnostiListARPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPF);
        vlastnostiListARPohyb = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPOHYB);
    }

    @InitBinder
    public void initBinder(WebDataBinder db) {
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }

}
