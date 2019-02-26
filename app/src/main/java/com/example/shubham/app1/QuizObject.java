package com.example.shubham.app1;

import java.util.ArrayList;

public class QuizObject {

    Integer image;
    String name;
    ArrayList<String> allAnswers;

    public QuizObject() {

    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(ArrayList<String> wrongAns) {
        this.allAnswers = wrongAns;
    }
}