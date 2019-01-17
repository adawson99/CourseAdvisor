package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "course_selection_table")
public class CourseSelectionObject {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name="semesterId")
    private int semester;

    @ColumnInfo(name="courseId")
    private String course;

    // hhmmm?
    public CourseSelectionObject(int semester, String course) {
        this.semester = semester;
        this.course = course;
    }

    public int getId() { return _id; }

    public void setId(int _id) { this._id = _id; }

    public int getSemester() { return semester; }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourse() { return course; }

    public void setCourse(String course) {
        this.course = course;
    }

    }