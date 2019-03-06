package com.example.shubham.app1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TopicsFragments extends Fragment {
    private String type;

    public TopicsFragments() {
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topics, container, false);


       Button topic_animals = view.findViewById(R.id.animals);
       topic_animals.setOnClickListener(new View.OnClickListener() {
           @Override

           public void onClick(View view) {
               Intent intent;
               if (type.equals("learn")){
                   intent = new Intent(getContext(), Animals_Learn.class);
                   startActivity(intent);
                   Toast.makeText(getContext(), "Click To Play Audio", Toast.LENGTH_LONG).show();
               }
               else if (type.equals("quiz")){

                   intent = new Intent(getContext(), Animal_QuizActivity.class);
                   startActivity(intent);

               }
       }
       });

       Button topic_fruits = view.findViewById(R.id.fruits);
       topic_fruits.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent;
               android.view.animation.Animation aniSlide = android.view.animation.AnimationUtils.loadAnimation(getContext(),R.anim.slide_in);
               if (type.equals("learn")){
                   intent = new Intent(getContext(), Fruits_Learn.class);
                   startActivity(intent);

               }
               else if (type.equals("quiz")){
                   intent = new Intent(getContext(), Fruits_QuizActivity.class);
                   startActivity(intent);
               }
           }
       });

       Button topic_colors = view.findViewById(R.id.colors);
       topic_colors.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent;
               if (type.equals("learn")){
                   intent = new Intent(getContext(), Colors_Learn.class);
                   startActivity(intent);

               }
               else if (type.equals("quiz")){
                   intent = new Intent(getContext(), Color_QuizActivity.class);
                   startActivity(intent);
               }
           }
       });

        return view;
    }
}
