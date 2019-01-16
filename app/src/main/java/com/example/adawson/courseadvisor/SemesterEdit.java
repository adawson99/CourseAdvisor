package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Semester;

public class SemesterEdit extends AppCompatActivity {

    private static final String TAG = "logging";
    String msgKey = "msgKey";
    //Button button5 = (Button)findViewById(R.id.button5);
    //Button button6 = (Button)findViewById(R.id.button6);
    String hldmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_edit);

        Intent intent = getIntent();
        int currentSemester = intent.getIntExtra(Home.SEMESTER_SELECTED, 0);
        Log.i(TAG, currentSemester + " the string passed");

        TextView semesterTitle = findViewById(R.id.currentSemester);
        semesterTitle.setText(currentSemester + "");
    }

    public void openCourseList(View view) {
        TextView v = (TextView) findViewById(R.id.currentSemester);
        String message = v.getText().toString();
        Intent intent = new Intent(this,CourseSelection.class);
        intent.putExtra(msgKey,message);
        startActivity(intent);

    }

}
