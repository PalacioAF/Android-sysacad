package com.zero.model;

public class Estudiante {
    String _id;
    String Nombre;
    String Apellido;
    int Legajo;
    String Password;
    Carrera IdCarrera;

    public Estudiante() {
    }

    public Estudiante(String _id, String nombre, String apellido, int legajo, String password, Carrera idCarrera) {
        this._id = _id;
        Nombre = nombre;
        Apellido = apellido;
        Legajo = legajo;
        Password = password;
        IdCarrera = idCarrera;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getLegajo() {
        return Legajo;
    }

    public void setLegajo(int legajo) {
        Legajo = legajo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Carrera getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        IdCarrera = idCarrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "_id='" + _id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Legajo=" + Legajo +
                ", Password='" + Password + '\'' +
                ", IdCarrera='" + IdCarrera._id + '\'' +
                ", IdCarrera='" + IdCarrera.Descripcion +
                '}';
    }
}
