package com.example.prototipo.retrofit;


import com.example.prototipo.retrofit.request.RequestLogin;
import com.example.prototipo.retrofit.response.ResponseLogin;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;

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
}
