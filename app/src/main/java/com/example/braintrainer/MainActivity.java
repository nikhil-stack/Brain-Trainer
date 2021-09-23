package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView textView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTotal;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView question;
    TextView timer;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    ConstraintLayout finalLayout;
    TextView finalScore;
    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        scoreTotal.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        gameLayout.setVisibility(View.VISIBLE);
        finalLayout.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setText("");
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {

                timer.setText(String.valueOf(l / 1000) + "s");
            }

            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                gameLayout.setVisibility(View.INVISIBLE);
                finalScore.setText("Correct: " + Integer.toString(score) + "\nAttempted: " + Integer.toString(numberOfQuestions) );
                finalLayout.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        scoreTotal = findViewById(R.id.score);
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            textView.setText("Correct!");
            score++;
        } else {
            textView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTotal.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void start(View view) {
        start.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        finalLayout.setVisibility(View.INVISIBLE);
    }

    public void newQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        locationOfCorrectAnswer = rand.nextInt(4);
        question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        question = findViewById(R.id.done);
        button0 = findViewById(R.id.option1);
        button1 = findViewById(R.id.option2);
        button2 = findViewById(R.id.option3);
        button3 = findViewById(R.id.option4);
        timer = findViewById(R.id.timer);
        playAgainButton = findViewById(R.id.playAgain);
        scoreTotal = findViewById(R.id.score);
        gameLayout = findViewById(R.id.gameLayout);
        textView = findViewById(R.id.textView);
        finalLayout = findViewById(R.id.finalLayout);
        finalScore = findViewById(R.id.finalScore);
        playAgain(findViewById(R.id.timer));
        start.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
