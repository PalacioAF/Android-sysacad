package com.zero.model;

public class Carrera {
    String _id;
    String Descripcion;

    public Carrera() {
    }

    public Carrera(String _id, String descripcion) {
        this._id = _id;
        Descripcion = descripcion;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

}
