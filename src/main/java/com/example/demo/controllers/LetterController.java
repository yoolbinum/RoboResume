package com.example.demo.controllers;

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
