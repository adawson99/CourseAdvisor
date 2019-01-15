package com.example.adawson.courseadvisor.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.CourseSelection;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseSelectionDAO {

    @Insert
    void insert (CourseSelection courseSelection);

    //select courses based on the semester id
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId = :id")
    int[] getThisSemesterCourses(int id);

    @Query("SELECT semesterId FROM course_selection_table WHERE semesterId = :id")
    int getSemesterId(int id);

    // gets all the courses select
    @Query("SELECT * FROM course_selection_table")
    LiveData<List<CourseSelection>> getTotalCourses();

    // gets course ids preceding and including this semester
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId <= :id")
    int getAllCoursesBefore(int id);

    // gets course ids after this semester
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId > :id")
    int getAllCoursesAfter(int id);


}

