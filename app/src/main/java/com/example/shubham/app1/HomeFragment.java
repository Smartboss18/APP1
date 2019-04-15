package com.example.shubham.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("DDDD", "fragment");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button learnButton = view.findViewById(R.id.learn);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                TopicsFragments topicsFragments = new TopicsFragments();
                topicsFragments.setType("learn");
                ft.setCustomAnimations(R.anim.slide_in ,R.anim.slide_out);
                ft.add(R.id.fragment,topicsFragments);
                ft.addToBackStack(null);
                ft.commit();


            }
        });

        final Button quizButton = view.findViewById(R.id.quiz);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopicsFragments topicsFragments = new TopicsFragments();
                topicsFragments.setType("quiz");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction quizFragment = fragmentManager.beginTransaction();
                quizFragment.setCustomAnimations(R.anim.slide_in ,R.anim.slide_out);
                quizFragment.add(R.id.fragment, topicsFragments);
                quizFragment.addToBackStack(null);
                quizFragment.commit();
            }
        });


        Button progressButton = view.findViewById(R.id.progress);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent progress = new Intent(getContext(), ProgressActivity.class);
                startActivity(progress);
            }
        });

        return view;
    }
}
