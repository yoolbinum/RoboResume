package com.example.demo.backend.services;

import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.RoleRepository;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveApplicant(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("APPLICANT")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveEmployer(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("EMPLOYER")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
