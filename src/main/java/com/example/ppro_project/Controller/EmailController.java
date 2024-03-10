package com.example.ppro_project.Controller;

import com.example.ppro_project.Service.ClenService;
import com.example.ppro_project.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmailController {

    public static EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

}
