package com.zero.response;

import com.zero.model.Estado;
import com.zero.model.Notificacion;

import java.util.List;

public class ResponseGetNotificacion {
    Estado estado;
    List<Notificacion> notificacion;

    public ResponseGetNotificacion() {
    }

    public ResponseGetNotificacion(Estado estado, List<Notificacion> notificacion) {
        this.estado = estado;
        this.notificacion = notificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Notificacion> getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(List<Notificacion> notificacion) {
        this.notificacion = notificacion;
    }
}
