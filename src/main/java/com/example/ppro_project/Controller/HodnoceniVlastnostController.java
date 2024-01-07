package com.example.ppro_project.Controller;

import com.example.ppro_project.Service.HodnoceniPopisService;
import com.example.ppro_project.Service.HodnoceniVlastnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HodnoceniVlastnostController {
    public static HodnoceniVlastnostService hodnoceniVlastnostService;

    @Autowired
    public HodnoceniVlastnostController(HodnoceniVlastnostService hodnoceniVlastnostService) {
        this.hodnoceniVlastnostService = hodnoceniVlastnostService;
    }

}