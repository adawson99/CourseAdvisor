package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.HashSet;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int key;
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "name")
    private String name;

   // connects the course to a major
    @ColumnInfo(name = "major")
    private String major;

    @ColumnInfo(name = "credits")
    private int credits;

   //  applicable latin honors
  // private HashSet<String> latinHonors;

    public Course(String id, String name, String major, int credits) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.credits = credits;
    }

    public int getKey() { return key; }

    public void setKey(int key) {this.key = key; }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getMajor() { return major; }

    //public HashSet<String> getLatinHonors() { return latinHonors; }

   /* public void setLatinHonors(HashSet<String> latinHonors) {
        this.latinHonors = latinHonors;
    }
*/
    public int getCredits() { return credits; }
}
