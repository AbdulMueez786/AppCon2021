package com.example.appcon_2021.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.content.Intent;
import com.example.appcon_2021.Activity.GoalDetail;
import com.example.appcon_2021.Activity.Home;
import com.example.appcon_2021.Activity.Login;

import com.example.appcon_2021.Activity.NewGoal;
import com.example.appcon_2021.R;
public class AllGoals extends Fragment {


    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_all_goals, container, false);

        TextView start=view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewGoal.class);
                ((Home)getActivity()).startActivity(intent);
            }
        });

        return view;

    }
}