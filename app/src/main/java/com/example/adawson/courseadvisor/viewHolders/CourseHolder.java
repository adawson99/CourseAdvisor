package com.example.adawson.courseadvisor.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.adawson.courseadvisor.R;
import com.example.adawson.courseadvisor.model.Course;

public class CourseHolder extends RecyclerView.ViewHolder {

    TextView courseName;
    TextView courseID;
    TextView majorName;
    TextView creditHours;

    public CourseHolder(View courseView) {
        super(courseView);
        //add OnClickListener here...
        courseName = (TextView) courseView.findViewById(R.id.courseName);
        courseID = (TextView) courseView.findViewById(R.id.courseID);
        majorName = (TextView) courseView.findViewById(R.id.majorName);
        creditHours = (TextView) courseView.findViewById(R.id.creditHours);
    }

    public void bindCourse(Course course) {
        courseID.setText(course.getId());
        courseName.setText(course.getName());
        majorName.setText(course.getMajor());
        creditHours.setText("" + course.getCredits());
    }

}
