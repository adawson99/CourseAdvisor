package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.bluetooth.BluetoothClass;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.Major;

import java.util.List;

@Dao
public interface MajorDAO {

    @Insert
    void insert(Major major);

    @Query("SELECT * FROM major_table ORDER BY id ASC")
    LiveData<List<Major>> getAllMajors();

    @Query("SELECT creditHours FROM major_table WHERE name LIKE :majorName")
    LiveData<List<Major>> getCreditHours(String majorName);
}
