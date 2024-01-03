package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Service.UtkaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UtkaniController {

    public static UtkaniService utkaniService;

    public static Utkani noveUtkani;
    @Autowired
    public UtkaniController(UtkaniService utkaniService) {
        this.utkaniService = utkaniService;
    }

}