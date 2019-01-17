package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.bluetooth.BluetoothClass;

import com.example.adawson.courseadvisor.model.Major;
import com.example.adawson.courseadvisor.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Query("UPDATE user_table SET major = :majorName")
    void update(String majorName);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT major FROM user_table")
    String getMajor();
}
