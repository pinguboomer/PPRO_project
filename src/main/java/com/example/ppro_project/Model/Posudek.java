package com.example.ppro_project.Model;

public class Posudek {

    public String idUtkani;
    public String jmenoR;
    public String prijmeniR;
    public String idFacrR;
    public String roleR;

    public String domaci;

    public String hoste;
    public String vysledek;

    public Posudek(String idUtkani, String jmenoR, String prijmeniR, String idFacrR,
                   String domaci, String hoste, String vysledek) {
        this.idUtkani = idUtkani;
        this.jmenoR = jmenoR;
        this.prijmeniR = prijmeniR;
        this.idFacrR = idFacrR;
        this.domaci = domaci;
        this.hoste = hoste;
        this.vysledek = vysledek;
    }

    public Posudek(String idUtkani, String jmenoR, String prijmeniR, String idFacrR,
                   String domaci, String hoste, String vysledek, String roleR) {
        this.idUtkani = idUtkani;
        this.jmenoR = jmenoR;
        this.prijmeniR = prijmeniR;
        this.idFacrR = idFacrR;
        this.domaci = domaci;
        this.hoste = hoste;
        this.vysledek = vysledek;
        this.roleR = roleR;
    }

    public String getRoleR() {
        return roleR;
    }

    public void setRoleR(String roleR) {
        this.roleR = roleR;
    }

    public String getIdUtkani() {
        return idUtkani;
    }

    public void setIdUtkani(String idUtkani) {
        this.idUtkani = idUtkani;
    }

    public String getJmenoR() {
        return jmenoR;
    }

    public void setJmenoR(String jmenoR) {
        this.jmenoR = jmenoR;
    }

    public String getPrijmeniR() {
        return prijmeniR;
    }

    public void setPrijmeniR(String prijmeniR) {
        this.prijmeniR = prijmeniR;
    }

    public String getIdFacrR() {
        return idFacrR;
    }

    public void setIdFacrR(String idFacrR) {
        this.idFacrR = idFacrR;
    }

    public String getDomaci() {
        return domaci;
    }

    public void setDomaci(String domaci) {
        this.domaci = domaci;
    }

    public String getHoste() {
        return hoste;
    }

    public void setHoste(String hoste) {
        this.hoste = hoste;
    }

    public String getVysledek() {
        return vysledek;
    }

    public void setVysledek(String vysledek) {
        this.vysledek = vysledek;
    }
}
