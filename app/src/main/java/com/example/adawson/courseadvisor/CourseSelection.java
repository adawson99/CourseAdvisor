package com.example.adawson.courseadvisor;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.CourseSelectionObject;

import java.util.ArrayList;
import java.util.List;

public class CourseSelection extends AppCompatActivity {
    private static final String TAG = "logging";
    String message;
    private CourseAdapter adapter = new CourseAdapter();

    // list of courses
    List<Course> courses = new ArrayList<>();

    // the database helper
    private CourseRepository courseRepository;
    private CourseSelectionRepository courseSelectionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        // initialize courseSelectionRepository
        courseSelectionRepository = new CourseSelectionRepository(getApplication());
        courseRepository = new CourseRepository(getApplication());

        list.setAdapter(adapter);

        // live data observer for getting courses
        courseRepository.getCourses().observe(
                this,
                new Observer<List<Course>>() {
                    @Override
                    public void onChanged(@Nullable List<Course> courses) {
                        adapter.setCourses(courses);
                    }
                });

        // insert courses into database once
        CourseDatabase db = CourseDatabase.getDatabase(getApplication());
        Boolean dbEmpty = true;
        Cursor mcursor = db.query("SELECT * FROM course_table", null);
        if (mcursor.moveToFirst()) {
            dbEmpty = false;
        }
        if (dbEmpty) {
            addAllCoursesToDatabase();
        }

    }

    public void addAllCoursesToDatabase() {
        // creates all courses
        Course course1 = new Course("CSC 220", "Data Structures", "Computer Science", 5);
        Course course2 = new Course("CSC 250", "Theory of Computer Science", "Computer Science", 4);
        Course course3 = new Course("MUS 951", "Glee CLub", "Art", 1);
        Course c1 = new Course("CSC 100", "Test CS Level 1", "Computer Science",
                4);
        Course c2 = new Course("CSC 200", "Test CS Level 2", "Computer Science",
                4);
        Course c3 = new Course("CSC 300", "Test CS Level 3", "Computer Science",
                4);
        Course c4 = new Course("CSC 400", "Test CS Level 4", "Computer Science",
                4);

        // inserts courses into the database
        courseRepository.insert(course1);
        courseRepository.insert(course2);
        courseRepository.insert(course3);
        courseRepository.insert(c1);
        courseRepository.insert(c2);
        courseRepository.insert(c3);
        courseRepository.insert(c4);
    }

}
