package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "semester_table")
public class Semester {

        // int from 1 to 8
        @PrimaryKey(autoGenerate = false)
        private int id;

        @ColumnInfo(name = "name")
        private String name;

    public Semester(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
