package com.example.demo.controllers;

import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.repositories.EducationRepository;
import com.example.demo.backend.repositories.ExperienceRepository;
import com.example.demo.backend.repositories.ResumeRepository;
import com.example.demo.backend.repositories.SkillRepository;
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
    ResumeRepository resumeRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    SkillRepository skillRepository;


    final private String resumeDir = "domains/resume/";
    final private String resumeURL = "/resume";

    @RequestMapping(resumeURL)
    public String resumesList(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        return resumeDir + "list";
    }

    @GetMapping(resumeURL + "/add")
    public String resumeForm(Model model){
        model.addAttribute("resume", new Resume());
        return resumeDir + "form";
    }

    @PostMapping(resumeURL + "/process")
    public String processForm(@Valid Resume resume, BindingResult result){
        if(result.hasErrors()){
            return resumeDir + "form";
        }
        resumeRepository.save(resume);
        return "redirect:" + resumeURL;
    }

    @RequestMapping(resumeURL + "/detail/{id}")
    public String showResume(@PathVariable("id") long id, Model model){
        model.addAttribute("resume", resumeRepository.findOne(id));
        return resumeDir+ "show";
    }

    @RequestMapping(resumeURL + "/update/{id}")
    public String updateResume(@PathVariable("id") long id, Model model){
        model.addAttribute("resume", resumeRepository.findOne(id));
        return resumeDir+ "form";
    }

    @RequestMapping(resumeURL + "/view")
    public String viewResume(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("experiences", experienceRepository.findAll());
        model.addAttribute("educations", educationRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return resumeDir + "view";
    }
}
