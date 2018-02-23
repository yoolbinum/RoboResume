package com.example.demo.controllers;

import com.example.demo.backend.domains.Contact;
import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.ContactRepository;
import com.example.demo.backend.repositories.ResumeRepository;
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
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    UserRepository userRepository;

    final private String contactDir = "domains/contact/";
    final private String contactURL = "/contact";

    @RequestMapping(contactURL)
    public String contactList(Model model, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        Contact contact = user.getResume().getContact();
        model.addAttribute("contact", contact);
        return contactDir + "list";
    }

    @GetMapping(contactURL + "/add")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return contactDir + "form";
    }

    @PostMapping(contactURL + "/process")
    public String processForm(@Valid Contact contact, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return contactDir + "form";
        }
        contactRepository.save(contact);
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().setContact(contact);
        userRepository.save(user);
        return "redirect:" + contactURL;
    }
}
