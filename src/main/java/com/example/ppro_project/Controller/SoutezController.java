package com.example.ppro_project.Controller;

import com.example.ppro_project.Service.SoutezService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class SoutezController {

    public static SoutezService soutezService;
    @Autowired
    public SoutezController(SoutezService soutezService) {
        this.soutezService = soutezService;
    }

}