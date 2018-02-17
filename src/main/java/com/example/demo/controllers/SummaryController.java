package com.example.demo.controllers;

import com.example.demo.backend.domains.Summary;
import com.example.demo.backend.repositories.SummaryRepository;
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
public class SummaryController {
    @Autowired
    SummaryRepository SummaryRepository;

    final private String SummaryDir = "domains/summary/";
    final private String SummaryURL = "/summary";

    @RequestMapping(SummaryURL)
    public String SummaryList(Model model){
        model.addAttribute("summaries", SummaryRepository.findAll());
        return SummaryDir + "list";
    }

    @GetMapping(SummaryURL + "/add")
    public String SummaryForm(Model model){
        model.addAttribute("summary", new Summary());
        return SummaryDir + "form";
    }

    @PostMapping(SummaryURL + "/process")
    public String processForm(@Valid Summary Summary, BindingResult result){
        if(result.hasErrors()){
            return SummaryDir + "form";
        }
        SummaryRepository.save(Summary);
        return "redirect:" + SummaryURL;
    }

    @RequestMapping(SummaryURL+ "/update/{id}")
    public String updateSummary(@PathVariable("id") long id, Model model){
        model.addAttribute("summary", SummaryRepository.findOne(id));
        return SummaryDir+ "form";
    }

    @RequestMapping(SummaryURL +"/delete/{id}")
    public String SummaryDelete(@PathVariable("id") long id) {
        SummaryRepository.delete(id);
        return "redirect:" + SummaryURL;
    }
}
