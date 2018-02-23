package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long>{
    User findUserByUsername(String username);
    Long countByUsername(String username);
    Set<User> findByRole(String role);
}
