package com.example.adawson.courseadvisor;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.model.CourseSelectionObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//Gives feedback
public class FeedbackActivity extends AppCompatActivity {
    CourseRepository courseRepository;
    CourseSelectionRepository courseSelectionRepository;
    private List<Course> courses = new ArrayList<>();
    UserRepository user;
    //int credits;
    //int majorcredits;
    //int othercredits;
    private static final String TAG = "logging" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Log.i(TAG, "onCreate: FeedBack Activity");


        courseRepository = new CourseRepository(getApplication());
        courseSelectionRepository = new CourseSelectionRepository(getApplication());
        user = new UserRepository(getApplication());

        courseSelectionRepository.getAllPairings().observe(
                this, new Observer<List<CourseSelectionObject>>() {
                    @Override
                    public void onChanged(@Nullable List<CourseSelectionObject> courseSelections) {
                        updateCredits(courseSelections);
                    }
                });
    }

    public void updateCredits(final List<CourseSelectionObject> courseSelections) {
        final String major = user.getMajor();
        final AtomicInteger majorcredits= new AtomicInteger(0);
        final AtomicInteger othercredits = new AtomicInteger(0);
        final AtomicInteger selectionsSeen = new AtomicInteger(0);
        for (int i = 0; i < courseSelections.size(); i++) {
            String courseID = courseSelections.get(i).getCourse();
            courseRepository.getCourseById(courseID).observe(this, new Observer<Course>() {
                @Override
                public void onChanged(@Nullable Course course) {
                    //if (major.equals(courseRepository.getMajorById(courseID))) {
                    if (major.equals(course.getMajor())) {
                        majorcredits.addAndGet(course.getCredits());
                    } else {
                        othercredits.addAndGet(course.getCredits());
                    }
                    int coursesSeen = selectionsSeen.addAndGet(1);

                    if (coursesSeen == courseSelections.size()) {
                        updateViews(majorcredits.get(),othercredits.get());
                    }
                }
            });

        }
    }

    public void updateViews(int majorcredits, int othercredits) {
        int credits = majorcredits + othercredits;
        int majorLeft = 44 - majorcredits;
        int otherLeft = 64 - othercredits;
        int totalLeft = 128 - credits;
        TextView majorCreditsDone = (TextView) findViewById(R.id.majorCreditsDone);
        TextView totalCreditsDone = (TextView) findViewById(R.id.totalCreditsDone);
        TextView outsideCreditsDone = (TextView) findViewById(R.id.outsideCreditsDone);
        TextView majorCreditsLeft = (TextView) findViewById(R.id.majorCreditsLeft);
        TextView otherCreditsLeft = (TextView) findViewById(R.id.otherCreditsLeft);
        TextView totalCreditsLeft = (TextView) findViewById(R.id.totalCreditsLeft);

        majorCreditsDone.setText("Completed: " + Integer.toString(majorcredits));
        totalCreditsDone.setText("Completed: " + Integer.toString(credits));
        outsideCreditsDone.setText("Completed: " + Integer.toString(othercredits));
        majorCreditsLeft.setText("Needed: " + Integer.toString(majorLeft));
        otherCreditsLeft.setText("Needed: " + Integer.toString(otherLeft));
        totalCreditsLeft.setText("Needed: " + Integer.toString(totalLeft));
    }

    public String findMajor (List<Course> courses, String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i).getMajor();
            }
        }
        Log.i(TAG, "findMajor: ");
        return "Error: major not found";
    }
    /**public String userMajor (UserRepository user) {
    String major;
        user.getMajor().observe(
                this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String Major) {
                        major = Major;
    }*/
}