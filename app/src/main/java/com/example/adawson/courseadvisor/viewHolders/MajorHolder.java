package com.example.adawson.courseadvisor.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.adawson.courseadvisor.MajorAdapter;
import com.example.adawson.courseadvisor.OnMajorClickListener;
import com.example.adawson.courseadvisor.R;
import com.example.adawson.courseadvisor.model.Major;

public class MajorHolder extends RecyclerView.ViewHolder {
    TextView majorName;
    View majorView;

    public MajorHolder(View majorView) {
        super(majorView);
        //add OnClickListener here...
        majorName = (TextView) majorView.findViewById(R.id.majorName);
        this.majorView = majorView;
    }

    public void bindMajor(final Major major, final OnMajorClickListener listener) {
        majorName.setText(major.getName());
        majorView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onItemClick(major);
            }
        });
    }

}
