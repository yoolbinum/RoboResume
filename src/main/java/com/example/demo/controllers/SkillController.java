package com.example.demo.controllers;

import com.example.demo.backend.domains.Skill;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.SkillRepository;
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
public class SkillController {
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UserRepository userRepository;

    final private String skillDir = "domains/skill/";
    final private String skillURL = "/skill";

    @RequestMapping(skillURL)
    public String skillList(Model model, Authentication auth){
        User user = userRepository.findUserByUsername(auth.getName());
        if(user.getRole().equalsIgnoreCase("APPLICANT")) {
            model.addAttribute("skills", user.getResume().getSkills());
        }else if(user.getRole().equalsIgnoreCase("RECRUITER")){
            model.addAttribute("skills", skillRepository.findAll());
        }
        return skillDir + "list";
    }

    @GetMapping(skillURL + "/add")
    public String skillForm(Model model){
        model.addAttribute("skill", new Skill());
        return skillDir + "form";
    }

    @PostMapping(skillURL + "/process")
    public String processForm(@Valid Skill skill, BindingResult result, Authentication auth){
        if(result.hasErrors()){
            return skillDir + "form";
        }
        skillRepository.save(skill);
        User user = userRepository.findUserByUsername(auth.getName());
        if(user.getRole().equalsIgnoreCase("APPLICANT")){
            user.getResume().addSkill(skill);
            userRepository.save(user);
        }

        return "redirect:" + skillURL;
    }

    @RequestMapping(skillURL+ "/update/{id}")
    public String updateSkill(@PathVariable("id") long id, Model model){
        model.addAttribute("skill", skillRepository.findOne(id));
        return skillDir+ "form";
    }

    @RequestMapping(skillURL +"/delete/{id}")
    public String skillDelete(@PathVariable("id") long id, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        if(user.getRole().equalsIgnoreCase("APPLICANT")) {
            user.getResume().removeSkill(skillRepository.findOne(id));
            userRepository.save(user);
        }else if(user.getRole().equalsIgnoreCase("RECRUITER")){
            skillRepository.delete(id);
        }

        return "redirect:" + skillURL;
    }

}

