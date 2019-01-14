package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "major")
    private String major;

    public User(String major) {
        this.major = major;
    }

    public int getKey() { return key; }

    public void setKey(int key) {this.key = key; }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }
}
