package com.example.ppro_project.Service;


import com.example.ppro_project.Model.Vlastnost;
import com.example.ppro_project.Repository.VlastnostRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VlastnostService {

    private final VlastnostRepository vlastnostRepository;

    @Autowired
    public VlastnostService(VlastnostRepository vlastnostRepository) {
        this.vlastnostRepository = vlastnostRepository;
    }

    public List<Vlastnost> getVlastnostiByKategorie(int kategorie) {
        return vlastnostRepository.findByKategorie(kategorie);
    }
}
