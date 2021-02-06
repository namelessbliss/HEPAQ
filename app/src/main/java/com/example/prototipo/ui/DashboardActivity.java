package com.example.prototipo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Utils;
import com.example.prototipo.ui.atenciones.AtencionesActivity;
import com.example.prototipo.ui.resultados.ResultadosActivity;
import com.example.prototipo.ui.vacuna.VacunaActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView ivUser;
    TextView tvUser;
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_atencion:
                    goToActivity(new AtencionesActivity());
                    return true;
                case R.id.navigation_vacuna:
                    goToActivity(new VacunaActivity());
                    return true;
                case R.id.navigation_resultados:
                    goToActivity(new ResultadosActivity());
                    return true;
                case R.id.navigation_acerca:
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivUser.setOnClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        ivUser = findViewById(R.id.imageViewUser);
        tvUser = findViewById(R.id.tvUser);
        bottomNavigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewUser:
                goToActivity(new ProfileActivity());
                break;
            case R.id.tvUser:
                goToActivity(new ProfileActivity());
                break;
            default:
                break;

        }
    }

    private void goToActivity(AppCompatActivity activity) {
        Intent intent = new Intent(DashboardActivity.this, activity.getClass());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

}