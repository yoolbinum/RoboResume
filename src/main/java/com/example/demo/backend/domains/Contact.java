package com.example.demo.backend.domains;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Contact {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
