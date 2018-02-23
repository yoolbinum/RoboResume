package com.example.demo.backend.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String organization;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Skill> requiredSkills = new HashSet<>();

    @ManyToMany(mappedBy = "postingJobs")
    private Set<User> postedUsers;

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<User> getPostedUsers() {
        return postedUsers;
    }

    public void setPostedUsers(Set<User> postedUsers) {
        this.postedUsers = postedUsers;
    }
}
