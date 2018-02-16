package com.example.demo.backend.repositories;

import com.example.demo.backend.domains.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByRole(String role);
}
