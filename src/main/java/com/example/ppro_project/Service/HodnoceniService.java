package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Model.Zprava;
import com.example.ppro_project.Repository.HodnoceniRepository;
import com.example.ppro_project.Repository.SoutezRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Hodnoceni save(Hodnoceni hodnoceni) {
        if(!Objects.equals(hodnoceni.znamka, "7.8") && !hodnoceni.znamka.equals("7.9")) {
            hodnoceni.znamka2 = null;
        }
        List<HodnoceniPopis> tempList = hodnoceni.hodnoceniPopisList;
        Hodnoceni h = hodnoceniRepository.save(hodnoceni);
        if(h != null){
            h.hodnoceniPopisList = tempList;
        }
        return h;
    }
}