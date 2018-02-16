package com.example.demo.controllers;

import com.example.demo.backend.domains.Experience;
import com.example.demo.backend.repositories.ExperienceRepository;
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
public class ExperienceController {
    @Autowired
    ExperienceRepository experienceRepository;

    final private String expDir = "domains/experience/";
    final private String expURL = "/experience";

    @RequestMapping(expURL)
    public String experienceList(Model model){
        model.addAttribute("experiences", experienceRepository.findAll());
        return expDir + "list";
    }

    @GetMapping(expURL + "/add")
    public String experienceForm(Model model){
        model.addAttribute("experience", new Experience());
        return expDir + "form";
    }

    @PostMapping(expURL + "/process")
    public String processForm(@Valid Experience experience, BindingResult result){
        if(result.hasErrors()){
            return expDir + "form";
        }
        experienceRepository.save(experience);
        return "redirect:" + expURL;
    }

    @RequestMapping(expURL + "/update/{id}")
    public String updateExperience(@PathVariable("id") long id, Model model){
        model.addAttribute("experience", experienceRepository.findOne(id));
        return expDir+ "form";
    }

    @RequestMapping(expURL + "/delete/{id}")
    public String experienceDelete(@PathVariable("id") long id) {
        experienceRepository.delete(id);
        return "redirect:" + expURL;
    }
}
