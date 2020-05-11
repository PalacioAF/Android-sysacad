package com.zero.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horario implements Serializable {
    String _id;
    String   Dia;
    Date HoraI;
    Date   HoraF;

    public Horario() {
    }

    public Horario(String _id, String dia, Date horaI, Date horaF) {
        this._id = _id;
        Dia = dia;
        HoraI = horaI;
        HoraF = horaF;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public Date getHoraI() {
        return HoraI;
    }

    public void setHoraI(Date horaI) {
        HoraI = horaI;
    }

    public Date getHoraF() {
        return HoraF;
    }

    public void setHoraF(Date horaF) {
        HoraF = horaF;
    }

    @Override
    public String toString() {
        return   Dia  +" " + new SimpleDateFormat("hh:mm").format(HoraI) + "-" + new SimpleDateFormat("hh:mm").format(HoraF);
    }
}

