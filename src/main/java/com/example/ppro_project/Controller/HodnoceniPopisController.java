package com.example.ppro_project.Controller;

import com.example.ppro_project.Service.HodnoceniPopisService;
import com.example.ppro_project.Service.HodnoceniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HodnoceniPopisController {
    public static HodnoceniPopisService hodnoceniPopisService;

    @Autowired
    public HodnoceniPopisController(HodnoceniPopisService hodnoceniPopisService) {
        this.hodnoceniPopisService = hodnoceniPopisService;
    }

}
