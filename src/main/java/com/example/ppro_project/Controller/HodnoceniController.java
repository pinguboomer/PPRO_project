package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Service.HodnoceniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HodnoceniController {

    public static HodnoceniService hodnoceniService;

    public static Hodnoceni hodnoceniR;
    public static Hodnoceni hodnoceniAR1;
    public static Hodnoceni hodnoceniAR2;
    @Autowired
    public HodnoceniController(HodnoceniService hodnoceniService) {
        this.hodnoceniService = hodnoceniService;
    }

}
