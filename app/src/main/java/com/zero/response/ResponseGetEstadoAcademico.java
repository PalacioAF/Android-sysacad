package com.zero.response;

import com.zero.model.Estado;
import com.zero.model.EstadoAcademico;

import java.util.List;

public class ResponseGetEstadoAcademico {
    Estado estado;
    List<EstadoAcademico> estadoacademico;

    public ResponseGetEstadoAcademico() {
    }

    public ResponseGetEstadoAcademico(Estado estado, List<EstadoAcademico> estadoacademico) {
        this.estado = estado;
        this.estadoacademico = estadoacademico;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<EstadoAcademico> getEstadoacademico() {
        return estadoacademico;
    }

    public void setEstadoacademico(List<EstadoAcademico> estadoacademico) {
        this.estadoacademico = estadoacademico;
    }
}
