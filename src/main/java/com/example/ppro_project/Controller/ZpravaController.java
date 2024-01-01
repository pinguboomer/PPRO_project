package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.*;
import com.example.ppro_project.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.ClenController.*;
import static com.example.ppro_project.Controller.VlastnostController.*;

@Controller
public class ZpravaController {

    public static ZpravaService zpravaService;
    public static Utkani noveUtkani;
    public static Zprava novaZprava;
    public static Hodnoceni hodnoceniR;
    public static Hodnoceni hodnoceniAR1;
    public static Hodnoceni hodnoceniAR2;

    public static List<Clen> rozhodciList;
    public static List<Clen> delegatiList;

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
        if(hodnoceniR == null){
            hodnoceniR = new Hodnoceni();
        }
        if(hodnoceniAR1 == null){
            hodnoceniAR1 = new Hodnoceni();
        }
        if(hodnoceniAR2 == null){
            hodnoceniAR2 = new Hodnoceni();
        }
        rozhodciList = clenService.getRozhodci();
        delegatiList = clenService.getDelegati();
        vlastnostiListPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_APLIKACE_PF);
        vlastnostiListOT = vlastnostService.getVlastnostiByKategorie(KATEGORIE_OT);
        vlastnostiListFyzicka = vlastnostService.getVlastnostiByKategorie(KATEGORIE_FYZICKA);
        vlastnostiListSpoluprace = vlastnostService.getVlastnostiByKategorie(KATEGORIE_SPOLUPRACE);
        vlastnostiListKomentar = vlastnostService.getVlastnostiByKategorie(KATEGORIE_KOMENTAR);
        vlastnostiListARPF = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPF);
        vlastnostiListARPohyb = vlastnostService.getVlastnostiByKategorie(KATEGORIE_ARPOHYB);
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("utkani", noveUtkani);
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

    @GetMapping("/nova_zprava/vyhledejUtkani")
    public String vyhledejUtkani(Model model){
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
        model.addAttribute("utkani", noveUtkani);
        model.addAttribute("novaZprava", novaZprava);
        model.addAttribute("hodnoceniR", hodnoceniR);
        model.addAttribute("hodnoceniAR1", hodnoceniAR1);
        model.addAttribute("hodnoceniAR2", hodnoceniAR2);
        model.addAttribute("rozhodciList", rozhodciList);
        model.addAttribute("delegatiList", delegatiList);
        return "nova_zprava";
    }


}
