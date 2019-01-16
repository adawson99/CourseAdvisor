package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Semester;

public class SemesterEdit extends AppCompatActivity {
    String msgKey = "msgKey";
    //Button button5 = (Button)findViewById(R.id.button5);
    //Button button6 = (Button)findViewById(R.id.button6);
    String hldmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_edit);
        //button5.setEnabled(false);
        //button6.setEnabled(false);
        Intent intent = getIntent();
        if (intent.getStringExtra(Keys.HLDMSG) != null) {
            hldmsg = intent.getStringExtra(Keys.HLDMSG);
            TextView v = (TextView) findViewById(R.id.currentSemester);
            String date = v.getText().toString();
            //TO DO:
        }
    }

    public void openCourseList(View view) {
        Intent intent = new Intent(this,CourseSelection.class);
        startActivity(intent);

    }
}
