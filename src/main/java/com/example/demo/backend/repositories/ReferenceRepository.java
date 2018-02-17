package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Reference;
import org.springframework.data.repository.CrudRepository;

public interface ReferenceRepository extends CrudRepository<Reference, Long> {
}
