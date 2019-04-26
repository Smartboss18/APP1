package com.example.shubham.app1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import static com.example.shubham.app1.R.drawable.quiz_correct_answer;

public class QuizActivity extends AppCompatActivity {

    TextView question, score, timer, outcome, finalPoint, wrongPoints, finalPercentage;

    Button start, button1, button2, button3, button4;
    ConstraintLayout mainQuestion;
    RelativeLayout theEnd;
    ImageView questionImage;
    ProgressBar progressBar;
    RatingBar ratingBar;

    MediaPlayer mplayer, tick, end;

    QuizObject quiz;
    ArrayList<QuizObject> questionsArraylist;
    QuizObject animalsQuiz;
    String[] names;
    int[] images;
    String quizType;
    Random rand;

    String percentageFinal="0";
    int position = 0, points=0, incorrectPoints=0, i=0;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        images = intent.getIntArrayExtra("images");
        names = intent.getStringArrayExtra("names");
        quizType = intent.getStringExtra("type");
        if (images == null || names == null || quizType == null)return;

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
            Collections.shuffle(questionsArraylist);
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
            try {
                countDownTimer.cancel();
                tick.stop();
            }catch (Exception e){ }
        }
        position++;
    }

    public void chooseAnswer(View view){

        final Button button = (Button)view;
        String answer = button.getText().toString();

        if (answer.equals(quiz.name)){
            outcome.setText("Correct!");
            button.setBackgroundResource(R.drawable.quiz_correct_answer);
            outcome.setTextColor(android.graphics.Color.parseColor("#97ea33"));
            points++;
            score.setText(Integer.toString(points));
            mplayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            mplayer.start();
        }else{
            outcome.setText("Wrong!!!");
            outcome.setTextColor(android.graphics.Color.parseColor("#f14839"));
            button.setBackgroundResource(R.drawable.quiz_wrong_answer);
            incorrectPoints++;
            mplayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            mplayer.start();
        }
        CountDownTimer mcountDownTimer = new CountDownTimer(300,100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                button.setBackgroundResource(R.drawable.quiz_options);
                position++;
                generateQuestion();
            }
        }.start();
    }

    public void theEnd(){
        try {
            countDownTimer.cancel();
            end = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
            end.start();
            end.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    try {
                        end.stop();
                    }catch (Exception e){ }
                }
            });
        }catch (Exception e){
        }

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
                releaseResources();
                Intent intent = null;
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Type", quizType);
                editor.commit();
                QuizHelper.startQuiz(getApplicationContext(), intent);
            }
        });

        Button homePage = findViewById(R.id.homePage);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseResources();
                Intent intent = new Intent(getApplicationContext(), new MainActivity().getClass());
                startActivity(intent);
            }
        });

        ratingBar = findViewById(R.id.ratingStar);

        if (percent == 0){
          ratingBar.setRating(0);
        } else if (percent <21){
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

        try {
            tick.reset();
            mplayer.reset();
            countDownTimer.cancel();
        }catch (Exception e){ }

        SharedPreferenceUtils.updateProgress(quizType, percentageFinal, getApplicationContext());
    }

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
                i++;
                progressBar.setProgress(100);
            }
        }.start();
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

    @Override
    protected void onStop() {
        super.onStop();
        releaseResources();
    }

    public  void releaseResources(){
        try {
        countDownTimer.cancel();
        mplayer.release();
        mplayer =null;
        end.release();
        end =null;
        tick.release();
        tick = null;
        }catch (Exception e){
        }
    }
}
