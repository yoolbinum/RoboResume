package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
    Set<Job> findAllByOrganizationContainingIgnoreCase(String string);
}
