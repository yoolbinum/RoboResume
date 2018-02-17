package com.example.demo.component;

import com.example.demo.backend.domains.Role;
import com.example.demo.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading Data ...");
        Role r = new Role();
        r.setRole("EMPLOYER");
        roleRepository.save(r);

        r = new Role();
        r.setRole("APPLICANT");
        roleRepository.save(r);
    }
}
