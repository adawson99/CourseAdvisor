package com.example.adawson.courseadvisor;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adawson.courseadvisor.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseSelection extends AppCompatActivity {

    private CourseAdapter adapter = new CourseAdapter();

    // the database helper
    private CourseRepository courseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

       // List<Course> courses = new ArrayList<>();

        courseRepository = new CourseRepository(getApplication());
        addAllCoursesToDatabase();

       /* Course course1 = new Course("CSC 220", "Data Structures", "Computer Science", 5);
        Course course2 = new Course("CSC 250", "Theory of Computer Science", "Computer Science", 4);
        Course course3 = new Course("MUS 951", "Glee CLub", "Art", 1);

        courses.add(course1);
        courses.add(course2);
        courses.add(course3); */

        list.setAdapter(adapter);

        // live data observer
        courseRepository.getCourses().observe(
                this,
                new Observer<List<Course>>() {
                    @Override
                    public void onChanged(@Nullable List<Course> courses) {
                        adapter.setCourses(courses);
                    }
                });

    }

    public void addAllCoursesToDatabase() {
        // creates all courses
        Course course1 = new Course("CSC 220", "Data Structures", "Computer Science", 5);
        Course course2 = new Course("CSC 250", "Theory of Computer Science", "Computer Science", 4);
        Course course3 = new Course("MUS 951", "Glee CLub", "Art", 1);

        // inserts courses into the database
        // (happens again every time!)
        courseRepository.insert(course1);
        courseRepository.insert(course2);
        courseRepository.insert(course3);
    }

}
