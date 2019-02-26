package com.example.shubham.app1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void learn(View view){

    }

    public void quiz(View view){
        Intent learn = new Intent(this, Animal_QuizActivity.class);
        startActivity(learn);
    }

    public  void progress(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment,new HomeFragment());
        ft.commit();

    }
}
