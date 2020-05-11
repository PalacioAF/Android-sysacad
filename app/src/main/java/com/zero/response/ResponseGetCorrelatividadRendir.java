package com.zero.response;

import com.zero.model.CorrelatividadRendir;
import com.zero.model.Estado;

import java.util.List;

public class ResponseGetCorrelatividadRendir {
    Estado estado;
    List<CorrelatividadRendir> Correlatividadrendir;

    public ResponseGetCorrelatividadRendir() {
    }

    public ResponseGetCorrelatividadRendir(Estado estado, List<CorrelatividadRendir> correlatividadrendir) {
        this.estado = estado;
        Correlatividadrendir = correlatividadrendir;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<CorrelatividadRendir> getCorrelatividadrendir() {
        return Correlatividadrendir;
    }

    public void setCorrelatividadrendir(List<CorrelatividadRendir> correlatividadrendir) {
        Correlatividadrendir = correlatividadrendir;
    }
}
