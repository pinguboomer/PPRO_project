package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.*;
import com.example.ppro_project.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.ClenController.*;
import static com.example.ppro_project.Controller.HodnoceniController.*;
import static com.example.ppro_project.Controller.SoutezController.novaSoutez;
import static com.example.ppro_project.Controller.SoutezController.soutezService;
import static com.example.ppro_project.Controller.UtkaniController.noveUtkani;
import static com.example.ppro_project.Controller.UtkaniController.utkaniService;
import static com.example.ppro_project.Controller.VlastnostController.*;

@Controller
public class ZpravaController {

    public static ZpravaService zpravaService;
    public static Zprava novaZprava;
    public static KompletniZprava kompletniZprava;

    public static Clen hlavniRozhodci;
    public static Clen ar1;
    public static Clen ar2;
    public static Clen dfa;
    public static Clen td;

    @Autowired
    public ZpravaController(ZpravaService zpravaService) {
        this.zpravaService = zpravaService;
    }


    @GetMapping("/nova_zprava")
    public String novaZprava(Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            noveUtkani = null;
            return "redirect:/";
        }

        if (noveUtkani == null) {
            vynulujParametryZpravy();
        }

        pridejAtributyDoModelu(model);

        return "nova_zprava";
    }

    private void vynulujParametryZpravy() {
        noveUtkani = new Utkani();
        novaZprava = new Zprava();
        novaSoutez = new Soutez();
        hlavniRozhodci = new Clen();
        ar1 = new Clen();
        ar2 = new Clen();
        dfa = new Clen();
        td = new Clen();
        hodnoceniR = new Hodnoceni();
        hodnoceniAR1 = new Hodnoceni();
        hodnoceniAR2 = new Hodnoceni();
        kompletniZprava = new KompletniZprava();
    }

    private void pridejAtributyDoModelu(Model model) {
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("kompletniZprava", kompletniZprava);
        model.addAttribute("utkani", noveUtkani);
        model.addAttribute("soutez", novaSoutez);
        model.addAttribute("novaZprava", novaZprava);
        model.addAttribute("r", hlavniRozhodci);
        model.addAttribute("ar1", ar1);
        model.addAttribute("ar2", ar2);
        model.addAttribute("dfa", dfa);
        model.addAttribute("td", td);
        model.addAttribute("hodnoceniR", hodnoceniR);
        model.addAttribute("hodnoceniAR1", hodnoceniAR1);
        model.addAttribute("hodnoceniAR2", hodnoceniAR2);
        model.addAttribute("rozhodciList", rozhodciList);
        model.addAttribute("delegatiList", delegatiList);
        model.addAttribute("vlastnostiListPF", vlastnostiListPF);
        model.addAttribute("vlastnostiListOT", vlastnostiListOT);
        model.addAttribute("vlastnostiListFyzicka", vlastnostiListFyzicka);
        model.addAttribute("vlastnostiListSpoluprace", vlastnostiListSpoluprace);
        model.addAttribute("vlastnostiListKomentar", vlastnostiListKomentar);
        model.addAttribute("vlastnostiListARPF", vlastnostiListARPF);
        model.addAttribute("vlastnostiListARPohyb", vlastnostiListARPohyb);
    }

    @PostMapping("/nova_zprava/vyhledejUtkani")
    public String vyhledejUtkani(@Valid @ModelAttribute("utkani") Utkani utkani,
                                 BindingResult br, Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            noveUtkani = null;
            return "redirect:/";
        }

        pridejAtributyDoModelu(model);
        if (utkani == null || utkani.idUtkani == null || utkani.idUtkani.isEmpty()) {
            br.rejectValue("idUtkani", "error.user", "Chybné údaje");
            return "nova_zprava";
        }
        Utkani utkaniNalezene = utkaniService.getUtkaniByIdUtkani(utkani.idUtkani);
        if (utkaniNalezene == null) {
            br.rejectValue("idUtkani", "error.user", "Zpráva nenalezena");
            return "nova_zprava";
        }
        Zprava zprava = zpravaService.getZpravaByIdUtkani(utkani.idUtkani);
        if (zprava != null && zprava.idDFA != prihlasenyUzivatel.getId()) {
            br.rejectValue("idUtkani", "error.user",
                    "Na toto utkání již napsal/píše zprávu jiný delegát");
            return "nova_zprava";
        }


        if (zprava == null) {
            novaZprava = new Zprava();
            novaZprava.idDFA = prihlasenyUzivatel.getId();
            novaZprava.idUtkani = utkani.idUtkani;
            dfa = prihlasenyUzivatel;
        } else {
            List<Hodnoceni> hodnoceni = hodnoceniService.getHodnoceniByIdZprava(zprava.getId());
            if(hodnoceni.isEmpty()){
                hodnoceniR = new Hodnoceni("R", zprava.getId());
                hodnoceniAR1 = new Hodnoceni("AR1", zprava.getId());
                hodnoceniAR2 = new Hodnoceni("AR2", zprava.getId());
            } else {
                Clen rozhodci = clenService.getClenById(zprava.idR);
                Clen AR1 = clenService.getClenById(zprava.idAR1);
                Clen AR2 = clenService.getClenById(zprava.idAR2);
                Clen dfa = clenService.getClenById(zprava.idDFA);
                Clen TD = clenService.getClenById(zprava.idTD);
                nastavRozhodciVeZprave(rozhodci, AR1, AR2, dfa, TD);
            }
        }
        Soutez soutezNalezena = soutezService.getSoutezByZkratka(utkani.idUtkani);
        utkaniNalezene.dekodujKoloZIDUtkani(utkaniNalezene.idUtkani);
        noveUtkani = utkaniNalezene;
        novaSoutez = soutezNalezena;
        model.addAttribute("utkani", utkaniNalezene);
        model.addAttribute("soutez", soutezNalezena);
        model.addAttribute("novaZprava", novaZprava);
        return "nova_zprava";
    }

    private void nastavRozhodciVeZprave(Clen rozhodci, Clen AR1, Clen AR2, Clen DFA, Clen TD) {
        if(rozhodci != null){
            hlavniRozhodci = rozhodci;
        } else {
            hlavniRozhodci = new Clen();
        }
        if(ar1 != null){
            ar1 = AR1;
        } else {
            ar1 = new Clen();
        }
        if(AR2 != null){
            ar2 = AR2;
        } else {
            ar2 = new Clen();
        }
        if(DFA != null){
            dfa = DFA;
        } else {
            dfa = new Clen();
        }
        if(TD != null){
            td = TD;
        } else {
            td = new Clen();
        }
    }

    @GetMapping("/nova_zprava/ulozit")
    public String ulozZpravu(@Valid @ModelAttribute("novaZprava") Zprava zprava,
                             @Valid @ModelAttribute("hodnoceniR") Hodnoceni hodnoceniRModel,
                             @Valid @ModelAttribute("hodnoceniAR1") Hodnoceni hodnoceniAR1Model,
                             @Valid @ModelAttribute("hodnoceniAR2") Hodnoceni hodnoceniAR2Model,
                             BindingResult br, Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            noveUtkani = null;
            return "redirect:/";
        }
        if (zprava == null){
            return "nova_zprava";
        }
        novaZprava = zprava;
        novaZprava = zpravaService.save(novaZprava);
        pridejAtributyDoModelu(model);
        return "nova_zprava";
    }

}
