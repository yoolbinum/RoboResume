package com.example.demo.backend.domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "resume")
    private User applicant;

    @OneToOne
    private Summary summary;

    @OneToOne
    private Contact contact;

    @OneToMany
    private Set<Education> educations = new HashSet<>();

    @OneToMany
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany
    private Set<Skill> skills = new HashSet<>();

    @OneToMany
    private Set<Contact> reference = new HashSet<>();

    public Resume() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public void addEducation(Education education) {
        this.educations.add(education);
    }
    public void removeEducation(Education education){
        this.educations.remove(education);
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public void addExperience(Experience experience){
        this.experiences.add(experience);
    }

    public void removeExperience(Experience experience){
        this.experiences.remove(experience);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void removeSkill(Skill skill){
        this.skills.remove(skill);
    }

    public Set<Contact> getReference() {
        return reference;
    }

    public void setReference(Set<Contact> reference) {
        this.reference = reference;
    }

    public void addReference(Contact contact){
        this.reference.add(contact);
    }

    public void removeReference(Contact contact){
        this.reference.remove(contact);
    }
}
