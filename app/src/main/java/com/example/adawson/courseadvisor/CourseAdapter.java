package com.example.adawson.courseadvisor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adawson.courseadvisor.model.Course;
import com.example.adawson.courseadvisor.viewHolders.CourseHolder;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {
    private List<Course> courses;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_view,parent,false);
        return new CourseHolder(view);
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
