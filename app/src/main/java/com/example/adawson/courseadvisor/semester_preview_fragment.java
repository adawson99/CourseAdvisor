package com.example.adawson.courseadvisor;

//https://guides.codepath.com/android/Creating-and-Using-Fragments

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Semester;


public class semester_preview_fragment extends Fragment {

    private static final String TAG = "logging";

    public semester_preview_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_semester_preview_fragment, container, false);

        // hard coded semester 1 as sample for how it should work with the data
        TextView semesterTitle = fragmentView.findViewById(R.id.semesterTitle);
        semesterTitle.setText("Semester 1");
        TextView course1 =  fragmentView.findViewById(R.id.course1);
        course1.setText("Course #1");
        TextView course2 = fragmentView.findViewById(R.id.course2);
        course2.setText("Course #2");
        TextView course3 = fragmentView.findViewById(R.id.course3);
        course3.setText("Course #3");
        TextView course4 = fragmentView.findViewById(R.id.course4);
        course4.setText("Course #4");
        TextView creditHours = fragmentView.findViewById(R.id.creditHours);
        creditHours.setText("16");

        // how do we get the different instances?
        //do we get all the semester ids and then cycle through, and put in the course titles that are oonnected to it
        // and its credit hours (that get updated when courses are added to it in the joint table)

        // Inflate the layout for this fragment
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    // triggers after onCreateView()
    // any view setup should occur here (lookups, attaching view listeners)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setup any handles to view objects here
        // a new adapter is created in on create and this is
        // set as the view's adapter here
    }


}