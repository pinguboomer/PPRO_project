package com.example.ppro_project.Service;

import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Repository.HodnoceniPopisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HodnoceniPopisService {

    private final HodnoceniPopisRepository hodnoceniPopisRepository;

    @Autowired
    public HodnoceniPopisService(HodnoceniPopisRepository hodnoceniPopisRepository) {
        this.hodnoceniPopisRepository = hodnoceniPopisRepository;
    }
    public List<HodnoceniPopis> getByIdHodnoceni(int idHodnoceni) {
        return hodnoceniPopisRepository.findByIdHodnoceni(idHodnoceni);
    }

    public HodnoceniPopis save(HodnoceniPopis hodnoceniPopis) {
        HodnoceniPopis hp =
                hodnoceniPopisRepository.
                        findByIdHodnoceniAndTyp(hodnoceniPopis.idHodnoceni, hodnoceniPopis.typ);
        if(hp != null){
            hodnoceniPopis.setId(hp.getId());
        }
        return hodnoceniPopisRepository.save(hodnoceniPopis);
    }
}