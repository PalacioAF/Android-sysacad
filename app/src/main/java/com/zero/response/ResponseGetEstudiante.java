package com.zero.response;

import com.zero.model.Estado;
import com.zero.model.Estudiante;

import java.util.List;

public class ResponseGetEstudiante {
    Estado estado;
    List<Estudiante> estudiente;
    String token;

    public ResponseGetEstudiante() {
    }

    public ResponseGetEstudiante(Estado estado, List<Estudiante> estudiente, String token) {
        this.estado = estado;
        this.estudiente = estudiente;
        this.token=token;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estudiante> getEstudiente() {
        return estudiente;
    }

    public void setEstudiente(List<Estudiante> estudiente) {
        this.estudiente = estudiente;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
