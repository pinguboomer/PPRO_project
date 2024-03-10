package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Soutez;
import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Repository.SoutezRepository;
import com.example.ppro_project.Repository.UtkaniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoutezService {

    private final SoutezRepository soutezRepository;

    @Autowired
    public SoutezService(SoutezRepository soutezRepository) {
        this.soutezRepository = soutezRepository;
    }

    public Soutez getSoutezByZkratka(String zkratka) {
        return soutezRepository.findByZkratka(zkratka);
    }

    public List<Soutez> getSoutezeByKategorie(String kategorie) {
        return soutezRepository.findByKategorie(kategorie);
    }
}