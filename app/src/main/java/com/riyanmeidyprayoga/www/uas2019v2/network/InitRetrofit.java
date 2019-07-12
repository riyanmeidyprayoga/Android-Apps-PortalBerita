package com.riyanmeidyprayoga.www.uas2019v2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rizal Hilman on 12/02/18.
 * www.khilman.com
 */

public class InitRetrofit {

    private static Retrofit setInit() {
        // URL Server API
        String API_URL = "http://192.168.43.38/api_portal_berita/";
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }
}