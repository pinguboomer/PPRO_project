package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.HodnoceniVlastnost;
import com.example.ppro_project.Repository.HodnoceniRepository;
import com.example.ppro_project.Repository.HodnoceniVlastnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HodnoceniVlastnostService {

    private final HodnoceniVlastnostRepository hodnoceniVlastnostRepository;

    @Autowired
    public HodnoceniVlastnostService(HodnoceniVlastnostRepository hodnoceniVlastnostRepository) {
        this.hodnoceniVlastnostRepository = hodnoceniVlastnostRepository;
    }
    public List<Integer> getVlastnostiIdByIdHodnoceni(int idHodnoceni) {
        return hodnoceniVlastnostRepository.findVlastnostiIdByIdHodnoceni(idHodnoceni);
    }

    public HodnoceniVlastnost save(int idHodnoceni, int idVlastnost, int typ) {
        HodnoceniVlastnost hv =
                hodnoceniVlastnostRepository.findByHodnoceniAndVlastnost(idHodnoceni, idVlastnost);
        if(hv == null){
            hv = new HodnoceniVlastnost(idHodnoceni, idVlastnost, typ);
        } else {
            hv.idHodnoceni = idHodnoceni;
            hv.idVlastnost = idVlastnost;
            hv.typ = typ;
        }
        return hodnoceniVlastnostRepository.save(hv);
    }
}