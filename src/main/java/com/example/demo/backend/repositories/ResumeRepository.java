package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ResumeRepository extends CrudRepository<Resume, Long> {
    Resume findResumeByApplicant(User user);

    Set<Resume> findResumesByApplicantIn(Set<User> users);
}
