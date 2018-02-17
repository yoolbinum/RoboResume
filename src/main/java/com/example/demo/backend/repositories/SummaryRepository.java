package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Summary;
import org.springframework.data.repository.CrudRepository;

public interface SummaryRepository extends CrudRepository<Summary, Long> {
}
