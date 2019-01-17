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

//Gives feedback
public class FeedbackActivity extends AppCompatActivity {
    CourseRepository courseRepository;
    CourseSelectionRepository courseSelectionRepository;
    private List<CourseSelectionObject> courseSelectionList = new ArrayList<>();
    UserRepository user;
    int credits;
    int majorcredits;
    int othercredits;
    //TextView totalCreditsDone;
    //TextView majorCreditsDone;
    //TextView outsideCreditsDone;
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
                        courseSelectionList = courseSelections;
                        updateCredits(courseSelectionList);
                    }
                });
    }

    public void updateCredits(List<CourseSelectionObject> courseSelections) {
        String major = user.getMajor();
        for (int i = 0; i < courseSelections.size(); i++) {
            String courseID = courseSelections.get(i).getCourse();
            credits = credits + 4;
            if (major.equals(courseRepository.getMajorById(courseID))) {
                majorcredits = majorcredits + 4;
            } else {
                othercredits = othercredits + 4;
            }
        }
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

}