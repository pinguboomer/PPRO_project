package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Posudek;
import com.example.ppro_project.Model.Zprava;
import com.example.ppro_project.Repository.ClenRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.DELEGAT;

@Service
public class ZpravaService {

    private final ZpravaRepository zpravaRepository;

    @Autowired
    public ZpravaService(ZpravaRepository zpravaRepository) {
        this.zpravaRepository = zpravaRepository;
    }

    public Zprava getZpravaByIdUtkani(String idUtkani) {
        Zprava zprava = zpravaRepository.findByIdUtkani(idUtkani);
        if (zprava != null) {
            zprava.dekodujNastaveni();
        }
        return zprava;
    }

    public List<Zprava> getZpravyByIdDFA(int idDFA) {
        return zpravaRepository.findByIdDfa(idDFA);
    }

    public List<Zprava> getZpravyByIdRozhodci(int idR) {
        return zpravaRepository.findByIdRorIdAr1orIdAr2(idR, idR, idR);
    }

    public List<Posudek> getPosudkyByIdDFA(int idDFA) {
        List<Object[]> objects = zpravaRepository.findPosudekByDFA(idDFA);
        List<Posudek> posudky = new ArrayList<>();
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String prijmeni = (String) object[index++];
            String jmeno = (String) object[index++];
            String idFacr = (String) object[index++];
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek));
        }
        return posudky;
    }

    public List<Posudek> getPosudkyByIdR(int idR, String idFacr) {
        List<Object[]> objects = zpravaRepository.findPosudekByR(idR);
        List<Posudek> posudky = new ArrayList<>();
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String prijmeni = (String) object[index++];
            String jmeno = (String) object[index++];
            String idFacr2 = (String) object[index++];
            if(!Objects.equals(idFacr2, idFacr)){
                continue;
            }
            String roleR = (String) object[index++];
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek, roleR));
        }
        return posudky;
    }

    public Zprava save(Zprava zprava) {
        zprava.nastavDobuHry();
        return zpravaRepository.save(zprava);
    }
}
