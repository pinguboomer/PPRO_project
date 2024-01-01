package com.example.ppro_project.Service;

import com.example.ppro_project.Repository.HodnoceniRepository;
import com.example.ppro_project.Repository.SoutezRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HodnoceniService {

    private final HodnoceniRepository hodnoceniRepository;

    @Autowired
    public HodnoceniService(HodnoceniRepository hodnoceniRepository) {
        this.hodnoceniRepository = hodnoceniRepository;
    }
}