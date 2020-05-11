package com.zero.model;

import java.io.Serializable;
import java.util.Date;

public class Notificacion implements Serializable {

    String _id;
    String Titulo;
    String  Descripcion;
    Date FechaCreacion;
    Date FechaActivacion;
    String Estado;
    String IdEstudiante;

    public Notificacion() {
    }

    public Notificacion(String _id, String titulo, String descripcion, Date fechaCreacion, Date fechaActivacion, String estado, String idEstudiante) {
        this._id = _id;
        Titulo = titulo;
        Descripcion = descripcion;
        FechaCreacion = fechaCreacion;
        FechaActivacion = fechaActivacion;
        Estado = estado;
        IdEstudiante = idEstudiante;
    }
    public Notificacion( String titulo, String descripcion, Date fechaCreacion, Date fechaActivacion, String estado, String idEstudiante) {
        Titulo = titulo;
        Descripcion = descripcion;
        FechaCreacion = fechaCreacion;
        FechaActivacion = fechaActivacion;
        Estado = estado;
        IdEstudiante = idEstudiante;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public Date getFechaActivacion() {
        return FechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        FechaActivacion = fechaActivacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "_id='" + _id + '\'' +
                ", Titulo='" + Titulo + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", FechaCreacion=" + FechaCreacion +
                ", FechaActivacion=" + FechaActivacion +
                ", Estado='" + Estado + '\'' +
                ", IdEstudiante='" + IdEstudiante + '\'' +
                '}';
    }
}
