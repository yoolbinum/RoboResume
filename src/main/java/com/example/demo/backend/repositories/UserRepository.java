package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    Long countByUsername(String username);
}
