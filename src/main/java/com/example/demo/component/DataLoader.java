package com.example.demo.component;

import com.example.demo.backend.domains.Role;
import com.example.demo.backend.repositories.RoleRepository;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading Data ...");

        roleRepository.save(new Role("EMPLOYER"));
        roleRepository.save(new Role("APPLICANT"));
    }
}
