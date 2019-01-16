package com.example.adawson.courseadvisor.viewHolders;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.adawson.courseadvisor.Keys;
import com.example.adawson.courseadvisor.R;
import com.example.adawson.courseadvisor.SemesterEdit;
import com.example.adawson.courseadvisor.model.Course;

public class CourseHolder extends RecyclerView.ViewHolder {

    TextView courseName;
    TextView courseID;
    TextView majorName;
    TextView creditHours;

    int currentSemester;

    private static final String TAG = "logging";


    public CourseHolder(View courseView) {
        super(courseView);
        //add OnClickListener here...
        courseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                doClick(v);
            }
        });
        courseName = (TextView) courseView.findViewById(R.id.courseName);
        courseID = (TextView) courseView.findViewById(R.id.courseID);
        majorName = (TextView) courseView.findViewById(R.id.majorName);
        creditHours = (TextView) courseView.findViewById(R.id.creditHours);

        Intent intent = new Intent();
        currentSemester = intent.getIntExtra(Keys.SEMESTER_SELECTED, 0);
        Log.i(TAG, currentSemester + " current semester in CourseHolder");

    }

    public void bindCourse(Course course) {
        courseID.setText(course.getId());
        courseName.setText(course.getName());
        majorName.setText(course.getMajor());
        creditHours.setText("" + course.getCredits());

    }
    private void doClick(View courseView) {

        Intent intent = new Intent();

       // Intent intent = new Intent(courseView.getContext(),SemesterEdit.class);
       String message = courseID.getText().toString();
        //intent.putExtra(Keys.HLDMSG,message);
       // intent.putExtra(Keys.HLDMSG, message);
        ((Activity)courseView.getContext()).setResult(Activity.RESULT_OK, intent.putExtra(Keys.HLDMSG, message));
        ((Activity)courseView.getContext()).finish();

       // Log.i(TAG, "sending " + currentSemester + " back to SemesterEdit");
       // courseView.getContext().startActivity(intent);
       // courseView.finish();

    }
}
