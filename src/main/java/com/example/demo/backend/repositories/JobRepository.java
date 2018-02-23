package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Job;
import com.example.demo.backend.domains.Skill;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
    Set<Job> findByRequiredSkills(Set<Skill> skills);
}
