package com.zero.model;

import java.io.Serializable;

public class NotaParciales implements Serializable {
    String _id;
    String   Parcial;
    int NotaN;
    String NotaE;

    public NotaParciales() {
    }

    public NotaParciales(String _id, String parcial, int notaN, String notaE) {
        this._id = _id;
        Parcial = parcial;
        NotaN = notaN;
        NotaE = notaE;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getParcial() {
        return Parcial;
    }

    public void setParcial(String parcial) {
        Parcial = parcial;
    }

    public int getNotaN() {
        return NotaN;
    }

    public void setNotaN(int notaN) {
        NotaN = notaN;
    }

    public String getNotaE() {
        return NotaE;
    }

    public void setNotaE(String notaE) {
        NotaE = notaE;
    }

    @Override
    public String toString() {
        return Parcial +":" + NotaN +"(" + NotaE  +")";
    }
}
