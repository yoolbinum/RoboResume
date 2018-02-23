package com.example.demo.controllers;

import com.example.demo.backend.domains.Summary;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.ResumeRepository;
import com.example.demo.backend.repositories.SummaryRepository;
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
public class SummaryController {
    @Autowired
    SummaryRepository summaryRepository;

    @Autowired
    UserRepository userRepository;

    final private String summaryDir = "domains/summary/";
    final private String summaryURL = "/summary";

    @RequestMapping(summaryURL)
    public String SummaryList(Model model, Authentication auth){
        User user = userRepository.findUserByUsername(auth.getName());
        Summary summary = user.getResume().getSummary();
        model.addAttribute("summary", summary);
        return summaryDir + "list";
    }

    @GetMapping(summaryURL + "/add")
    public String SummaryForm(Model model){
        model.addAttribute("summary", new Summary());
        return summaryDir + "form";
    }

    @PostMapping(summaryURL + "/process")
    public String processForm(@Valid Summary summary, BindingResult result, Authentication auth){
        if(result.hasErrors()){
            return summaryDir + "form";
        }
        summaryRepository.save(summary);
        User user = userRepository.findUserByUsername(auth.getName());
        user.getResume().setSummary(summary);
        userRepository.save(user);
        return "redirect:" + summaryURL;
    }
}
