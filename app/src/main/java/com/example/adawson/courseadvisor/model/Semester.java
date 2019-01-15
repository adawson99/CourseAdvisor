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

        @ColumnInfo(name = "creditHours")
        private int creditHours;

    public Semester(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getName() { return name; }

    public int getId() { return id; }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
}
