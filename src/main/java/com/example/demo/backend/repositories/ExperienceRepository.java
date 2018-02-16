package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {
}
