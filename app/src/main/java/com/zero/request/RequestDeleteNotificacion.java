package com.zero.request;

import com.zero.model.Estado;

public class RequestDeleteNotificacion {
    Estado estado;

    public RequestDeleteNotificacion() {
    }

    public RequestDeleteNotificacion(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
