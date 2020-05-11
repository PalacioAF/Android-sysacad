package com.zero.model;

public class Estado {
    int codigo;
    String respuesta;

    public Estado() {
    }

    public Estado(int codigo, String respuesta) {
        this.codigo = codigo;
        this.respuesta = respuesta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
