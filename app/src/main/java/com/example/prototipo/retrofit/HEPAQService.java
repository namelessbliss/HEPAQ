package com.example.prototipo.retrofit;


import com.example.prototipo.retrofit.request.RequestLogin;
import com.example.prototipo.retrofit.response.ResponseLogin;

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

    /**
     * Pasa de parametro un objeto RequestSignUp y obtiene como respuesta un ResponseAuth
     * @param requestSignUp
     * @return
     *
     @POST("auth/signup") Call<ResponseAuth> doSignUp(@Body RequestSignUp requestSignUp);
     */
}
