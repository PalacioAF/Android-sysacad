package com.zero.model;

import java.util.List;

public class Cursado {
    String _id;
    String Materia;
    String Comision;
    int Anio;
    String Aula;
    String Obs;
    String IdEstudiante;
    List<Horario> Horario;
    List<NotaParciales> NotaParciales;

    public Cursado() {
    }

    public Cursado(String _id, String materia, String comision, int anio, String aula, String obs, String idEstudiante, List<Horario> horario, List<NotaParciales> notaParciales) {
        this._id = _id;
        Materia = materia;
        Comision = comision;
        Anio = anio;
        Aula = aula;
        Obs = obs;
        IdEstudiante = idEstudiante;
        Horario = horario;
        NotaParciales = notaParciales;
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

    public String getComision() {
        return Comision;
    }

    public void setComision(String comision) {
        Comision = comision;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int anio) {
        Anio = anio;
    }

    public String getAula() {
        return Aula;
    }

    public void setAula(String aula) {
        Aula = aula;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    public List<Horario> getHorario() {
        return Horario;
    }

    public void setHorario(List<Horario> horario) {
        Horario = horario;
    }

    public List<NotaParciales> getNotaParciales() {
        return NotaParciales;
    }

    public void setNotaParciales(List<NotaParciales> notaParciales) {
        NotaParciales = notaParciales;
    }

    @Override
    public String toString() {
        return "Cursado{" +
                "_id='" + _id + '\'' +
                ", Materia='" + Materia + '\'' +
                ", Comision='" + Comision + '\'' +
                ", Anio=" + Anio +
                ", Aula='" + Aula + '\'' +
                ", Obs='" + Obs + '\'' +
                ", IdEstudiante='" + IdEstudiante + '\'' +
                ", Horario=" + Horario +
                ", NotaParciales=" + NotaParciales +
                '}';
    }

    public String ToStringCursado(){
        String s="";
        if(Horario!=null)
            for(Horario horario:Horario){
                if (s.equals("")) {
                    s=horario.toString();
                }
                else
                    s=s+"\n"+horario.toString();
            }
        return s;
    }
    public String ToStringNota(){
        String s="";
        if(NotaParciales!=null)
            for(NotaParciales notaParciales:NotaParciales){
                if (s.equals("")) {
                    s=notaParciales.toString();
                }
                else
                    s=s+"\n"+notaParciales.toString();
            }
        return s;
    }
}
