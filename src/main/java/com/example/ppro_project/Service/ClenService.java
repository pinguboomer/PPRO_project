package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Repository.ClenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ppro_project.Constants.Constants.DELEGAT;
import static com.example.ppro_project.Constants.Constants.ROZHODCI;

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

    public List<Clen> getRozhodci() {
        return clenRepository.findByRole(ROZHODCI);
    }

    public List<Clen> getDelegati() {
        return clenRepository.findByRole(DELEGAT);
    }

    public Clen getClenById(int idClen) {
        return clenRepository.findById(idClen);
    }
}
