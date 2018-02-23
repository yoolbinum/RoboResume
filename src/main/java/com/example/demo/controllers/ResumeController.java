package com.example.demo.controllers;

import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResumeController {
    @Autowired
    UserRepository userRepository;

    final private String resumeDir = "domains/resume/";
    final private String resumeURL = "/resume";


    @RequestMapping(resumeURL)
    public String viewResume(Model model){
        model.addAttribute("applicants", userRepository.findByRole("APPLICANT"));

        return resumeDir + "list";
    }
}
