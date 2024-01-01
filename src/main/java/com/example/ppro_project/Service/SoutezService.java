package com.example.ppro_project.Service;

import com.example.ppro_project.Repository.SoutezRepository;
import com.example.ppro_project.Repository.UtkaniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoutezService {

    private final SoutezRepository soutezRepository;

    @Autowired
    public SoutezService(SoutezRepository soutezRepository) {
        this.soutezRepository = soutezRepository;
    }
}