package com.zero.response;

import com.zero.model.Estado;
import com.zero.model.MateriasPlan;

import java.util.List;

public class ResponseGetMateriasPlan {
    Estado estado;
    List<MateriasPlan> materiasplan;

    public ResponseGetMateriasPlan() {
    }

    public ResponseGetMateriasPlan(Estado estado, List<MateriasPlan> materiasplan) {
        this.estado = estado;
        this.materiasplan = materiasplan;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<MateriasPlan> getMateriasplan() {
        return materiasplan;
    }

    public void setMateriasplan(List<MateriasPlan> materiasplan) {
        this.materiasplan = materiasplan;
    }
}
