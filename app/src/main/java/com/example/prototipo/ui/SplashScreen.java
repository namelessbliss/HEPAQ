package com.example.prototipo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.prototipo.R;
import com.hanks.htextview.line.LineTextView;

public class SplashScreen extends AppCompatActivity {

    private LineTextView lineTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        lineTextView = (LineTextView) findViewById(R.id.lineTextView);
        lineTextView.animateText(getString(R.string.app_name));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }

}