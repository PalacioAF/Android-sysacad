package com.zero.response;

import com.zero.model.CorrelatividadCursado;
import com.zero.model.Estado;

import java.util.List;

public class ResponseGetCorrelatividadCursado {
    Estado estado;
    List<CorrelatividadCursado> Correlatividadcursado;

    public ResponseGetCorrelatividadCursado() {
    }

    public ResponseGetCorrelatividadCursado(Estado estado, List<CorrelatividadCursado> correlatividadcursado) {
        this.estado = estado;
        Correlatividadcursado = correlatividadcursado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<CorrelatividadCursado> getCorrelatividadcursado() {
        return Correlatividadcursado;
    }

    public void setCorrelatividadcursado(List<CorrelatividadCursado> correlatividadcursado) {
        Correlatividadcursado = correlatividadcursado;
    }
}
