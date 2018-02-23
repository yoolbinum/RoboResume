package com.example.demo.controllers;

import com.example.demo.backend.domains.Contact;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.ContactRepository;
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
public class ReferenceController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    final private String ReferenceDir = "domains/reference/";
    final private String ReferenceURL = "/reference";

    @RequestMapping(ReferenceURL)
    public String ReferenceList(Model model, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        model.addAttribute("references", user.getResume().getReference());
        return ReferenceDir + "list";
    }

    @GetMapping(ReferenceURL + "/add")
    public String ReferenceForm(Model model) {
        model.addAttribute("reference", new Contact());
        return ReferenceDir + "form";
    }

    @PostMapping(ReferenceURL + "/process")
    public String processForm(@Valid Contact contact, BindingResult result,Authentication auth) {
        if (result.hasErrors()) {
            return ReferenceDir + "form";
        }
        contactRepository.save(contact);
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().addReference(contact);
        userRepository.save(user);
        return "redirect:" + ReferenceURL;
    }

    @RequestMapping(ReferenceURL + "/update/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("reference", contactRepository.findOne(id));
        return ReferenceDir + "form";
    }

    @RequestMapping(ReferenceURL + "/delete/{id}")
    public String ReferenceDelete(@PathVariable("id") long id, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().removeReference(contactRepository.findOne(id));
        userRepository.save(user);
        return "redirect:" + ReferenceURL;
    }
}
