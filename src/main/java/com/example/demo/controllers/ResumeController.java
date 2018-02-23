package com.example.demo.controllers;

import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.ResumeRepository;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ResumeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeRepository resumeRepository;

    final private String resumeDir = "domains/resume/";
    final private String resumeURL = "/resume";


    @RequestMapping(resumeURL)
    public String viewResume(Model model, Authentication auth){
        User user = userRepository.findUserByUsername(auth.getName());

        if(user.getRole().equalsIgnoreCase("APPLICANT")){
            model.addAttribute("summary", user.getResume().getSummary());
            model.addAttribute("contact", user.getResume().getContact());
            model.addAttribute("educations", user.getResume().getEducations());
            model.addAttribute("experiences", user.getResume().getExperiences());
            model.addAttribute("skills", user.getResume().getSkills());
            return resumeDir + "detail";
        }else if(user.getRole().equalsIgnoreCase("RECRUITER")){
        }

        Set<User> users = userRepository.findUsersByRole("APPLICANT");
        /*
        model.addAttribute("resumes", resumeRepository.findResumesByApplicantIn(users));
*/
        Set<Resume> resumes = new HashSet<>();
        for(User u: users){
            resumes.add(u.getResume());
        }
        model.addAttribute("resumes", resumes);




        return resumeDir + "list";
    }

    @RequestMapping(resumeURL + "/detail/{id}")
    public String resumeDetail(@PathVariable("id") long id, Model model) {
        Resume resume = resumeRepository.findOne(id);
        model.addAttribute("summary", resume.getSummary());
        model.addAttribute("contact", resume.getContact());
        model.addAttribute("educations", resume.getEducations());
        model.addAttribute("experiences", resume.getExperiences());
        model.addAttribute("skills", resume.getSkills());
        return resumeDir + "detail";
    }
}
