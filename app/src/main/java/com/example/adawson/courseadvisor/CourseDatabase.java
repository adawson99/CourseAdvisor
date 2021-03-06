package com.example.adawson.courseadvisor;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.adawson.courseadvisor.Dao.CourseSelectionDAO;
import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.Semester;
import com.example.adawson.courseadvisor.model.Major;
import com.example.adawson.courseadvisor.model.User;
import com.example.adawson.courseadvisor.model.CourseSelectionObject;
import com.example.adawson.courseadvisor.model.majorRequirements;

@Database(entities = {Course.class, Semester.class, Major.class, CourseSelectionObject.class, User.class, majorRequirements.class}, version = 1, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();
    public abstract MajorDAO majorDAO();
    public abstract SemesterDAO semesterDAO();
    public abstract UserDAO userDAO();
    public abstract CourseSelectionDAO courseSelectionDAO();

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
