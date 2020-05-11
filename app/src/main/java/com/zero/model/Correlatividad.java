package com.zero.model;

import java.io.Serializable;

public class Correlatividad implements Serializable {
    String _id;
    String   Materia;
    String   Motivo;

    public Correlatividad() {
    }

    public Correlatividad(String _id, String materia, String motivo) {
        this._id = _id;
        Materia = materia;
        Motivo = motivo;
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

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    @Override
    public String toString() {
        return "No tiene "+ Motivo+" de "+ Materia;
    }
}
