package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "course_selection_table")
public class CourseSelection {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name="semesterId")
    private int semester;

    @ColumnInfo(name="courseId")
    private int course;

    // hhmmm?
    public CourseSelection(int semester, int course) {
        this.semester = semester;
        this.course = course;
    }

    public int getId() { return _id; }

    public void setId(int _id) { this._id = _id; }

    public int getSemester() { return semester; }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCourse() { return course; }

    public void setCourse(int course) {
        this.course = course;
    }

    }