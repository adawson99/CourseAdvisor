package com.example.adawson.courseadvisor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adawson.courseadvisor.model.Major;

import java.util.ArrayList;
import java.util.List;

public class MajorSelection extends AppCompatActivity {

    OnMajorClickListener listener = new OnMajorClickListener() {
        @Override
        public void onItemClick(Major major) {
            onClickContinue(major);
        }
    };

    private MajorAdapter adapter = new MajorAdapter(listener);

    // list of majors
    List<Major> majors = new ArrayList<>();

    // the database helper
    private MajorRepository majorRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TestCrash","MajorSelection entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_selection);

        RecyclerView majorList = (RecyclerView) findViewById(R.id.majorList);
        majorList.setLayoutManager(new LinearLayoutManager(this));
        Log.d("TestCrash","RecyclerView initiated");

        majorRepository = new MajorRepository(getApplication());

        CourseDatabase db = CourseDatabase.getDatabase(getApplication());

        LiveData<List<Major>> existingMajors = db.majorDAO().getAllMajors();

        Log.d("TestCrash", "Database and Repository initialized");
        addAllMajorsToDatabase();
        if (existingMajors!=null) {
            addAllMajorsToDatabase();
            Log.d("TestCrash", "Majors initiated");
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

    public void addAllMajorsToDatabase() {
        Major major1 = new Major("Computer Science",44);
        Major major2 = new Major("English",42);
        Major major3 = new Major("Mathematics and Statistics",40);

        // inserts courses into the database
        // (happens again every time!)
        majorRepository.insert(major1);
        majorRepository.insert(major2);
        majorRepository.insert(major3);

    }

    public void onClickContinue(Major major) {
        Log.d("TestCrash","In onClick");
        //List list = findViewById(R.id.majorList);
        Intent intent = new Intent(this,IntroActivity.class);
        //TextView majorView = (TextView) findViewById(R.id.majorName);
        //String majorName = majorView.getText().toString();
        String majorName = major.getName();
        Log.d("TestCrash","major selected: "+majorName);
        intent.putExtra("SELECTED_MAJOR",majorName);

        startActivity(intent);
    }
}
