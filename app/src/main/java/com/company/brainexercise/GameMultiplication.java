package com.company.brainexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;
import java.util.Random;

public class GameMultiplication extends AppCompatActivity {

    TextView textViewScore;
    TextView life;
    TextView time;

    TextView question;
    EditText editAnswer;

    Button ok;
    Button next;

    int counterLife = 3;
    int counterScore = 0;

    Random random = new Random();
    CountDownTimer timer;
    public static final long START_TIMER_IN_MILES = 15000;
    boolean timerIsRunning;
    long time_left_in_millis = START_TIMER_IN_MILES;

    int number1;
    int number2;
    int userAnswer;

    String s = "";

    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_multiplication);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        textViewScore = findViewById(R.id.textViewScoreMul);
        life = findViewById(R.id.textViewLIfeMul);
        time = findViewById(R.id.textView1TimeMul);
        question = findViewById(R.id.textViewQuestionMul);
        editAnswer = findViewById(R.id.editTextMul);
        ok = findViewById(R.id.buttonOkMul);
        next = findViewById(R.id.buttonNextMul);

        adView = findViewById(R.id.adViewIdM);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        showQuestion();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.valueOf(editAnswer.getText().toString());

                int res = number1 * number2;

                pauseTimer();

                if (number1 * number2 == userAnswer) {
                    question.setText("Your answer is true!");
                    counterScore = counterScore + 1;
                    textViewScore.setText("" + counterScore);
                } else {
                    question.setText("Your answer is wrong.");
                    counterLife--;
                    life.setText("" + counterLife);
                    editAnswer.setText("Correct answer: " + res);
                }

                if (counterLife == 0) {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameMultiplication.this, Last.class);
                    intent.putExtra("score", counterScore);
                    finish();
                    startActivity(intent);
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();

                if (editAnswer.getText().toString().equals(s)) {
                    counterLife = counterLife - 1;
                    life.setText("" + counterLife);
                }

                showQuestion();
                editAnswer.setText("");

                if (counterLife == 0) {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameMultiplication.this, Last.class);
                    intent.putExtra("score", counterScore);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void showQuestion() {

        number1 = random.nextInt(100);
        number2 = random.nextInt(10);
        question.setText(number1 + " * " + number2);
        startTimer();

    }

    public void startTimer() {

        timer = new CountDownTimer(time_left_in_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time_left_in_millis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timerIsRunning = false;
                pauseTimer();
                resetTimer();
                updateText();
                question.setText("Sorry, time is up.");

            }
        }.start();

        timerIsRunning = true;

    }

    public void updateText() {

        int second = (int) (time_left_in_millis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);

    }

    public void pauseTimer() {

        timer.cancel();
        timerIsRunning = false;

    }

    public void resetTimer() {

        time_left_in_millis = START_TIMER_IN_MILES;
        updateText();

    }

}
