package com.example.adawson.courseadvisor;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.adawson.courseadvisor.model.Course;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();

    // instance of app database class
    private static volatile CourseDatabase INSTANCE;

    // singleton that returns the same reference of the database
    // the database can only be created once
    static CourseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabase.class,
                            "course_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
