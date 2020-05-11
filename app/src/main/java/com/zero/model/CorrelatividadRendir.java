package com.zero.model;

import java.io.Serializable;
import java.util.List;

public class CorrelatividadRendir implements Serializable {

    String _id;
    String Materia;
    String Plan;
    int Anio;
    String IdEstudiante;
    List<Correlatividad> Correlatividad;

    public CorrelatividadRendir() {
    }

    public CorrelatividadRendir(String _id, String materia, String plan, int anio, String idEstudiante, List<Correlatividad> correlatividad) {
        this._id = _id;
        Materia = materia;
        Plan = plan;
        Anio = anio;
        IdEstudiante = idEstudiante;
        Correlatividad = correlatividad;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String plan) {
        Plan = plan;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int anio) {
        Anio = anio;
    }

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    public List<Correlatividad> getCorrelatividad() {
        return Correlatividad;
    }

    public void setCorrelatividad(List<Correlatividad> correlatividad) {
        Correlatividad = correlatividad;
    }

    @Override
    public String toString() {
        return "CorrelatividadRendir{" +
                "_id='" + _id + '\'' +
                ", Materia='" + Materia + '\'' +
                ", Plan='" + Plan + '\'' +
                ", Anio=" + Anio +
                ", IdEstudiante='" + IdEstudiante + '\'' +
                ", Correlatividad=" + Correlatividad +
                '}';
    }

    public String ToStringCorrelatividad(){
        String s="";
        if(Correlatividad!=null)
            for(Correlatividad correlatividad:Correlatividad){
                if (s.equals("")) {
                    s=correlatividad.toString();
                }
                else
                    s=s+"\n"+correlatividad.toString();
            }
        return s;
    }
}
