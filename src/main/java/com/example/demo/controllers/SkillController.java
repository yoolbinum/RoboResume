package com.example.demo.controllers;

import com.example.demo.backend.domains.Skill;
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
public class SkillController {
    @Autowired
    SkillRepository skillRepository;

    final private String skillDir = "domains/skill/";
    final private String skillURL = "/skill";

    @RequestMapping(skillURL)
    public String skillList(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return skillDir + "list";
    }

    @GetMapping(skillURL + "/add")
    public String skillForm(Model model){
        model.addAttribute("skill", new Skill());
        return skillDir + "form";
    }

    @PostMapping(skillURL + "/process")
    public String processForm(@Valid Skill skill, BindingResult result){
        if(result.hasErrors()){
            return skillDir + "form";
        }
        skillRepository.save(skill);
        return "redirect:" + skillURL;
    }

    @RequestMapping(skillURL+ "/update/{id}")
    public String updateSkill(@PathVariable("id") long id, Model model){
        model.addAttribute("skill", skillRepository.findOne(id));
        return skillDir+ "form";
    }

    @RequestMapping(skillURL +"/delete/{id}")
    public String skillDelete(@PathVariable("id") long id) {
        skillRepository.delete(id);
        return "redirect:" + skillURL;
    }

}

