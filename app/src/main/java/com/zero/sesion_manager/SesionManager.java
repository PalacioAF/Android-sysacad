package com.zero.sesion_manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.zero.model.Carrera;
import com.zero.model.Estudiante;

public class SesionManager {
    private Context context;
    private SharedPreferences content;

    public SesionManager(Context context) {
        content=context.getSharedPreferences("Login",Context.MODE_PRIVATE);
    }

    //Registramos al estudiante y token de autentificacion en las shared preferences
    //damos al login un valor true, porque se logueo el usuario
    public Boolean RegisterEstudiante(Estudiante estudiante, String token){
        SharedPreferences.Editor editor=content.edit();
        editor.putBoolean("Login",true);
        editor.putString("IdE",estudiante.get_id());
        editor.putString("Nombre",estudiante.getNombre());
        editor.putString("Apellido",estudiante.getApellido());
        editor.putInt("Legajo",estudiante.getLegajo());
        editor.putString("Password",estudiante.getPassword());
        editor.putString("IdC",estudiante.getIdCarrera().get_id());
        editor.putString("Carrera",estudiante.getIdCarrera().getDescripcion());
        editor.putString("Token",token.replaceAll("\"",""));
        editor.commit();
        return true;
    }

    //devuelve el valor de  login
    public boolean isLogin(){
        return content.getBoolean("Login",false);
    }

    //devuelve el valor de estudiante
    public Estudiante GetEstudiante(){
        return (new Estudiante(content.getString("IdE",""),content.getString("Nombre",""),content.getString("Apellido"
                ,""),content.getInt("Legajo",0),content.getString("Password","")
                ,new Carrera(content.getString("IdC",""),content.getString("Carrera",""))));
    }

    //devuelve el valor del token de autenticacion
    public String GetToken(){
        return content.getString("Token","");
    }

    //borra el contenido de las share prefereces cuando se desloguea el usuario
    public void Logout(){
        SharedPreferences.Editor editor=content.edit();
        editor.clear();
        editor.commit();
    }

    public Boolean setState(Boolean b){
        SharedPreferences.Editor editor=content.edit();
        editor.putBoolean("State",b);
        editor.commit();
        return true;
    }
    public Boolean getState(){
       return content.getBoolean("State",false);
    }


}
