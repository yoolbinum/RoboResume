package com.example.demo.controllers;

import com.example.demo.backend.domains.Reference;
import com.example.demo.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LetterController {

    final private String letterDir = "domains/letter/";
    final private String letterURL = "/letter";


    @RequestMapping(letterURL)
    public String viewResume(Model model){
        return letterDir + "view";
    }
}
