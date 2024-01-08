package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Service.UtkaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.ppro_project.Controller.ClenController.jePrihlasenUzivatel;
import static com.example.ppro_project.Controller.ClenController.prihlasenyUzivatel;


@Controller
public class UtkaniController {

    public static UtkaniService utkaniService;

    public static Utkani hledaneUtkani;
    @Autowired
    public UtkaniController(UtkaniService utkaniService) {
        this.utkaniService = utkaniService;
    }

}