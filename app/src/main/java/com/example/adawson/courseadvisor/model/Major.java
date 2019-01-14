package com.example.adawson.courseadvisor.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "major_table")
public class Major {

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "creditHours")
    private int creditHours;

    public Major(String name, int creditHours) {
        this.name = name;
        this.creditHours = creditHours;
    }

    // getters and setters for the auto generated id for each major object
    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
