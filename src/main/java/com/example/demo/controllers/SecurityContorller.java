package com.example.demo.controllers;

import com.example.demo.backend.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.backend.services.UserService;

import javax.validation.Valid;

@Controller
public class SecurityContorller {
    private final String secDir = "security/";

    @Autowired UserService userService;

    @RequestMapping("/login")
    public String longin(){
        return secDir + "login";
    }

    @RequestMapping("/secure")
    public String secure(){
        return secDir + "secure";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return secDir + "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "registration";
        }else{
            if(user.getRole() == "EMPLOYER"){
                userService.saveEmployer(user);
            }else{ // if user.getRole() == "APPLICANT"
                userService.saveApplicant(user);
            }
            model.addAttribute("message", "User Account Successfully Created");
        }

        return secDir + "login";
    }
}
