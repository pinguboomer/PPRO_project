package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Soutez;
import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Service.DelegaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.ADMIN;
import static com.example.ppro_project.Controller.ClenController.*;
import static com.example.ppro_project.Controller.SoutezController.soutezService;
import static com.example.ppro_project.Controller.UtkaniController.utkaniService;

@Controller
public class DelegaceController {
    public static DelegaceService delegaceService;

    @Autowired
    public DelegaceController(DelegaceService delegaceService) {
        this.delegaceService = delegaceService;
    }


    @GetMapping("/obsazeni")
    public String obsazeni(Model model) {
        if (!jePrihlasenUzivatel() || !Objects.equals(prihlasenyUzivatel.getRole(), ADMIN)) {
            return "redirect:/";
        }
        List<Soutez> soutezList = soutezService.getSoutezeByKategorie("dospělí");
        List<Utkani> utkaniList = utkaniService.getVsechnaUtkani("2023");
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("rozhodciList", rozhodciList);
        model.addAttribute("delegatiList", delegatiList);
        model.addAttribute("utkaniList", utkaniList);
        model.addAttribute("soutezeList", soutezList);
        return "obsazeni";
    }
}
