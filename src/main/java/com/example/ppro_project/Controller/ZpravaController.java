package com.example.ppro_project.Controller;

import com.example.ppro_project.Service.ClenService;
import com.example.ppro_project.Service.ZpravaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ZpravaController {

    public static ZpravaService zpravaService;

    @Autowired
    public ZpravaController(ZpravaService zpravaService) {
        this.zpravaService = zpravaService;
    }

}
