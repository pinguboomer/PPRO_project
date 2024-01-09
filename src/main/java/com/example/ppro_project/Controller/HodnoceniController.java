package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.*;
import com.example.ppro_project.Service.HodnoceniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.DELEGAT;
import static com.example.ppro_project.Constants.Constants.ROZHODCI;
import static com.example.ppro_project.Controller.ClenController.*;
import static com.example.ppro_project.Controller.UtkaniController.hledaneUtkani;
import static com.example.ppro_project.Controller.UtkaniController.utkaniService;
import static com.example.ppro_project.Controller.VlastnostController.*;
import static com.example.ppro_project.Controller.ZpravaController.*;

@Controller
public class HodnoceniController {

    public static HodnoceniService hodnoceniService;

    public static List<Posudek> mojePosudky;
    @Autowired
    public HodnoceniController(HodnoceniService hodnoceniService) {
        this.hodnoceniService = hodnoceniService;
    }

    @GetMapping("/rozpracovane")
    public String rozpracovane(Model model) {
        if (!jePrihlasenUzivatel() || Objects.equals(prihlasenyUzivatel.getRole(), ROZHODCI)) {
            return "redirect:/";
        }
        mojePosudky = zpravaService.getZpravyByIdDFARozpracovane(prihlasenyUzivatel.getId());
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("posudky", mojePosudky);
        return "moje_posudky";
    }

    @GetMapping("/posudky")
    public String posudky(Model model) {
        if (!jePrihlasenUzivatel()) {
            return "redirect:/";
        }

        if(Objects.equals(prihlasenyUzivatel.getRole(), ROZHODCI)){
            mojePosudky = zpravaService.getPosudkyByIdR(prihlasenyUzivatel.getId(),
                    prihlasenyUzivatel.idFacr);
        } else {
            mojePosudky = zpravaService.getPosudkyByIdDFA(prihlasenyUzivatel.getId());
        }
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("posudky", mojePosudky);
        return "moje_posudky";
    }

    @GetMapping("/posudek")
    public String posudek(@RequestParam String idUtkani, Model model) {
        if (!jePrihlasenUzivatel()) {
            return "redirect:/";
        }
        Utkani utkaniNalezene = utkaniService.getUtkaniByIdUtkani(idUtkani);
        if (utkaniNalezene == null) {
            kompletniZprava = new KompletniZprava();
            return "redirect:/";
        }
        Zprava zprava = zpravaService.getZpravaByIdUtkani(idUtkani);
        if(zprava == null){
            return "redirect:/";
        }
        if (zprava.idDFA != prihlasenyUzivatel.getId() &&
                zprava.idTD != prihlasenyUzivatel.getId() &&
                zprava.idR != prihlasenyUzivatel.getId() &&
                zprava.idAR1 != prihlasenyUzivatel.getId() &&
                zprava.idAR2 != prihlasenyUzivatel.getId()) {
            kompletniZprava = new KompletniZprava();
            return "redirect:/";
        }
        if(!Objects.equals(prihlasenyUzivatel.getRole(), ROZHODCI) && zprava.stav == 0){
            if(zprava.idDFA == prihlasenyUzivatel.getId()){
                kompletniZprava = new KompletniZprava();
                naplnKompletniZpravu(utkaniNalezene, zprava);
                return "redirect:nova_zprava";
            }
            return "redirect:/";
        }
        kompletniZprava = new KompletniZprava();
        naplnKompletniZpravu(utkaniNalezene, zprava);
        pridejAtributyDoModelu(model);

        return "posudek";
    }

    private void pridejAtributyDoModelu(Model model) {
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("kompletniZprava", kompletniZprava);
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


}
