package com.example.adawson.courseadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Major;

public class IntroActivity extends AppCompatActivity {
    String majorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Intent intent = getIntent();
        majorName = intent.getStringExtra("SELECTED_MAJOR");
        TextView selectedMajor = (TextView)findViewById(R.id.selectedMajor);
        selectedMajor.setText(majorName);
    }

    public boolean onSelectMajor(View view) {
        Log.d("TestCrash","in onClick for Select Major button");
        //Button button = (Button) findViewById(R.id.selectMajor);
        Intent intent = new Intent(this, MajorSelection.class);
        startActivity(intent);
        Log.d("TestCrash","other activity started");
        return true;
    }

    public boolean onStartButton(View view) {
        Button button = (Button) findViewById(R.id.startButton);
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("SELECTED_MAJOR",majorName);
        startActivity(intent);
        return true;
    }
}
