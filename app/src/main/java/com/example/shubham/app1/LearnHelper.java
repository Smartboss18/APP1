package com.example.shubham.app1;

import android.widget.Button;

public class LearnHelper {
    Integer image;
    String name;
    Button button;

    public LearnHelper(Integer image, String name) {
        this.image = image;
        this.name = name;
    }

    public void Topics(Integer image, Button button){
        this.image = image;
        this.button = button;
    }

}

