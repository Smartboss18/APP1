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
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else if (type.equals("Color")){
            int[] images = {R.drawable.red, R.drawable.orangecolor, R.drawable.yellow, R.drawable.green,
                    R.drawable.blue,  R.drawable.indigo,R.drawable.violet};
            String [] names = new String[]{"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Color");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else if (type.equals("Fruit")){
            int[] images = {R.drawable.apple, R.drawable.banana, R.drawable.cherry, R.drawable.kiwi,
                    R.drawable.orange, R.drawable.pear, R.drawable.pineapple, R.drawable.strawberry, R.drawable.grapes, R.drawable.watermelon};
            String[] names = new String[]{"Apple", "Banana", "Cherry", "Kiwi", "Orange", "Pear", "Pineapple", "Strawberry", "Grapes", "Watermelon"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "Fruit");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
            intent.putExtra("type", "Flag");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else if (type.equals("RoadSign")){
            int[] images = {R.drawable.bicycle_traffic, R.drawable.hill_warning, R.drawable.men_at_work, R.drawable.no_right_turn, R.drawable.no_left_turn,
                    R.drawable.no_parking, R.drawable.no_pedestrian, R.drawable.no_truck, R.drawable.no_uturn, R.drawable.pedestrian_crossing,
                    R.drawable.school_zone, R.drawable.slippery_road, R.drawable.speed_limit, R.drawable.two_way_sign};
            String[] names = new String[]{"Bicycle \n Traffic", "Hill Warning", "Men At \n work", "no \n right turn", "no \n left turn", "no \n parking",
                    "no \n pedestrian", "no truck", "no u-turn", "pedestrian \n crossing", "School \n zone", "slippery \n zone",
                    "speed limit", "two-way"};
            intent = new Intent(context, QuizActivity.class);
            intent.putExtra("images", images);
            intent.putExtra("names", names);
            intent.putExtra("type", "RoadSign");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
