package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "major_requirements_table")
public class majorRequirements {

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name="majorId")
    private int major;

    @ColumnInfo(name="courseId")
    private int course;

    // hhmmm?
    public majorRequirements() {
        this.major = 0;
        this.course = 0;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getMajor() { return major; }

    public void setMajor(int major) {
        this.major = major;
   }

    public int getCourse() { return course; }

    public void setCourse(int course) {
        this.course = course;
    }
}

