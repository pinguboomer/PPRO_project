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
    public List<HodnoceniVlastnost> getVlastnostiIdByIdPopis(int idHodnoceni) {
        return hodnoceniVlastnostRepository.findVlastnostiIdByIdPopis(idHodnoceni);
    }

    public HodnoceniVlastnost save(int idPopis, int idVlastnost, int typ) {
        HodnoceniVlastnost hv =
                hodnoceniVlastnostRepository.findByPopisAndVlastnost(idPopis, idVlastnost);
        if(hv == null){
            hv = new HodnoceniVlastnost(idPopis, idVlastnost, typ);
        } else {
            hv.idPopis = idPopis;
            hv.idVlastnost = idVlastnost;
            hv.typ = typ;
        }
        return hodnoceniVlastnostRepository.save(hv);
    }
}