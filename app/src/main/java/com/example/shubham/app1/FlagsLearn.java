package com.example.shubham.app1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;

public class FlagsLearn extends Activity {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_learn);
        adapter = new RecyclerAdapter(generateArrayList(), this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void takeQuiz(View view){
        Intent intent = new Intent(getApplicationContext(), Animal_QuizActivity.class);
        startActivity(intent);
    }

    public ArrayList<Learn> generateArrayList(){
        int[] images = {R.drawable.afghanistan, R.drawable.algeria, R.drawable.angola, R.drawable.argentina, R.drawable.australia,
                R.drawable.bahamas, R.drawable.bangladesh, R.drawable.belgium, R.drawable.benin,
                R.drawable.brazil, R.drawable.burkinafaso};
        String[] names = new String[]{"Afghanistan", "Algeria", "Angola", "Argentina", "Australia", "Bahamas", "Bangladesh", "Belgium", "Benin",
                "Brazil", "Burkina Faso"};

        ArrayList<Learn> arrayList = new ArrayList<>();

        for (int i=0; i<images.length; i++){
            Learn learn = new Learn(images[i], names[i]);
            arrayList.add(learn);
        }
        return arrayList;
    }
}



















