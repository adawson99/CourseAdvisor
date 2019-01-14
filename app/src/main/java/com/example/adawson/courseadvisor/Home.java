package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // handler for checkProgress button that triggers information/feedback activity
    public void checkProgress(View view) {
        // will connect to the feedback activity
     // Intent intent = new Intent(this, feedback.class);
        Intent intent = new Intent(this, CourseSelection.class);
      startActivity(intent);
    }
}
