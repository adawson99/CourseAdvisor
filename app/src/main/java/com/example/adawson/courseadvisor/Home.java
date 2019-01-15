package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

// changes so that it implements the semester_preview_fragment
public class Home extends AppCompatActivity {

    private static final String TAG = "logging";
    // my fragment
   semester_preview_fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fm = getSupportFragmentManager();
        // initialize the fragment semester titles, this never needs to change or update
        initializeFragmentTitles(fm);
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

    public void initializeFragmentTitles(FragmentManager fm) {
        // all the fragments
        Fragment s1 = fm.findFragmentById(R.id.semester_1_fragment);
        Fragment s2 = fm.findFragmentById(R.id.semester_2_fragment);
        Fragment s3 = fm.findFragmentById(R.id.semester_3_fragment);
        Fragment s4 = fm.findFragmentById(R.id.semester_4_fragment);
        Fragment s5 = fm.findFragmentById(R.id.semester_5_fragment);
        Fragment s6 = fm.findFragmentById(R.id.semester_6_fragment);
        Fragment s7 = fm.findFragmentById(R.id.semester_7_fragment);
        Fragment s8 = fm.findFragmentById(R.id.semester_8_fragment);

        TextView semester1Title = (TextView) s1.getView().findViewById(R.id.semesterTitle);
        semester1Title.setText(R.string.s1);

        // semester 2 title
        TextView semester2Title = (TextView) s2.getView().findViewById(R.id.semesterTitle);
        semester2Title.setText(R.string.s2);

        // semester 3 title
        TextView semester3Title = (TextView) s3.getView().findViewById(R.id.semesterTitle);
        semester3Title.setText(R.string.s3);

        // semester 4 title
        TextView semester4Title = (TextView) s4.getView().findViewById(R.id.semesterTitle);
        semester4Title.setText(R.string.s4);

        // semester 5 title
        TextView semester5Title = (TextView) s5.getView().findViewById(R.id.semesterTitle);
        semester5Title.setText(R.string.s5);

        // semester 6 title
        TextView semester6Title = (TextView) s6.getView().findViewById(R.id.semesterTitle);
        semester6Title.setText(R.string.s6);

        // semester 7 title
        TextView semester7Title = (TextView) s7.getView().findViewById(R.id.semesterTitle);
        semester7Title.setText(R.string.s7);

        // semester 8 title
        TextView semester8Title = (TextView) s8.getView().findViewById(R.id.semesterTitle);
        semester8Title.setText(R.string.s8);
    }

    }

