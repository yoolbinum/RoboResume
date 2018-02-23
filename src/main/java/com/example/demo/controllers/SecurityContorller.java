package com.example.demo.controllers;

import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.domains.Role;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.RoleRepository;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class SecurityContorller {
    private final String secDir = "security/";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/login")
    public String longin(){
        return secDir + "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return secDir + "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            return "registration";
        }else{
            model.addAttribute(user.getUsername()+" created");
            Role r = roleRepository.findByRole(user.getRole());
            user.addRole(r);
            if(user.getRole().equalsIgnoreCase("APPLICANT")){
                user.setResume(new Resume());
            }
            userRepository.save(user);
        }

        return "redirect:/" ;
    }
}
