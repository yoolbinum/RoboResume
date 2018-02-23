package com.example.demo.backend.domains;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=1)
    private String title;

    @NotNull
    private String rating;

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<Job> matchedJobs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Set<Job> getMatchedJobs() {
        return matchedJobs;
    }

    public void setMatchedJobs(Set<Job> matchedJobs) {
        this.matchedJobs = matchedJobs;
    }
}
