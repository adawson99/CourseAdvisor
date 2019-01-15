package com.example.adawson.courseadvisor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adawson.courseadvisor.model.Major;
import com.example.adawson.courseadvisor.viewHolders.MajorHolder;

import java.util.ArrayList;
import java.util.List;
//Rachel was here!!
public class MajorAdapter extends RecyclerView.Adapter<MajorHolder> {
    private List<Major> majors;

    private final OnMajorClickListener listener;

    public MajorAdapter(OnMajorClickListener listener) {
        this.listener = listener;
        this.majors = new ArrayList<Major>();
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
        notifyDataSetChanged();
    }

    @Override
    public MajorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.major_view,parent,false);
        return new MajorHolder(view);
    }

    @Override
    public void onBindViewHolder(MajorHolder holder, int position) {
        holder.bindMajor(majors.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return majors.size();
    }
}
