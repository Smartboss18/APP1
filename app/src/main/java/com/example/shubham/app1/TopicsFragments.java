package com.example.shubham.app1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
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

               Intent intent = null;
               SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("Type", "Animal");
               editor.commit();

               if (type.equals("learn")){
                   intent = new Intent(getContext(), LearnActivity.class);
                   int[] images = {R.drawable.cat, R.drawable.cow, R.drawable.dog, R.drawable.duck, R.drawable.elephant,
                           R.drawable.lion, R.drawable.tiger, R.drawable.camel, R.drawable.giraffes, R.drawable.goat,
                           R.drawable.horse, R.drawable.panda, R.drawable.sloth, R.drawable.fox, R.drawable.squirrel,
                           R.drawable.antelope, R.drawable.cheetah, R.drawable.pigeon,
                           R.drawable.rabbit, R.drawable.turtle, R.drawable.zebra};
                   String[] names = new String[]{"cat", "cow", "dog", "duck", "elephant", "lion", "tiger", "camel", "giraffe",
                           "goat", "horse", "panda", "sloth", "fox", "squirrel",
                           "antelope", "cheetah", "pigeon", "rabbit", "turtle",
                           "zebra"};
                   intent.putExtra("images", images);
                   intent.putExtra("names", names);
                   intent.putExtra("type", "Animal");
                   startActivity(intent);
                   Toast.makeText(getContext(), "Click To Play Audio", Toast.LENGTH_LONG).show();
               }
               else if (type.equals("quiz")){
                   QuizHelper.startQuiz(getContext(), intent);
               }
       }
       });

       Button topic_fruits = view.findViewById(R.id.fruits);
       topic_fruits.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = null;
               SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("Type", "Fruit");
               editor.commit();

               if (type.equals("learn")){
                   int[] images = {R.drawable.apple, R.drawable.banana, R.drawable.cherry, R.drawable.kiwi,
                           R.drawable.orange, R.drawable.pear, R.drawable.pineapple, R.drawable.strawberry, R.drawable.grapes, R.drawable.watermelon};
                   String[] names = new String[]{"Apple", "Banana", "Cherry", "Kiwi", "Orange", "Pear", "Pineapple", "Strawberry", "Grapes", "Watermelon"};
                   intent = new Intent(getContext(), LearnActivity.class);
                   intent.putExtra("images", images);
                   intent.putExtra("names", names);
                   intent.putExtra("type", "Fruit");
                   startActivity(intent);
               }
               else if (type.equals("quiz")){
                  QuizHelper.startQuiz(getContext(), intent);
               }
           }
       });

       Button topic_colors = view.findViewById(R.id.colors);
       topic_colors.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = null;
               SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("Type", "Color");
               editor.commit();

               if (type.equals("learn")){
                   intent = new Intent(getContext(), LearnActivity.class);
                   int[] images = {R.drawable.red, R.drawable.orangecolor, R.drawable.yellow, R.drawable.green,
                           R.drawable.blue,  R.drawable.indigo,R.drawable.violet};
                   String [] names = new String[]{"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
                   intent.putExtra("images", images);
                   intent.putExtra("names", names);
                   intent.putExtra("type", "Color");
                   startActivity(intent);
               }
               else if (type.equals("quiz")){
                   QuizHelper.startQuiz(getContext(), intent);
               }
           }
       });

       Button topics_flags = view
               .findViewById(R.id.flags);
       topics_flags.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = null;
               SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("Type", "Flag");
               editor.commit();

               android.view.animation.Animation aniSlide = android.view.animation.AnimationUtils.loadAnimation(getContext(),R.anim.slide_in);
               if (type.equals("learn")){
                   int[] images = {R.drawable.afghanistan, R.drawable.algeria, R.drawable.angola, R.drawable.argentina, R.drawable.australia,
                           R.drawable.bahamas, R.drawable.bangladesh, R.drawable.belgium, R.drawable.benin,
                           R.drawable.brazil, R.drawable.burkinafaso};
                   String[] names = new String[]{"Afghanistan", "Algeria", "Angola", "Argentina", "Australia", "Bahamas", "Bangladesh", "Belgium", "Benin",
                           "Brazil", "Burkina Faso"};
                   intent = new Intent(getContext(), LearnActivity.class);
                   intent.putExtra("images", images);
                   intent.putExtra("names", names);
                   intent.putExtra("type", "Flag");
                   startActivity(intent);
               }
               else if (type.equals("quiz")){
                  QuizHelper.startQuiz(getContext(), intent);
               }
           }
       });

        return view;
    }
}
