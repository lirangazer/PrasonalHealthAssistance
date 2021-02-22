package com.example.prasonalhealthassistance;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(4000);
                        Intent intent = new Intent (LaunchPage.this,LoginPage.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}