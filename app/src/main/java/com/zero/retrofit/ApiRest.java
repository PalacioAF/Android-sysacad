package com.zero.retrofit;

import com.zero.Request.RequestPostNotificacion;
import com.zero.model.Notificacion;
import com.zero.response.ResponseGetCorrelatividadCursado;
import com.zero.response.ResponseGetCorrelatividadRendir;
import com.zero.response.ResponseGetCursado;
import com.zero.response.ResponseGetEstadoAcademico;
import com.zero.response.ResponseGetEstudiante;
import com.zero.response.ResponseGetExamen;
import com.zero.response.ResponseGetMateriasPlan;
import com.zero.response.ResponseGetNotificacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRest {

    @GET("login/{id}&{pass}")
    Call<ResponseGetEstudiante> Login(@Path("id") int usuario, @Path("pass") String pass);
    @GET("materiasplan/{id}")
    Call<ResponseGetMateriasPlan> GetMateriasPlan(@Header("Authorization") String token, @Path("id") String id);
    @GET("estadoacademico/{id}")
    Call<ResponseGetEstadoAcademico> GetEstadoAcademido(@Header("Authorization") String token, @Path("id") String id);
    @GET("examenes/{id}")
    Call<ResponseGetExamen> GetExamenes(@Header("Authorization") String token, @Path("id") String id);
    @GET("cursado/{id}")
    Call<ResponseGetCursado> GetCursado(@Header("Authorization") String token, @Path("id") String id);
    @GET("Correlatividadcursado/{id}")
    Call<ResponseGetCorrelatividadCursado> GetCorrelatividadCursado(@Header("Authorization") String token, @Path("id") String id);
    @GET("Correlatividadrendir/{id}")
    Call<ResponseGetCorrelatividadRendir> GetCorrelatividadRendir(@Header("Authorization") String token, @Path("id") String id);
    @GET("notificacion/{id}")
    Call<ResponseGetNotificacion> GetNotificacion(@Header("Authorization") String token, @Path("id") String id);
    @POST("notificacion")
    Call<RequestPostNotificacion> AddNotificacion(@Body Notificacion notificacion);
}
