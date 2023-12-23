package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Repository.ClenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClenService {

    private final ClenRepository clenRepository;

    @Autowired
    public ClenService(ClenRepository clenRepository) {
        this.clenRepository = clenRepository;
    }

    public Clen getClenByIdFacrAndHeslo(String idFacr, String heslo) {
        return clenRepository.findByIdFacrAndHeslo(idFacr, heslo);
    }
}
