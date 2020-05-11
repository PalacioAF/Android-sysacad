package com.zero.model;

import java.io.Serializable;

public class EstadoAcademico implements Serializable {
    String _id;
    String Materia;
    String Estado;
    int Anio;
    String IdEstudiante;

    public EstadoAcademico() {
    }

    public EstadoAcademico(String _id, String materia, String estado, int anio, String idEstudiante) {
        this._id = _id;
        Materia = materia;
        Estado = estado;
        Anio = anio;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
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
}
