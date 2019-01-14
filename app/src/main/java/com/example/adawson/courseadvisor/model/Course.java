package com.example.adawson.courseadvisor.model;

import java.util.HashSet;

public class Course {

    private String id;

    private String name;

   // connects the course to a major
    private String majorId;

    private int credits;

   //  applicable latin honors
   private HashSet<String> latinHonors;

    public Course(String id, String name, String majorId, int credits) {
        this.id = id;
        this.name = name;
        this.majorId = majorId;
        this.credits = credits;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getMajor() { return majorId; }

    public HashSet<String> getLatinHonors() { return latinHonors; }

    public void setLatinHonors(HashSet<String> latinHonors) {
        this.latinHonors = latinHonors;
    }

    public int getCredits() { return credits; }
}
