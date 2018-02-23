package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    Skill findSkillByTitle(String title);
}
