package com.example.prototipo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.retrofit.HEPAQClient;
import com.example.prototipo.retrofit.HEPAQService;
import com.example.prototipo.retrofit.request.RequestLogin;
import com.example.prototipo.retrofit.response.ResponseLogin;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    private EditText etDNI;
    private EditText etfechaNacimiento;
    private ImageView imgCalendario;
    private Button btnLogin;
    HEPAQClient hepaqClient;
    HEPAQService hepaqService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        retrofitInit();
        bindView();
        events();
        configurarDatePicker();

    }

    private void retrofitInit() {
        hepaqClient = hepaqClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void bindView() {
        etDNI = findViewById(R.id.etDNI);
        etfechaNacimiento = findViewById(R.id.etFechaNacimiento);
        imgCalendario = findViewById(R.id.imgCalendario);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void events() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = etDNI.getText().toString();
                String fecha = etfechaNacimiento.getText().toString();

                if (dni.isEmpty()) {
                    etDNI.setError("Complete el campo requerido");
                } else if (fecha.isEmpty()) {
                    etfechaNacimiento.setError("Complete el campo requerido");
                } else {
                    RequestLogin requestLogin = new RequestLogin(dni, fecha);

                    Call<ResponseLogin> call = hepaqService.doLoginWithField(dni, fecha);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Acceso correcto", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            if (t.getMessage().equals(Constants.NET_ERROR)) {
                                new AestheticDialog.Builder(LoginActivity.this, DialogStyle.EMOTION, DialogType.ERROR)
                                        .setTitle("ERROR DE CONEXION")
                                        .setMessage("Revise su conexion a internet")
                                        .show();
                            } else {
                                new AestheticDialog.Builder(LoginActivity.this, DialogStyle.EMOTION, DialogType.ERROR)
                                        .setTitle("ERROR")
                                        .setMessage("DNI o Fecha de nacimiento incorrecta")
                                        .show();
                            }

                        }
                    });
                }
            }
        });
    }

    private void configurarDatePicker() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etfechaNacimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LoginActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        imgCalendario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LoginActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        etfechaNacimiento.setText(sdf.format(myCalendar.getTime()));
    }
}