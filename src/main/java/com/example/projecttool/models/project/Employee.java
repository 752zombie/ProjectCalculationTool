package com.example.projecttool.models.project;

import java.util.List;

// Magnus

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;

    public Employee(int id, String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.skills = skills;

    }

    public Employee(String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    // Do not delete. Is in use by thymeleaf
    public String getLastName() {
        return lastName == null ? "" : " " + lastName;
    }

    // Do not delete. Is in use by thymeleaf
    public String getFirstName() {
        return firstName;
    }

    // Do not delete. Is in use by thymeleaf
    public List<Skill> getSkills() {
        return skills;
    }

    // Do not delete. Is in use by thymeleaf
    public boolean hasSkill(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return true;
            }

        }
        return false;
    }
}
