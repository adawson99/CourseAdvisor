package com.example.adawson.courseadvisor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.viewHolders.CourseHolder;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {
    private List<Course> courses;
    private String oldCourseId;

    public CourseAdapter() {
        this.courses = new ArrayList<Course>();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public void setOldCourseId(String oldCourseId) {
        this.oldCourseId = oldCourseId;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_view,parent,false);
        CourseHolder ch = new CourseHolder(view);
        //ch.setOldCourseId(oldCourseId);
        return ch;
    }

    @Override
    public void onBindViewHolder(CourseHolder holder, int position) {
        holder.bindCourse(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
