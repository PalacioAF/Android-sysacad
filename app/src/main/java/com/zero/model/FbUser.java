package com.zero.model;

public class FbUser {
    private String Legajo;
    private String Idtoken;

    public FbUser() {
    }

    public FbUser(String legajo, String idtoken) {
        Legajo = legajo;
        Idtoken = idtoken;
    }

    public String getLegajo() {
        return Legajo;
    }

    public void setLegajo(String legajo) {
        Legajo = legajo;
    }

    public String getIdtoken() {
        return Idtoken;
    }

    public void setIdtoken(String idtoken) {
        Idtoken = idtoken;
    }
}
