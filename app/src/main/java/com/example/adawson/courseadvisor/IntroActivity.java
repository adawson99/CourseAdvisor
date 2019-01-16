package com.example.adawson.courseadvisor;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adawson.courseadvisor.model.Major;
import com.example.adawson.courseadvisor.model.User;

public class IntroActivity extends AppCompatActivity {
    String majorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Gets the selected major if returning from the MajorSelection activity
        Intent intent = getIntent();
        majorName = intent.getStringExtra("SELECTED_MAJOR");
        TextView selectedMajor = (TextView)findViewById(R.id.selectedMajor);

        //Updates the user_table or the display text as appropriate
        CourseDatabase db = CourseDatabase.getDatabase(getApplication());
        UserRepository userRepository = new UserRepository(getApplication());
        Cursor mcursor = db.query("SELECT * FROM user_table",null);
        if (mcursor.moveToFirst()) { //if the database already contains a user
            //update text with user's new major
            String userMajor = mcursor.getString(mcursor.getColumnIndex("major"));
            selectedMajor.setText(userMajor);
        } else {
            userRepository.insert(new User("None"));
        }

    }

    public boolean onSelectMajor(View view) {
        Intent intent = new Intent(this, MajorSelection.class);
        startActivity(intent);
        return true;
    }

    public boolean onStartButton(View view) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("SELECTED_MAJOR",majorName);
        startActivity(intent);
        return true;
    }
}
