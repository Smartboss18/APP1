package com.example.shubham.app1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Animals_Learn extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals__learn);
        adapter = new RecyclerAdapter(generateArrayList(), this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 1);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                switch(adapter.getItemViewType(position)) {
//                    case 0:
//                        return 1;
//                    case 1:
//                        return 2;
//                    default:
//                        return 1;
//                }
//            }
//        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void takeQuiz(View view){
        Intent intent = new Intent(getApplicationContext(), Animal_QuizActivity.class);
        startActivity(intent);
    }

    public ArrayList<Learn> generateArrayList(){
        int[] images = {R.drawable.cat, R.drawable.cow, R.drawable.dog, R.drawable.duck, R.drawable.elephant,
                        R.drawable.lion, R.drawable.tiger, R.drawable.camel, R.drawable.giraffes, R.drawable.goat,
                        R.drawable.horse, R.drawable.panda};
        String[] names = {"cat", "cow", "dog", "duck", "elephant", "lion", "tiger", "camel", "giraffe", "goat", "horse", "panda"};

        ArrayList<Learn> arrayList = new ArrayList<>();

        for (int i=0; i<images.length; i++){
            Learn learn = new Learn(images[i], names[i]);
            arrayList.add(learn);
        }
        return arrayList;
    }
}
