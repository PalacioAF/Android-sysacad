package com.zero.model;

import java.io.Serializable;

public class MateriasPlan implements Serializable {
    String _id;
    String Materia;
    String Dic;
    int   Nivel;

    public MateriasPlan() {
    }

    public MateriasPlan(String _id, String materia, String dic, int nivel) {
        this._id = _id;
        Materia = materia;
        Dic = dic;
        Nivel = nivel;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public String getDic() {
        return Dic;
    }

    public void setDic(String dic) {
        Dic = dic;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int nivel) {
        Nivel = nivel;
    }

}
