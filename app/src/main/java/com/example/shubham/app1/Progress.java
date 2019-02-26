package com.example.shubham.app1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Progress extends AppCompatActivity {

    TextView animalPercent;
    TextView fruitPercent;
    TextView colorPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        animalPercent = findViewById(R.id.animalPercent);
        fruitPercent = findViewById(R.id.fruitPercent);
        colorPercent = findViewById(R.id.colorPercent);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String animalPoint = sharedPreferences.getString("animalPercentage","");
        String fruitPoint = sharedPreferences.getString("fruitPercentage", "");
        String colorPoint = sharedPreferences.getString("colorPercentage", "");


        animalPercent.setText("Animals:" + animalPoint);
        fruitPercent.setText("Fruit:" + fruitPoint);
        colorPercent.setText("Color:" + colorPoint);

        Log.i("Animals", animalPoint);
        Log.i("Fruit", fruitPoint);
        Log.i("Color", colorPoint);



    }
}
