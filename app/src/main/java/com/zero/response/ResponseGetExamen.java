package com.zero.response;

import com.zero.model.Estado;
import com.zero.model.Examen;

import java.util.List;

public class ResponseGetExamen {
    Estado estado;
    List<Examen> examenes;

    public ResponseGetExamen() {
    }

    public ResponseGetExamen(Estado estado, List<Examen> examenes) {
        this.estado = estado;
        this.examenes = examenes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
}
