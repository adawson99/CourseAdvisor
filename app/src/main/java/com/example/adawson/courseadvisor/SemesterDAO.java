package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adawson.courseadvisor.model.Semester;

import java.util.List;

@Dao
public interface SemesterDAO {

    @Insert
    void insert(Semester semester);

    @Query("SELECT * FROM semester_table ORDER BY name ASC")
    LiveData<List<Semester>> getAllSemesters();


    // SOMETHING LIKE THESE WOULD PROBABLY BE IN THE USED IN THE JOINT SEMESTER
    // AND COURSE TABLE AS OPPOSED TO HERE TO GET ALL COURSES IN THE RELATED SEMESTERS

    // gets semesters preceeding and including this semester
    @Query("SELECT * FROM semester_table WHERE id <= :id")
    LiveData<List<Semester>> getAllSemestersBefore(String id);

    // gets semesters after this semester
    @Query("SELECT * FROM semester_table WHERE id > :id")
    LiveData<List<Semester>> getAllSemestersAfter(String id);
}

