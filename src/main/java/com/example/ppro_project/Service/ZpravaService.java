package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Zprava;
import com.example.ppro_project.Repository.ClenRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(zprava != null){
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

    public Zprava save(Zprava zprava) {
        zprava.nastavDobuHry();
        return zpravaRepository.save(zprava);
    }
}
