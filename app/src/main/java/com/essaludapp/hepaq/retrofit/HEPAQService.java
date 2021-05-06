package com.essaludapp.hepaq.retrofit;


import com.essaludapp.hepaq.retrofit.request.RequestLogin;
import com.essaludapp.hepaq.retrofit.response.ResponseLogin;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.essaludapp.hepaq.retrofit.response.tests.ResponseListarTest;
import com.essaludapp.hepaq.retrofit.response.tests.ResponseRegistrarTests;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseConfirmarVacuna;
import com.essaludapp.hepaq.retrofit.response.vacunas.ResponseDosisVacuna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HEPAQService {


    /**
     * Pasa de parametro un objeto RequestLogin y obtiene como respuesta un ResponseAuth
     *
     * @param requestLogin
     * @return
     */
    @POST("loginAndroid.php")
    Call<ResponseLogin> doLogin(@Body RequestLogin requestLogin);

    @POST("loginAndroid.php")
    @FormUrlEncoded
    Call<ResponseLogin> doLoginWithField(@Field("documento") String documento, @Field("fecha_naci") String fecha_naci);


    @POST("seleccionarActoMedicoMovil.php")
    @FormUrlEncoded
    Call<List<ResponseAtenciones>> getAllAtenciones(@Field("id") String documento);

    @POST("listarDosisPacientesConfirmados.php")
    @FormUrlEncoded
    Call<List<ResponseDosisVacuna>> getDosisConfirmados(@Field("id") String documento);

    @POST("listarDosisPacientesNoConfirmados.php")
    @FormUrlEncoded
    Call<List<ResponseDosisVacuna>> getDosisNoConfirmados(@Field("id") String documento);

    @POST("confirmarDosisPaciente.php")
    @FormUrlEncoded
    Call<ResponseConfirmarVacuna> confirmarDosisVacuna(@Field("id") String id, @Field("dni") String dni);

    @POST("registrarTest.php")
    @FormUrlEncoded
    Call<ResponseRegistrarTests> registrarTest(@Field("dataForm") String requestTest);

/*    @POST("registrarTest.php")
    @FormUrlEncoded
    Call<ResponseTests> registrarTest(@Body RequestTest requestTest);*/

    @POST("listarTest.php")
    @FormUrlEncoded
    Call<ResponseListarTest> getTest(@Field("dni") String dni, @Field("tipo") int tipo);
}
