package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adawson.courseadvisor.model.Major;

import java.util.ArrayList;
import java.util.List;

public class MajorSelection extends AppCompatActivity {

    //Listener that does something if a major is clicked
    OnMajorClickListener listener = new OnMajorClickListener() {
        @Override
        public void onItemClick(Major major) {
            onClickContinue(major);
        }
    };

    public void onClickContinue(Major major) {
        Intent intent = new Intent(this,IntroActivity.class);
        String majorName = major.getName();

        //Updates the major field in the user table with the major just chosen
        UserRepository userRepository = new UserRepository(getApplication());
        userRepository.update(majorName);

        //passes the name of the major as a string (for display purposes)
        //may be unecessary; can get from user_table now
        intent.putExtra("SELECTED_MAJOR",majorName);
        startActivity(intent);
    }

    private MajorAdapter adapter = new MajorAdapter(listener);

    // list of majors
    List<Major> majors = new ArrayList<>();

    // the database helper
    private MajorRepository majorRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_selection);

        RecyclerView majorList = (RecyclerView) findViewById(R.id.majorList);
        majorList.setLayoutManager(new LinearLayoutManager(this));

        majorRepository = new MajorRepository(getApplication());
        CourseDatabase db = CourseDatabase.getDatabase(getApplication());

        Boolean dbEmpty = true;
        Cursor mcursor = db.query("SELECT * FROM major_table",null);

        if (mcursor.moveToFirst()) { //the database already contains information
            dbEmpty = false;
        }

        //if the major_table is empty, add all the majors to the database
        if (dbEmpty) {
            addAllMajorsToDatabase();
        }

        majorList.setAdapter(adapter);

        // live data observer
        majorRepository.getMajors().observe(
                this,
                new Observer<List<Major>>() {
                    @Override
                    public void onChanged(@Nullable List<Major> majors) {
                        adapter.setMajors(majors);
                    }
                });

    }

    //Propogates the major_table
    public void addAllMajorsToDatabase() {
        Major major1 = new Major("Computer Science",44);
        Major major2 = new Major("English",42);
        Major major3 = new Major("Mathematics and Statistics",40);

        majorRepository.insert(major1);
        majorRepository.insert(major2);
        majorRepository.insert(major3);

    }
}
