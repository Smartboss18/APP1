package com.example.shubham.app1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Progress extends AppCompatActivity {

    TextView animalPercent;
    TextView fruitPercent;
    TextView colorPercent;

    String animalPoint;
    String fruitPoint;
    String colorPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        animalPercent = findViewById(R.id.animalPercent);
        fruitPercent = findViewById(R.id.fruitPercent);
        colorPercent = findViewById(R.id.colorPercent);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        animalPoint = sharedPreferences.getString("animalPercentage","");
        fruitPoint = sharedPreferences.getString("fruitPercentage", "");
        colorPoint = sharedPreferences.getString("colorPercentage", "");


        animalPercent.setText("Animals:" + animalPoint);
        fruitPercent.setText("Fruit:" + fruitPoint);
        colorPercent.setText("Color:" + colorPoint);

        Log.i("Animals", animalPoint);
        Log.i("Fruit", fruitPoint);
        Log.i("Color", colorPoint);

    }

}
