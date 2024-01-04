package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.Zprava;
import com.example.ppro_project.Repository.HodnoceniRepository;
import com.example.ppro_project.Repository.SoutezRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HodnoceniService {

    private final HodnoceniRepository hodnoceniRepository;

    @Autowired
    public HodnoceniService(HodnoceniRepository hodnoceniRepository) {
        this.hodnoceniRepository = hodnoceniRepository;
    }
    public List<Hodnoceni> getHodnoceniByIdZprava(int idZprava) {
        return hodnoceniRepository.findByIdZprava(idZprava);
    }
}