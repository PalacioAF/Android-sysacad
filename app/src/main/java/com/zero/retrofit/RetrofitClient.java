package com.zero.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getCliente(String urlServidorRest){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(urlServidorRest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
