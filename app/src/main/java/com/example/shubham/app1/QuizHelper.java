package com.example.shubham.app1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class QuizHelper {

    public static void startQuiz(Context context, Intent intent){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String type  = sharedPreferences.getString("Type", "");

        if (type.equals("Animal")) {
            int[] images = {R.drawable.cat, R.drawable.cow, R.drawable.dog, R.drawable.duck, R.drawable.elephant,
                    R.drawable.lion, R.drawable.tiger, R.drawable.camel, R.drawable.giraffes, R.drawable.goat,
                    R.drawable.horse, R.drawable.panda, R.drawable.sloth, R.drawable.fox, R.drawable.squirrel,
                    R.drawable.antelope, R.drawable.cheetah, R.drawable.pigeon,
                    R.drawable.rabbit, R.drawable.turtle, R.drawable.zebra};
            String[] names = new String[]{"cat", "cow", "dog", "duck", "elephant", "lion", "tiger", "camel", "giraffe",
                    "goat", "horse", "panda", "sloth", "fox", "squirrel",
                    "antelope", "cheetah", "pigeon", "rabbit", "turtle",
                    "zebra"};

            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Animal");
            context.startActivity(intent);

        }else if (type.equals("Color")){
            int[] images = {R.drawable.red, R.drawable.orangecolor, R.drawable.yellow, R.drawable.green,
                    R.drawable.blue,  R.drawable.indigo,R.drawable.violet};
            String [] names = new String[]{"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Color");
            context.startActivity(intent);

        }else if (type.equals("Fruit")){
            int[] images = {R.drawable.apple, R.drawable.banana, R.drawable.cherry, R.drawable.kiwi,
                    R.drawable.orange, R.drawable.pear, R.drawable.pineapple, R.drawable.strawberry, R.drawable.grapes, R.drawable.watermelon};
            String[] names = new String[]{"Apple", "Banana", "Cherry", "Kiwi", "Orange", "Pear", "Pineapple", "Strawberry", "Grapes", "Watermelon"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Fruit");
            context.startActivity(intent);

        }else if (type.equals("Flag")){
            int[] images = {R.drawable.afghanistan, R.drawable.algeria, R.drawable.angola, R.drawable.argentina, R.drawable.australia,
                    R.drawable.bahamas, R.drawable.bangladesh, R.drawable.belgium, R.drawable.benin,
                    R.drawable.brazil, R.drawable.burkinafaso};
            String[] names = new String[]{"Afghanistan", "Algeria", "Angola", "Argentina", "Australia", "Bahamas", "Bangladesh", "Belgium", "Benin",
                    "Brazil", "Burkina Faso"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Flags");
            context.startActivity(intent);
        }

    }
}
