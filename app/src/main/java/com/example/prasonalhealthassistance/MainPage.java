package com.example.prasonalhealthassistance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fitnessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent (MainPage.this,FitnessActivity.class);
                 startActivity(intent);
            }

        });
        findViewById(R.id.healthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainPage.this,HealthActivity.class);
                startActivity(intent);
            }

        });
    }
}