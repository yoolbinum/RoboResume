package com.example.demo.controllers;

import com.example.demo.backend.domains.Education;
import com.example.demo.backend.repositories.EducationRepository;
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
public class EducationController {
    @Autowired
    EducationRepository educationRepository;

    final private String educationDir = "domains/education/";
    final private String eduURL = "/education";

    @RequestMapping(eduURL)
    public String educationList(Model model) {
        model.addAttribute("educations", educationRepository.findAll());
        return educationDir + "list";
    }

    @GetMapping(eduURL + "/add")
    public String educationForm(Model model) {
        model.addAttribute("education", new Education());
        return educationDir + "form";
    }

    @PostMapping(eduURL + "/process")
    public String processForm(@Valid Education education, BindingResult result) {
        if (result.hasErrors()) {
            return educationDir + "form";
        }
        educationRepository.save(education);
        return "redirect:" + eduURL;
    }

    @RequestMapping(eduURL + "/update/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("education", educationRepository.findOne(id));
        return educationDir + "form";
    }

    @RequestMapping(eduURL + "/delete/{id}")
    public String educationDelete(@PathVariable("id") long id) {
        educationRepository.delete(id);
        return "redirect:" + eduURL;
    }
}
