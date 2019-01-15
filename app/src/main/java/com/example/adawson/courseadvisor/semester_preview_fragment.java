package com.example.adawson.courseadvisor;

//https://guides.codepath.com/android/Creating-and-Using-Fragments

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class semester_preview_fragment extends Fragment {

    private static final String TAG = "logging";

    public semester_preview_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_semester_preview_fragment, container, false);


        fragmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.semester_1_fragment:
                        Intent editSem1 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem1);
                        break;
                    case R.id.semester_2_fragment:
                        Intent editSem2 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem2);
                        break;
                    case R.id.semester_3_fragment:
                        // pass to it the semester id 3?
                        Intent editSem3 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem3);
                        break;
                    case R.id.semester_4_fragment:
                        Intent editSem4 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem4);
                        break;
                    case R.id.semester_5_fragment:
                        Intent editSem5 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem5);
                        break;
                    case R.id.semester_6_fragment:
                        Intent editSem6 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem6);
                        break;
                    case R.id.semester_7_fragment:
                        Intent editSem7 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem7);
                        break;
                    case R.id.semester_8_fragment:
                        Intent editSem8 = new Intent(getActivity(), SemesterEdit.class);
                        startActivity(editSem8);
                        break;
                }
            }
        });

        return fragmentView;

        /* hard coded semester 1 as sample for how it should work with the data
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
        */

        // Inflate the layout for this fragment
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