package com.example.demo.controllers;

import com.example.demo.backend.domains.Reference;
import com.example.demo.backend.repositories.ReferenceRepository;
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
public class ReferenceController {
    @Autowired
    ReferenceRepository referenceRepository;

    final private String ReferenceDir = "domains/reference/";
    final private String ReferenceURL = "/reference";

    @RequestMapping(ReferenceURL)
    public String ReferenceList(Model model) {
        model.addAttribute("references", referenceRepository.findAll());
        return ReferenceDir + "list";
    }

    @GetMapping(ReferenceURL + "/add")
    public String ReferenceForm(Model model) {
        model.addAttribute("reference", new Reference());
        return ReferenceDir + "form";
    }

    @PostMapping(ReferenceURL + "/process")
    public String processForm(@Valid Reference Reference, BindingResult result) {
        if (result.hasErrors()) {
            return ReferenceDir + "form";
        }
        referenceRepository.save(Reference);
        return "redirect:" + ReferenceURL;
    }

    @RequestMapping(ReferenceURL + "/update/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("reference", referenceRepository.findOne(id));
        return ReferenceDir + "form";
    }

    @RequestMapping(ReferenceURL + "/delete/{id}")
    public String ReferenceDelete(@PathVariable("id") long id) {
        referenceRepository.delete(id);
        return "redirect:" + ReferenceURL;
    }
}
