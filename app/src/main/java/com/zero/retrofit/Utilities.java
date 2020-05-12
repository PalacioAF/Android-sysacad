package com.zero.retrofit;

public class Utilities {
    public static final String BASE_URL = "http://192.168.1.2:3000/";

    public static ApiRest getAPIService(){
        return RetrofitClient.getCliente(BASE_URL).create(ApiRest.class);
    }
}
