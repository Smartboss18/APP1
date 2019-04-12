package com.example.shubham.app1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fruits_QuizActivity extends AppCompatActivity {

    TextView question, score, timer, outcome, finalPoint, wrongPoints, finalPercentage;

    Button start, button1, button2, button3, button4;
    RelativeLayout mainQuestion, theEnd;
    ImageView questionImage;
    ProgressBar progressBar;
    RatingBar ratingBar;

    MediaPlayer mplayer, tick, end;

    QuizObject quiz;
    ArrayList<QuizObject> questionsArraylist;
    QuizObject animalsQuiz;
    String[] names;
    Random rand;

    String percentageFinal="0";
    int position = 0;
    int points=0;
    int incorrectPoints=0;
    int i=0;

    CountDownTimer countDownTimer;

    public void getTimer(){

        progressBar.setProgress(i);
        countDownTimer = new CountDownTimer(31000,1000) {


            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000) + "s");
                i++;
                progressBar.setProgress(i *100/(31000/1000));
            }

            @Override
            public void onFinish() {
                theEnd();
                mplayer.stop();
                tick.stop();
                i++;
                progressBar.setProgress(100);
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        start  = findViewById(R.id.go);
        questionImage = findViewById(R.id.questionImage);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        mainQuestion = findViewById(R.id.mainQuestionPage);
        outcome = findViewById(R.id.outcome);
        rand = new Random();
        finalPoint = findViewById(R.id.finalPoints);
        wrongPoints = findViewById(R.id.wrongPoint);
        finalPercentage = findViewById(R.id.percentage);
        theEnd = findViewById(R.id.theEnd);
        progressBar = findViewById(R.id.progressBar);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.INVISIBLE);
                mainQuestion.setVisibility(View.VISIBLE);
                questionImage.setVisibility(View.VISIBLE);
                tick = MediaPlayer.create(getApplicationContext(), R.raw.ticktock);
                tick.start();
            }
        });

        generateAnswersArray();
        generateQuestion();
        getTimer();
        score.setText(Integer.toString(points));
        }


    public void generateAnswersArray(){
        int[] images = {R.drawable.apple, R.drawable.banana, R.drawable.cherry, R.drawable.kiwi,
            R.drawable.orange, R.drawable.pear, R.drawable.pineapple, R.drawable.strawberry, R.drawable.grapes, R.drawable.watermelon};
        names = new String[]{"Apple", "Banana", "Cherry", "Kiwi", "Orange", "Pear", "Pineapple", "Strawberry", "Grapes", "Watermelon"};

        questionsArraylist = new ArrayList<QuizObject>();

        for(int i=0; i<images.length; i++) {

            Random random = new Random();
            ArrayList<String> allAnswers= new ArrayList<>();

            allAnswers.add(names[i]);

            int wrongAnsPos = random.nextInt(names.length);

            while (allAnswers.size()<4){
                String wrongAnswer = names[wrongAnsPos];
                if (allAnswers.contains(wrongAnswer)){
                    wrongAnsPos = random.nextInt(names.length);
                }else {
                    allAnswers.add(wrongAnswer);
                }
            }
            Collections.shuffle(allAnswers);

            animalsQuiz = new QuizObject();
            animalsQuiz.setImage(images[i]);
            animalsQuiz.setName(names[i]);
            animalsQuiz.setAllAnswers(allAnswers);

            questionsArraylist.add(animalsQuiz);
        }
    }

    public void generateQuestion(){

        if(position<questionsArraylist.size()){

            quiz = questionsArraylist.get(position);
            questionImage.setImageResource(quiz.image);

            button1.setText(quiz.getAllAnswers().get(0));
            button2.setText(quiz.getAllAnswers().get(1));
            button3.setText(quiz.getAllAnswers().get(2));
            button4.setText(quiz.getAllAnswers().get(3));
        }else{
            theEnd();
            tick.stop();
        }
        position++;
    }

    public void chooseAnswer(View view){

        Button button = (Button)view;
        String answer = button.getText().toString();

        if (answer.equals(quiz.name)){
            outcome.setText("Correct!");
            outcome.setTextColor(android.graphics.Color.parseColor("#97ea33"));
//            button.setBackgroundColor(Color.parseColor("#6ee02f"));
            points++;
            score.setText(Integer.toString(points));
            mplayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            mplayer.start();
        }else{
            outcome.setText("Wrong!!!");
            outcome.setTextColor(android.graphics.Color.parseColor("#f14839"));
//            button.setBackgroundColor(Color.parseColor("#ed6068"));
            incorrectPoints++;
            mplayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            mplayer.start();
        }
        position++;
        generateQuestion();
    }

    public void theEnd(){
        end = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
        end.start();

        mainQuestion.setVisibility(findViewById(R.id.mainQuestionPage).INVISIBLE);
        start.setVisibility(findViewById(R.id.go).INVISIBLE);
        theEnd.setVisibility(findViewById(R.id.theEnd).VISIBLE);

        finalPoint.setVisibility(findViewById(R.id.wrongPoint).VISIBLE);
        wrongPoints.setVisibility(findViewById(R.id.wrongPoint).VISIBLE);
        finalPercentage.setVisibility(findViewById(R.id.wrongPoint).VISIBLE);

        finalPoint.setText("Correct answers: " + Integer.toString(points));
        wrongPoints.setText( "Wrong Answers: " + Integer.toString(incorrectPoints));

        double percent = (double)points/(points+incorrectPoints)*100;

        percentageFinal = String.format("%.2f", percent) + "%";

        finalPercentage.setText( "Percentage: \n " + percentageFinal);

        Button retry = findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Fruits_QuizActivity.class);
                startActivity(intent);
            }
        });

        Button homePage = findViewById(R.id.homePage);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), new MainActivity().getClass());
                startActivity(intent);
            }
        });

        ratingBar = findViewById(R.id.ratingStar);

        if (percent <21){
            ratingBar.setRating(1);
        }else if (percent >20 && percent < 41 ){
            ratingBar.setRating(2);
        }else if (percent >40 && percent < 61 ){
            ratingBar.setRating(3);
        }else if (percent >60 & percent < 81 ){
            ratingBar.setRating(4);
        }else if (percent > 80){
            ratingBar.setRating(5);
        }

        SharedPreferenceUtils.updateProgress("Fruit", percentageFinal, getApplicationContext());
    }

    public void onBackPressed(){
        try {
            countDownTimer.cancel();
            tick.stop();
            end.stop();
        }catch (Exception e){

        }
        super.onBackPressed();
    }
}