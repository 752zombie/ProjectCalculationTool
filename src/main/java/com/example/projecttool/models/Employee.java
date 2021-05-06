package com.example.projecttool.models;

import java.util.List;

public class Employee {
    int id;
    String name;
    List<Skill> skills;

    public Employee(int id, String name, List<Skill> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public Employee(String name, List<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }
}