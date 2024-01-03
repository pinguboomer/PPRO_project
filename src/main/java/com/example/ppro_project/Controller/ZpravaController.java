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

    @Autowired
    public ZpravaController(ZpravaService zpravaService) {
        this.zpravaService = zpravaService;
    }


    @GetMapping("/nova_zprava")
    public String novaZprava(Model model){
        if(!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI){
            noveUtkani = null;
            return "redirect:/";
        }
        if(noveUtkani == null){
            noveUtkani = new Utkani();
        }
        if(novaZprava == null){
            novaZprava = new Zprava();
        }
        if(novaSoutez == null){
            novaSoutez = new Soutez();
        }
        if(hodnoceniR == null){
            hodnoceniR = new Hodnoceni();
        }
        if(hodnoceniAR1 == null){
            hodnoceniAR1 = new Hodnoceni();
        }
        if(hodnoceniAR2 == null){
            hodnoceniAR2 = new Hodnoceni();
        }


        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("utkani", noveUtkani);
        model.addAttribute("soutez", novaSoutez);
        model.addAttribute("novaZprava", novaZprava);
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
        return "nova_zprava";
    }

    @PostMapping("/nova_zprava/vyhledejUtkani")
    public String vyhledejUtkani(@Valid @ModelAttribute("utkani") Utkani utkani,
                                 BindingResult br, Model model){
        if(!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI){
            noveUtkani = null;
            return "redirect:/";
        }
        if(noveUtkani == null){
            noveUtkani = new Utkani();
        }
        if(novaZprava == null){
            novaZprava = new Zprava();
        }
        if(hodnoceniR == null){
            hodnoceniR = new Hodnoceni();
        }
        if(hodnoceniAR1 == null){
            hodnoceniAR1 = new Hodnoceni();
        }
        if(hodnoceniAR2 == null){
            hodnoceniAR2 = new Hodnoceni();
        }

        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("novaZprava", novaZprava);
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

        if(utkani == null || utkani.idUtkani == null || utkani.idUtkani.isEmpty()){
            br.rejectValue("idUtkani", "error.user", "Chybné údaje");
            return "nova_zprava";
        }
        Utkani utkaniNalezene = utkaniService.getUtkaniByIdUtkani(utkani.idUtkani);
        if(utkaniNalezene == null){
            br.rejectValue("idUtkani", "error.user", "Zpráva nenalezena");
            return "nova_zprava";
        }
        Soutez soutezNalezena = soutezService.getSoutezByZkratka(utkani.idUtkani);
        utkaniNalezene.dekodujKoloZIDUtkani(utkaniNalezene.idUtkani);
        noveUtkani = utkaniNalezene;
        novaSoutez = soutezNalezena;
        model.addAttribute("utkani", utkaniNalezene);
        model.addAttribute("soutez", soutezNalezena);
        return "nova_zprava";
    }


}
