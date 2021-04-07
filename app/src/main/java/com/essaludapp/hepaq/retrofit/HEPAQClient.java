package com.essaludapp.hepaq.retrofit;


import com.essaludapp.hepaq.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HEPAQClient {

    // Instancia propia estatica
    private static HEPAQClient INSTANCE = null;

    private HEPAQService HEPAQService;
    private Retrofit retrofit;

    public HEPAQClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HEPAQService = retrofit.create(HEPAQService.class);
    }

    /**
     * Patron Singleton
     *
     * @return
     */
    public static HEPAQClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HEPAQClient();
        }
        return INSTANCE;
    }

    public HEPAQService getHEPAQService() {
        return HEPAQService;
    }
}
