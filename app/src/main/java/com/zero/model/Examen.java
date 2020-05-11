package com.zero.model;

import java.io.Serializable;
import java.util.Date;

public class Examen implements Serializable {

    String _id;
    String Materia;
    Date Fecha;
    String Nota;
    int N_Nota;
    String IdEstudiante;

    public Examen() {
    }

    public Examen(String _id, String materia, Date fecha, String nota, int n_Nota, String idEstudiante) {
        this._id = _id;
        Materia = materia;
        Fecha = fecha;
        Nota = nota;
        N_Nota = n_Nota;
        IdEstudiante = idEstudiante;
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

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    public int getN_Nota() {
        return N_Nota;
    }

    public void setN_Nota(int n_Nota) {
        N_Nota = n_Nota;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "_id='" + _id + '\'' +
                ", Materia='" + Materia + '\'' +
                ", Fecha=" + Fecha +
                ", Nota='" + Nota + '\'' +
                ", IdEstudiante='" + IdEstudiante + '\'' +
                '}';
    }
}
