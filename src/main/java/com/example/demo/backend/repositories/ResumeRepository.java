package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Long> {
    Resume findByApplicant_Username(String username);
}
