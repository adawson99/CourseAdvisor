package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "semester_table")
public class Semester {

        @PrimaryKey(autoGenerate = true)
        private int key;

        @ColumnInfo(name = "name")
        private String name;

    public Semester(String name) {
        this.name = name;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
