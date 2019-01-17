package com.example.adawson.courseadvisor.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.adawson.courseadvisor.model.CourseSelectionObject;

import java.util.List;

@Dao
public interface CourseSelectionDAO {

    @Insert
    void insert (CourseSelectionObject courseSelectionObject);

    //select courses based on the semester id
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId = :id")
    LiveData<List<String>> getThisSemesterCourses(int id);
    //integer

    @Query("SELECT * FROM course_selection_table ORDER BY _id ASC")
    LiveData<List<CourseSelectionObject>> getAllPairings();

    // for editing the courses in the Course Selection table
    // get the id of the semester with this semester and this course
    @Query("SELECT _id FROM course_selection_table WHERE semesterId = :semesterId AND courseId = :courseId")
    LiveData<Integer> getIdforCourseSelection(int semesterId, int courseId);

    //replace an existing course with another course
    @Query("UPDATE course_selection_table SET courseId = :courseId WHERE _id = :id")
    void replaceCourse(String courseId, int id);

    // gets all the courses select
    @Query("SELECT * FROM course_selection_table")
    LiveData<List<CourseSelectionObject>> getTotalCourses();

    // gets course ids preceding and including this semester
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId <= :id")
    LiveData<List<String>> getAllCoursesBefore(int id);
    //integer

    // gets course ids after this semester
    @Query("SELECT courseId FROM course_selection_table WHERE semesterId > :id")
    LiveData<List<String>> getAllCoursesAfter(int id);

    //integer


}

