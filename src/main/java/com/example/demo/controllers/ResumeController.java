package com.example.demo.controllers;

import com.example.demo.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ResumeController {
    @Autowired
    SummaryRepository summaryRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    SkillRepository skillRepository;


    final private String resumeDir = "domains/resume/";
    final private String resumeURL = "/resume";


    @RequestMapping(resumeURL)
    public String viewResume(Model model){
        model.addAttribute("summaries", summaryRepository.findAll());
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("experiences", experienceRepository.findAll());
        model.addAttribute("educations", educationRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return resumeDir + "view";
    }
}
