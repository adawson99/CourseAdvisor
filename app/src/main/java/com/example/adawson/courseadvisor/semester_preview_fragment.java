package com.example.adawson.courseadvisor;

//https://guides.codepath.com/android/Creating-and-Using-Fragments

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Semester;

import java.util.ArrayList;
import java.util.List;


public class semester_preview_fragment extends Fragment {

    private static final String TAG = "logging";

    // list of semesters
    List<Semester> semesterList = new ArrayList<>();


    public semester_preview_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get back arguments

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //load data for each view

        //defines XML files for the fragment
        View view = inflater.inflate(R.layout.fragment_semester_preview_fragment, container, false);

        return view;
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

        /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int semesterId;
                switch (v.getId()) {
                    case R.id.semester_1_fragment:
                        Intent editSem1 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(0).getId();
                        editSem1.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem1);
                        break;
                    case R.id.semester_2_fragment:
                        Intent editSem2 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(1).getId();
                        editSem2.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem2);
                        break;
                    case R.id.semester_3_fragment:
                        // pass to it the semester id 3?
                        Intent editSem3 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(2).getId();
                        editSem3.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem3);
                        break;
                    case R.id.semester_4_fragment:
                        Intent editSem4 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(3).getId();
                        editSem4.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem4);
                        break;
                    case R.id.semester_5_fragment:
                        Intent editSem5 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(4).getId();
                        editSem5.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem5);
                        break;
                    case R.id.semester_6_fragment:
                        Intent editSem6 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(5).getId();
                        editSem6.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem6);
                        break;
                    case R.id.semester_7_fragment:
                        Intent editSem7 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(6).getId();
                        editSem7.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem7);
                        break;
                    case R.id.semester_8_fragment:
                        Intent editSem8 = new Intent(getActivity(), SemesterEdit.class);
                        semesterId = semesterList.get(7).getId();
                        editSem8.putExtra("SEMESTER_SELECTED", semesterId);
                        startActivity(editSem8);
                        break;
                }

            }
        });*/

    }


}