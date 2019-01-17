package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
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

    //get course name by key
    @Query("SELECT name FROM course_table WHERE _key LIKE :key")
    String getCourseNameByKey(int key);

    //get course name by id
    @Query("SELECT name FROM course_table WHERE id LIKE :id")
    String getCourseNameById(String id);


    @Query("SELECT major FROM course_table WHERE id LIKE :id")
    String getCourseMajorById(String id);

    // counts the items in the table
    @Query("SELECT COUNT(*) from course_table")
    int countItems();

    //get key by courseId
    @Query("SELECT _key FROM course_table WHERE id LIKE :id")
    int getCourseKeyById(String id);

    // get credits by courseId
    @Query("SELECT credits FROM course_table WHERE id LIKE :id")
    int getCreditsByCourseId(String id);
}
