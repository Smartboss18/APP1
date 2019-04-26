package com.example.shubham.app1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;


public class ProgressActivity extends AppCompatActivity {

    TextView animalPercent, fruitPercent, colorPercent, flagPercent, roadSignPercent, symbolPercent;
    FirebaseFirestore db;
    String animalPoint, fruitPoint, colorPoint, flagPoint,currentUser, roadSignPoint, symbolPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        db = FirebaseFirestore.getInstance();

        animalPercent = findViewById(R.id.animalPercent);
        fruitPercent = findViewById(R.id.fruitPercent);
        colorPercent = findViewById(R.id.colorPercent);
        flagPercent = findViewById(R.id.flagPercent);
        roadSignPercent = findViewById(R.id.roadsignPercent);
        symbolPercent = findViewById(R.id.symbolPercentt);

       currentUser = SharedPreferenceUtils.getDetail("CurrentUser", this);

        animalPoint = SharedPreferenceUtils.getDetail("Animal", this);
        colorPoint = SharedPreferenceUtils.getDetail("Color", this);
        fruitPoint = SharedPreferenceUtils.getDetail("Fruit", this);
        flagPoint = SharedPreferenceUtils.getDetail("Flag", this);
        roadSignPoint = SharedPreferenceUtils.getDetail("RoadSign", this);
        symbolPoint = SharedPreferenceUtils.getDetail("Symbol", this);

        animalPercent.setText("ANIMALS: " + animalPoint);
        fruitPercent.setText("FRUITS: " + fruitPoint);
        colorPercent.setText("COLORS: " + colorPoint);
        flagPercent.setText("FLAGS: " + flagPoint);
        roadSignPercent.setText("ROAD SIGNS: " + roadSignPoint);
        symbolPercent.setText("SYMBOLS: " + symbolPoint);

        String currentUser = SharedPreferenceUtils.getDetail("CurrentUser", getApplicationContext());
        SharedPreferenceUtils.updateProgressInCloud(getApplicationContext(), currentUser);
    }
}


