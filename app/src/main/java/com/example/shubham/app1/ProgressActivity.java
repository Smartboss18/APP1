package com.example.shubham.app1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;


public class ProgressActivity extends AppCompatActivity {

    TextView animalPercent;
    TextView fruitPercent;
    TextView colorPercent;

    FirebaseFirestore db;

    String animalPoint;
    String fruitPoint;
    String colorPoint;
    String currentUser;

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        db = FirebaseFirestore.getInstance();

        animalPercent = findViewById(R.id.animalPercent);
        fruitPercent = findViewById(R.id.fruitPercent);
        colorPercent = findViewById(R.id.colorPercent);

       currentUser = SharedPreferenceUtils.getDetail("CurrentUser", this);

        animalPoint = SharedPreferenceUtils.getDetail("Animal", this);
        colorPoint = SharedPreferenceUtils.getDetail("Color", this);
        fruitPoint = SharedPreferenceUtils.getDetail("Fruit", this);

        animalPercent.setText("ANIMALS: " + animalPoint);
        fruitPercent.setText("FRUITS: " + fruitPoint);
        colorPercent.setText("COLORS: " + colorPoint);

        String currentUser = SharedPreferenceUtils.getDetail("CurrentUser", getApplicationContext());

        SharedPreferenceUtils.updateProgressInCloud(getApplicationContext(), currentUser);
    }
}


