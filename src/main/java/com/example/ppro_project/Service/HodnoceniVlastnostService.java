package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.HodnoceniVlastnost;
import com.example.ppro_project.Repository.HodnoceniRepository;
import com.example.ppro_project.Repository.HodnoceniVlastnostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class HodnoceniVlastnostService {

    private final HodnoceniVlastnostRepository hodnoceniVlastnostRepository;

    @Autowired
    public HodnoceniVlastnostService(HodnoceniVlastnostRepository hodnoceniVlastnostRepository) {
        this.hodnoceniVlastnostRepository = hodnoceniVlastnostRepository;
    }
    public List<HodnoceniVlastnost> getVlastnostiIdByIdPopis(int idHodnoceni) {
        return hodnoceniVlastnostRepository.findVlastnostiIdByIdPopis(idHodnoceni);
    }


    public void vymazVsechnyPodleIdPopis(int idPopis) {
        hodnoceniVlastnostRepository.deleteByIdPopis(idPopis);
    }
    public HodnoceniVlastnost save(int idPopis, int idVlastnost, int typ) {

        HodnoceniVlastnost hv = new HodnoceniVlastnost(idPopis, idVlastnost, typ);
        return hodnoceniVlastnostRepository.save(hv);
    }
}