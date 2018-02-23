package com.example.demo.controllers;

import com.example.demo.backend.domains.Job;
import com.example.demo.backend.domains.Skill;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.JobRepository;
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
import java.util.HashSet;
import java.util.Set;

@Controller
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    final private String jobDir = "domains/job/";
    final private String jobURL = "/job";

    @RequestMapping(jobURL)
    public String jobList(Model model, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        Set<Job> jobs = new HashSet<>();
        if (user.getRole().equalsIgnoreCase("RECRUITER")) {
            jobs = user.getPostingJobs();
        } else if (user.getRole().equalsIgnoreCase("APPLICANT")) {
            Set<Skill> skills = user.getResume().getSkills();
            for(Job job : jobRepository.findAll()){
                Object[] skillArray =  job.getRequiredSkills().toArray();
                Skill jobSkill = (Skill)skillArray[0];
                for(Skill skill : skills){
                    if(jobSkill.getTitle().equalsIgnoreCase(skill.getTitle())){
                        jobs.add(job);
                    }
                }
            }
        }
        model.addAttribute("jobs", jobs);
        return jobDir + "list";
    }

    @GetMapping(jobURL + "/add")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("skill", new Skill());
        return jobDir + "form";
    }

    @PostMapping(jobURL + "/process")
    public String processForm(@Valid Job job, @Valid Skill skill, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return jobDir + "form";
        }
        skillRepository.save(skill);
        job.getRequiredSkills().add(skill);
        jobRepository.save(job);
        User user = userRepository.findUserByUsername(auth.getName());
        user.getPostingJobs().add(job);
        userRepository.save(user);
        return "redirect:" + jobURL;
    }

    @RequestMapping(jobURL + "/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model) {
        Job job = jobRepository.findOne(id);
        model.addAttribute("job", job);
        Object[] skillArray =  job.getRequiredSkills().toArray();
        Skill skill = (Skill)skillArray[0];
        model.addAttribute("skill", skill);
        return jobDir + "form";
    }

    @RequestMapping(jobURL + "/delete/{id}")
    public String jobDelete(@PathVariable("id") long id, Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName());
        user.getPostingJobs().remove(jobRepository.findOne(id));
        userRepository.save(user);
        return "redirect:" + jobURL;
    }

    @RequestMapping(jobURL + "/detail/{id}")
    public String jobDetail(@PathVariable("id") long id, Model model) {
        Job job = jobRepository.findOne(id);
        model.addAttribute("job", job);
        Object[] skillArray =  job.getRequiredSkills().toArray();
        Skill skill = (Skill)skillArray[0];
        model.addAttribute("skills", skill);
        return jobDir + "detail";
    }
}
