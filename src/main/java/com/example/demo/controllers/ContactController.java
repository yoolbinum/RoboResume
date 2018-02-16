package com.example.demo.controllers;

import com.example.demo.backend.domains.Contact;
import com.example.demo.backend.repositories.ContactRepository;
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
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    final private String contactDir = "domains/contact/";
    final private String contactURL = "/contact";

    @RequestMapping(contactURL)
    public String contactList(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return contactDir + "list";
    }

    @GetMapping(contactURL + "/add")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return contactDir + "form";
    }

    @PostMapping(contactURL + "/process")
    public String processForm(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return contactDir + "form";
        }
        contactRepository.save(contact);
        return "redirect:" + contactURL;
    }

    @RequestMapping(contactURL + "/update/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("contact", contactRepository.findOne(id));
        return contactDir + "form";
    }

    @RequestMapping(contactURL + "/delete/{id}")
    public String contactDelete(@PathVariable("id") long id) {
        contactRepository.delete(id);
        return "redirect:" + contactURL;
    }
}
