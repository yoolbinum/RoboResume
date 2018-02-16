package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Long> {
}
