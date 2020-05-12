package com.zero.Request;

import com.zero.model.Estado;
import com.zero.model.Notificacion;

public class RequestPostNotificacion {
    Estado estado;
    Notificacion notificacion;

    public RequestPostNotificacion() {
    }

    public RequestPostNotificacion(Estado estado, Notificacion notificacion) {
        this.estado = estado;
        this.notificacion = notificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
}
