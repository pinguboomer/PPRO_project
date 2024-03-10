package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Posudek;
import com.example.ppro_project.Repository.ClenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Clen getClenByIdFacrAndRole(String idFacr, String role) {
        return clenRepository.findByIdFacrAndRole(idFacr, role);
    }

    public Clen getClenByEmailAndRole(String email, String role) {
        List<Clen> clens = clenRepository.findByEmailAndRole(email, role);
        if(clens != null && clens.size() > 0){
            return clens.get(0);
        }
        return null;
    }

    public Clen getClenByIdFacrAndHeslo(String idFacr, String heslo) {
        return clenRepository.findByIdFacrAndHeslo(idFacr, heslo);
    }

  //  public void updateHeslo(int id, String noveHeslo) {
       // clenRepository.updateHeslo(id, noveHeslo);
   // }

    public void updateClen(Clen clen) {
        clenRepository.save(clen);
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

    public Posudek getNejlepsiZnamkaRByIdR(int idClen) {
        List<Object[]> objects = clenRepository.findNejlepsiZnamkaRByIdR(idClen);

        return dekodujZnamkuNaPosudek(objects);
    }

    public Posudek getNejhorsiZnamkaRByIdR(int idClen) {
        List<Object[]> objects = clenRepository.findNejhorsiZnamkaRByIdR(idClen);
        return dekodujZnamkuNaPosudek(objects);
    }

    public Posudek getNejlepsiZnamkaARByIdR(int idClen) {
        List<Object[]> objects = clenRepository.findNejlepsiZnamkaARByIdR(idClen);
        return dekodujZnamkuNaPosudek(objects);
    }

    public Posudek getNejhorsiZnamkaARByIdR(int idClen) {
        List<Object[]> objects = clenRepository.findNejhorsiZnamkaARByIdR(idClen);
        return dekodujZnamkuNaPosudek(objects);
    }
    private Posudek dekodujZnamkuNaPosudek(List<Object[]> objects) {
        Posudek posudek = new Posudek();
        for (Object[] object : objects) {
            int index = 0;
            posudek.idUtkani = (String) object[index++];
            posudek.znamka = (String) object[index++];
            if(posudek.znamka.equals("7.8") || posudek.znamka.equals("7.9")){
                posudek.znamka = posudek.znamka + " (*" + object[index] + ")";
            }
            return posudek;
        }
        return posudek;
    }
}
