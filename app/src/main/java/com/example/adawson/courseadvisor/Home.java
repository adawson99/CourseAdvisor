package com.example.adawson.courseadvisor;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Semester;

import java.util.ArrayList;
import java.util.List;

// changes so that it implements the semester_preview_fragment
public class Home extends AppCompatActivity {

    View view1, view2, view3, view4, view5, view6, view7, view8;
    Intent editSem;
    int semesterId;

    Activity activity = this;

    public static final String SEMESTER_SELECTED = "SEMESTER_SELECTED";
    private static final int REQUEST_CODE = 1001;

    private static final String TAG = "logging" ;

    // for editing the fragment
    private FragmentManager fm = getSupportFragmentManager();

    // list of semesters
    List<Semester> semesterList = new ArrayList<>();

   // the semester repository
    private SemesterRepository semesterRepository;
    private CourseSelectionRepository courseSelectionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editSem = new Intent(activity, SemesterEdit.class);

        setListenersForAllFragments();

        CourseDatabase db = CourseDatabase.getDatabase(getApplication());

        // initialize semesterRepository
        semesterRepository = new SemesterRepository(getApplication());
        // initialize courseSelectionRepository
        courseSelectionRepository = new CourseSelectionRepository(getApplication());

        semesterRepository.getAllSemesters().observe(
                this,
                new Observer<List<Semester>>() {
                    @Override
                    public void onChanged(@Nullable List<Semester> semesters) {
                        semesterList = semesters;
                        setTitles(fm);
                    }
                });

        Boolean dbEmpty = true;
        //loads the semester templates if first time running
       Cursor mcursor = db.query("SELECT * FROM semester_table", null);
        if (mcursor.moveToFirst()) {
            dbEmpty = false;
        }
        if (dbEmpty) {
            populateSemesters();
            Log.i(TAG, "populating the semesters");
        } else {
            // in progress
            Log.i(TAG, "there are semester, so load them into view");
        }

    }


    /* handler for checkProgress button that
     * triggers information/feedback activity
      */
    public void checkProgress(View view) {
        // will connect to the feedback activity
     // Intent intent = new Intent(this, feedback.class);
        Intent intent = new Intent(this, FeedbackActivity.class);
      startActivity(intent);
    }


    public void populateSemesters() {
        Semester s1 = new Semester(1, "Semester 1");
        Semester s2 = new Semester(2, "Semester 2");
        Semester s3 = new Semester(3, "Semester 3");
        Semester s4 = new Semester(4, "Semester 4");
        Semester s5 = new Semester(5, "Semester 5");
        Semester s6 = new Semester(6, "Semester 6");
        Semester s7 = new Semester(7, "Semester 7");
        Semester s8 = new Semester(8, "Semester 8");
        semesterRepository.insert(s1);
        semesterRepository.insert(s2);
        semesterRepository.insert(s3);
        semesterRepository.insert(s4);
        semesterRepository.insert(s5);
        semesterRepository.insert(s6);
        semesterRepository.insert(s7);
        semesterRepository.insert(s8);
        finish();
    }

    public void setTitles(FragmentManager fm) {

        Fragment s1 = fm.findFragmentById(R.id.semester_1_fragment);
        Fragment s2 = fm.findFragmentById(R.id.semester_2_fragment);
        Fragment s3 = fm.findFragmentById(R.id.semester_3_fragment);
        Fragment s4 = fm.findFragmentById(R.id.semester_4_fragment);
        Fragment s5 = fm.findFragmentById(R.id.semester_5_fragment);
        Fragment s6 = fm.findFragmentById(R.id.semester_6_fragment);
        Fragment s7 = fm.findFragmentById(R.id.semester_7_fragment);
        Fragment s8 = fm.findFragmentById(R.id.semester_8_fragment);


        TextView semester1Title = (TextView) s1.getView().findViewById(R.id.semesterTitle);
        semester1Title.setText(semesterList.get(0).getName());

        // semester 2 title
        TextView semester2Title = (TextView) s2.getView().findViewById(R.id.semesterTitle);
        semester2Title.setText(semesterList.get(1).getName());

        // semester 3 title
        TextView semester3Title = (TextView) s3.getView().findViewById(R.id.semesterTitle);
        semester3Title.setText(semesterList.get(2).getName());

        // semester 4 title
        TextView semester4Title = (TextView) s4.getView().findViewById(R.id.semesterTitle);
        semester4Title.setText(semesterList.get(3).getName());

        // semester 5 title
        TextView semester5Title = (TextView) s5.getView().findViewById(R.id.semesterTitle);
        semester5Title.setText(semesterList.get(4).getName());

        // semester 6 title
        TextView semester6Title = (TextView) s6.getView().findViewById(R.id.semesterTitle);
        semester6Title.setText(semesterList.get(5).getName());

        // semester 7 title
        TextView semester7Title = (TextView) s7.getView().findViewById(R.id.semesterTitle);
        semester7Title.setText(semesterList.get(6).getName());

        // semester 8 title
        TextView semester8Title = (TextView) s8.getView().findViewById(R.id.semesterTitle);
        semester8Title.setText(semesterList.get(7).getName());
    }

    public void setListenersForAllFragments() {

        view1 = findViewById(R.id.fragment_container);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                semesterId = semesterList.get(0).getId();
                Log.i(TAG, "The string to pass " + semesterId);
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view2 = findViewById(R.id.fragment__2_container);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(1).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view3 = findViewById(R.id.fragment__3_container);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(2).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view4 = findViewById(R.id.fragment__4_container);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(3).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view5 = findViewById(R.id.fragment__5_container);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(4).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view6 = findViewById(R.id.fragment__6_container);
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(5).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view7 = findViewById(R.id.fragment__7_container);
        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(6).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });

        view8 = findViewById(R.id.fragment__8_container);
        view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "on click triggered");
                //Intent editSem1 = new Intent(activity, SemesterEdit.class);
                semesterId = semesterList.get(7).getId();
                editSem.putExtra(SEMESTER_SELECTED, semesterId);
                startActivity(editSem);
            }
        });


    }


}

