package com.example.demo.controllers;

import com.example.demo.backend.domains.Experience;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.ExperienceRepository;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    UserRepository userRepository;

    final private String expDir = "domains/experience/";
    final private String expURL = "/experience";

    @RequestMapping(expURL)
    public String experienceList(Model model, Authentication auth){
        User user = userRepository.findUserByUsername(auth.getName());
        model.addAttribute("experiences", user.getResume().getExperiences());
        return expDir + "list";
    }

    @GetMapping(expURL + "/add")
    public String experienceForm(Model model){
        model.addAttribute("experience", new Experience());
        return expDir + "form";
    }

    @PostMapping(expURL + "/process")
    public String processForm(@Valid Experience experience, BindingResult result, Authentication auth){
        if(result.hasErrors()){
            return expDir + "form";
        }
        experienceRepository.save(experience);
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().addExperience(experience);
        userRepository.save(user);
        return "redirect:" + expURL;
    }

    @RequestMapping(expURL + "/update/{id}")
    public String updateExperience(@PathVariable("id") long id, Model model){
        model.addAttribute("experience", experienceRepository.findOne(id));
        return expDir+ "form";
    }

    @RequestMapping(expURL + "/delete/{id}")
    public String experienceDelete(@PathVariable("id") long id, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().removeExperience(experienceRepository.findOne(id));
        userRepository.save(user);
        return "redirect:" + expURL;
    }
}
