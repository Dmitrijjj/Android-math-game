package com.company.brainexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Last extends AppCompatActivity {

    TextView lastScore;
    Button exit;
    Button playAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        lastScore = findViewById(R.id.textViewLastScore);
        exit = findViewById(R.id.buttonExit);
        playAgain = findViewById(R.id.buttonPlayAgain);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        lastScore.setText("Your score: " + score);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Last.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}