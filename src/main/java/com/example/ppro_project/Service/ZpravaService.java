package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Repository.ClenRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZpravaService {

    private final ZpravaRepository zpravaRepository;

    @Autowired
    public ZpravaService(ZpravaRepository zpravaRepository) {
        this.zpravaRepository = zpravaRepository;
    }


}
