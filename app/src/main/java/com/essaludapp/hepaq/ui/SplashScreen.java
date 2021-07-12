package com.essaludapp.hepaq.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.ui.dashboard.DashboardActivity;
import com.hanks.htextview.line.LineTextView;

public class SplashScreen extends AppCompatActivity {

    private LineTextView lineTextView;
    private boolean isLogin = false;
    private boolean isSetNombre = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        try {
            isLogin = SharedPreferencesManager.getBooleanValue(Constants.PREF_LOGIN);
            String nombre = SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE);
            if (nombre != null && !nombre.equalsIgnoreCase("null"))
                isSetNombre = true;
            else
                isSetNombre = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        lineTextView = (LineTextView) findViewById(R.id.lineTextView);
        lineTextView.animateText(getString(R.string.app_name));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (isLogin)
                    if (isSetNombre)
                    i = new Intent(SplashScreen.this, DashboardActivity.class);
                    else {
                        isLogin=false;
                        i = new Intent(SplashScreen.this, LoginActivity.class);
                    }
                else
                    i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }

}