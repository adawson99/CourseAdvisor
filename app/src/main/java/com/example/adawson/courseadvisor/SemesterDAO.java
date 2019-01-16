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

    // get all semesters
    @Query("SELECT * FROM semester_table ORDER BY name ASC")
    LiveData<List<Semester>> getAllSemesters();

    //gets the semester name based on the id
    @Query("SELECT name FROM semester_table WHERE id = :id")
    LiveData<String> getSemesterName(int id);

}

