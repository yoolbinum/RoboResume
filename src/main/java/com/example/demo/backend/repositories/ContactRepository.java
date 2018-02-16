package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
