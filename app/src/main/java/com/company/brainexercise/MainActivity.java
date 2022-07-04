package com.company.brainexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button additionBtn;
    Button subtractionBtn;
    Button multiplicationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        additionBtn = findViewById(R.id.buttonAdd);
        subtractionBtn = findViewById(R.id.buttonSub);
        multiplicationBtn = findViewById(R.id.buttonMul);



        additionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GameAddition.class);
                startActivity(intent);
                finish();

            }
        });

        multiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GameMultiplication.class);
                startActivity(intent);
                finish();

            }
        });

        subtractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GameSubtraction.class);
                startActivity(intent);
                finish();

            }
        });
    }
}