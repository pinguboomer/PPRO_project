package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.Vlastnost;
import com.example.ppro_project.Service.ClenService;
import com.example.ppro_project.Service.VlastnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VlastnostController {

    public static List<Vlastnost> vlastnostiListPF;
    public static List<Vlastnost> vlastnostiListOT;
    public static List<Vlastnost> vlastnostiListFyzicka;
    public static List<Vlastnost> vlastnostiListSpoluprace;
    public static List<Vlastnost> vlastnostiListKomentar;

    public static List<Vlastnost> vlastnostiListARPF;
    public static List<Vlastnost> vlastnostiListARPohyb;

    public static VlastnostService vlastnostService;
    @Autowired
    public VlastnostController(VlastnostService vlastnostService) {
        this.vlastnostService = vlastnostService;
    }

}
