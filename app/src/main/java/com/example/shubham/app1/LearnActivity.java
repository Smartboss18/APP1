package com.example.shubham.app1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    RecyclerAdapter adapter;

    int[] images;
    String[] names;
    String quizType;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Intent intent = getIntent();
        images = intent.getIntArrayExtra("images");
        names = intent.getStringArrayExtra("names");
        quizType = intent.getStringExtra("type");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        type = sharedPreferences.getString("Type", "");

        if (images == null || names == null || quizType == null)return;

        adapter = new RecyclerAdapter(generateArrayList(), this);

        recyclerView = findViewById(R.id.recyclerView);

        if (isTablet(getApplicationContext()) == true){
            layoutManager = new GridLayoutManager(this, 2);
        }else{
            layoutManager = new GridLayoutManager(this, 1);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void takeQuiz(View view){
        Intent intent = null;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Type", quizType);
        editor.commit();
        QuizHelper.startQuiz(getApplicationContext(), intent);
    }

    public ArrayList<Learn> generateArrayList(){

        ArrayList<Learn> arrayList = new ArrayList<>();

        for (int i=0; i<images.length; i++){
            Learn learn = new Learn(images[i], names[i]);
            arrayList.add(learn);
        }
        return arrayList;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
