package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Repository.UtkaniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtkaniService {

    private final UtkaniRepository utkaniRepository;

    @Autowired
    public UtkaniService(UtkaniRepository utkaniRepository) {
        this.utkaniRepository = utkaniRepository;
    }

    public Utkani getUtkaniByIdUtkani(String idUtkani) {
        return utkaniRepository.findByIdUtkani(idUtkani);
    }
}
