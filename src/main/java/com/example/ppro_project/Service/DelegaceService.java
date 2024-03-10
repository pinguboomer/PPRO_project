package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Delegace;
import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Repository.DelegaceRepository;
import com.example.ppro_project.Repository.HodnoceniPopisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegaceService {

    private final DelegaceRepository delegaceRepository;

    @Autowired
    public DelegaceService(DelegaceRepository delegaceRepository) {
        this.delegaceRepository = delegaceRepository;
    }

    public Delegace getById(int id) {
        return delegaceRepository.findById(id);
    }

 //   public Delegace getByUtkani(String utkani) {
    //    return delegaceRepository.findByIdUtkani(utkani);
   // }

    public List<Delegace> getDelegaceClena(int idClena) {
        return delegaceRepository.findDelegaceByIdClen(idClena);
    }

    public Delegace save(Delegace delegace) {
        return delegaceRepository.save(delegace);
    }
}
