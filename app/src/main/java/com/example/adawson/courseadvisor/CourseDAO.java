package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adawson.courseadvisor.model.Course;

import java.util.List;

// the data access class
@Dao
public interface CourseDAO {
    @Insert
    void insert(Course course);

    @Insert
    void insertAll(List<Course> courses);

    @Query("SELECT * FROM course_table ORDER BY id ASC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE major LIKE :majorName")
    LiveData<List<Course>> getCourseBy(String majorName);

    @Query("SELECT * FROM course_table WHERE name LIKE :name")
    LiveData<List<Course>> getCourseByName(String name);

    // counts the items in the table
    @Query("SELECT COUNT(*) from course_table")
    int countItems();

}
