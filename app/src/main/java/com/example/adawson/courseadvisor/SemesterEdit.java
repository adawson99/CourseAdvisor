package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SemesterEdit extends AppCompatActivity {
    String msgKey = "msgKey";
    Button button3 = (Button)findViewById(R.id.button3);
    Button button4 = (Button)findViewById(R.id.button4);
    Button button5 = (Button)findViewById(R.id.button5);
    Button button6 = (Button)findViewById(R.id.button6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_edit);
    }

    public void openCourseList(View view) {
        TextView v = (TextView) findViewById(R.id.currentSemester);
        String message = v.getText().toString();
        Intent intent = new Intent(this,CourseSelection.class);
        intent.putExtra(msgKey,message);
        startActivity(intent);
    }
}
