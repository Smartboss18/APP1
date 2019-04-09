package com.example.shubham.app1;

import android.widget.Button;

public class Learn {
    Integer image;
    String name;
    Button button;

    public Learn(Integer image, String name) {
        this.image = image;
        this.name = name;
    }

    public void Topics(Integer image, Button button){
        this.image = image;
        this.button = button;
    }

}

