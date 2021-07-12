package com.essaludapp.hepaq.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.ProgressGenerator;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.ResponseLogin;
import com.essaludapp.hepaq.ui.dashboard.DashboardActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener {

    final Calendar myCalendar = Calendar.getInstance();
    private EditText etDNI;
    private EditText etfechaNacimiento;
    private ImageView imgCalendario;
    private ActionProcessButton btnLogin;
    private ProgressGenerator progressGenerator;
    private boolean loginCorrecto = false;
    private CheckBox cbRecordar;


    private HEPAQClient hepaqClient;
    private HEPAQService hepaqService;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        progressGenerator = new ProgressGenerator(this);

        retrofitInit();
        bindView();
        getLoginData();
        events();
        configurarDatePicker();

    }

    private void getLoginData() {
        try {
            etDNI.setText(SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO));
            etfechaNacimiento.setText(SharedPreferencesManager.getStringValue(Constants.PREF_FECHA_NACIMIENTO));
        } catch (Exception e) {

        }

    }

    private void retrofitInit() {
        hepaqClient = hepaqClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void bindView() {
        etDNI = findViewById(R.id.etDNI);
        etfechaNacimiento = findViewById(R.id.etFechaNacimiento);
        imgCalendario = findViewById(R.id.imgCalendario);
        cbRecordar = findViewById(R.id.cbRecordar);

        btnLogin = (ActionProcessButton) findViewById(R.id.btnConfir);
        btnLogin.setMode(ActionProcessButton.Mode.PROGRESS);
        btnLogin.setProgress(0);
    }

    private void events() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnLogin);
                etfechaNacimiento.setEnabled(false);
                etDNI.setEnabled(false);
                btnLogin.setEnabled(false);
                String dni = etDNI.getText().toString();
                String fecha = etfechaNacimiento.getText().toString();

                if (dni.isEmpty()) {
                    etDNI.setError("");
                    loginCorrecto = false;
                    etfechaNacimiento.setEnabled(true);
                    etDNI.setEnabled(true);
                    btnLogin.setEnabled(true);
                } else if (fecha.isEmpty()) {
                    etfechaNacimiento.setError("");
                    loginCorrecto = false;
                    etfechaNacimiento.setEnabled(true);
                    etDNI.setEnabled(true);
                    btnLogin.setEnabled(true);
                } else {

                    //RequestLogin requestLogin = new RequestLogin(dni, fecha);

                    Call<ResponseLogin> call = hepaqService.doLoginWithField(dni, fecha);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()) {

                                if (response.body() == null) {
                                    onFailure(call, new Throwable());
                                } else {
                                    //Almacena preferences del login
                                    if (cbRecordar.isChecked()) {
                                        SharedPreferencesManager.setBooleanValue(Constants.PREF_RECORDAR, true);
                                        SharedPreferencesManager.setStringValue(Constants.PREF_DOCUMENTO, response.body().getDocumento());
                                        SharedPreferencesManager.setStringValue(Constants.PREF_FECHA_NACIMIENTO, response.body().getFechaNacimiento());
                                    } else {
                                        SharedPreferencesManager.setBooleanValue(Constants.PREF_RECORDAR, false);
                                    }
                                    SharedPreferencesManager.setBooleanValue(Constants.PREF_LOGIN, true);
/*                                    SharedPreferencesManager.setStringValue(Constants.PREF_APELLIDO, response.body().getApellidos());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_NOMBRE, response.body().getNombres());*/
                                    SharedPreferencesManager.setStringValue(Constants.PREF_APELLIDO, " ");
                                    SharedPreferencesManager.setStringValue(Constants.PREF_NOMBRE, " ");
                                    SharedPreferencesManager.setStringValue(Constants.PREF_NOMBRE_COMPLETO, response.body().getNombres_completos());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_SEXO, response.body().getApellidos());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_DIRECCION, response.body().getDireccion());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_TELEFONO, response.body().getTelefono());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_CORREO, response.body().getEmail());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_TIPO, response.body().getTipo());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_TIPO_ASEGURADO, response.body().getTipoAsegurado());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_N_HISTORIA_CLINICA, response.body().getNHistoriaClinica() + "");
                                    SharedPreferencesManager.setStringValue(Constants.PREF_AUTOGENERADO, response.body().getAutogenerado());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_ESTADO, response.body().getEstado());
                                    SharedPreferencesManager.setStringValue(Constants.PREF_ID_SEDE, response.body().getIdSede() + "");
                                    loginCorrecto = true;
                                }
                            } else {
                                SharedPreferencesManager.setBooleanValue(Constants.PREF_LOGIN, false);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            SharedPreferencesManager.setBooleanValue(Constants.PREF_LOGIN, false);
                            if (t.getMessage() == null) {
                                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("ERROR")
                                        .setContentText("DNI o Fecha de nacimiento incorrecta")
                                        .show();
                                loginCorrecto = false;
                            } else {
                                try {
                                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("ERROR DE CONEXION")
                                            .setContentText("Revise su conexion a internet")
                                            .show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                loginCorrecto = false;
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

        /*etfechaNacimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LoginActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        imgCalendario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        LoginActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                etfechaNacimiento.setText(date);
            }
        };
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        etfechaNacimiento.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onComplete() {
        if (loginCorrecto) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            loginCorrecto = false;
            startActivity(intent);
            //finish();

        } else {
            btnLogin.setProgress(0);
        }
        etfechaNacimiento.setEnabled(true);
        etDNI.setEnabled(true);
        btnLogin.setEnabled(true);

    }
}