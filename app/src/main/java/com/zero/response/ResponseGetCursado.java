package com.zero.response;

import com.zero.model.Cursado;
import com.zero.model.Estado;

import java.util.List;

public class ResponseGetCursado {
    Estado estado;
    List<Cursado> cursado;

    public ResponseGetCursado() {
    }

    public ResponseGetCursado(Estado estado, List<Cursado> cursado) {
        this.estado = estado;
        this.cursado = cursado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Cursado> getCursado() {
        return cursado;
    }

    public void setCursado(List<Cursado> cursado) {
        this.cursado = cursado;
    }
}
