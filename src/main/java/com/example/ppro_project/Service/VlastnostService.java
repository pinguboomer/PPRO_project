package com.example.ppro_project.Service;


import com.example.ppro_project.Model.Vlastnost;
import com.example.ppro_project.Repository.VlastnostRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VlastnostService {

    private final VlastnostRepository vlastnostRepository;

    @Autowired
    public VlastnostService(VlastnostRepository vlastnostRepository) {
        this.vlastnostRepository = vlastnostRepository;
    }

    public Vlastnost getVlastnostiById(int idVlastnost) {
        Optional<Vlastnost> vl = vlastnostRepository.findById(idVlastnost);
        return vl.orElseGet(Vlastnost::new);
    }

    public List<Vlastnost> getVlastnostiByKategorie(int kategorie) {
        return vlastnostRepository.findByKategorie(kategorie);
    }

    public List<Vlastnost> getNejcastejsiVlastnostiRByIdR(int idClen, int typ) {
        return vlastnostRepository.findNejcastejsiVlastnostiRByIdR(idClen, typ);
    }

    public List<Vlastnost> getNejcastejsiVlastnostiARByIdR(int idClen, int typ) {
        return vlastnostRepository.findNejcastejsiVlastnostiARByIdR(idClen, typ);
    }
}
