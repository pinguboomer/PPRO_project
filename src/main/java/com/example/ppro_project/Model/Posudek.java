package com.example.ppro_project.Model;

public class Posudek {

    public String idUtkani;
    public String jmenoR;
    public String prijmeniR;
    public String idFacrR;
    public String jmenoDFA;
    public String prijmeniDFA;
    public String idFacrDFA;

    public String jmenoAR1;
    public String prijmeniAR1;
    public String idFacrAR1;

    public String jmenoAR2;
    public String prijmeniAR2;
    public String idFacrAR2;
    public String roleR;

    public String domaci;

    public String hoste;
    public String vysledek;

    public String znamka = "-";

    public Posudek(){

    }

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
                   String domaci, String hoste, String vysledek,
                   String jmenoDFA, String prijmeniDFA, String idFacrDFA, String znamka,
                   String jmenoAR1, String prijmeniAR1, String idFacrAR1,
                   String jmenoAR2, String prijmeniAR2, String idFacrAR2) {
        this.idUtkani = idUtkani;
        this.jmenoR = jmenoR;
        this.prijmeniR = prijmeniR;
        this.idFacrR = idFacrR;
        this.domaci = domaci;
        this.hoste = hoste;
        this.vysledek = vysledek;
        this.jmenoDFA = jmenoDFA;
        this.prijmeniDFA = prijmeniDFA;
        this.idFacrDFA = idFacrDFA;
        this.znamka = znamka;
        this.jmenoAR1 = jmenoAR1;
        this.prijmeniAR1 = prijmeniAR1;
        this.idFacrAR1 = idFacrAR1;
        this.jmenoAR2 = jmenoAR2;
        this.prijmeniAR2 = prijmeniAR2;
        this.idFacrAR2 = idFacrAR2;
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

    public String getZnamka() {
        return znamka;
    }

    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }

    public String getJmenoDFA() {
        return jmenoDFA;
    }

    public void setJmenoDFA(String jmenoDFA) {
        this.jmenoDFA = jmenoDFA;
    }

    public String getPrijmeniDFA() {
        return prijmeniDFA;
    }

    public void setPrijmeniDFA(String prijmeniDFA) {
        this.prijmeniDFA = prijmeniDFA;
    }

    public String getIdFacrDFA() {
        return idFacrDFA;
    }

    public void setIdFacrDFA(String idFacrDFA) {
        this.idFacrDFA = idFacrDFA;
    }

    public String getJmenoAR1() {
        return jmenoAR1;
    }

    public void setJmenoAR1(String jmenoAR1) {
        this.jmenoAR1 = jmenoAR1;
    }

    public String getPrijmeniAR1() {
        return prijmeniAR1;
    }

    public void setPrijmeniAR1(String prijmeniAR1) {
        this.prijmeniAR1 = prijmeniAR1;
    }

    public String getIdFacrAR1() {
        return idFacrAR1;
    }

    public void setIdFacrAR1(String idFacrAR1) {
        this.idFacrAR1 = idFacrAR1;
    }

    public String getJmenoAR2() {
        return jmenoAR2;
    }

    public void setJmenoAR2(String jmenoAR2) {
        this.jmenoAR2 = jmenoAR2;
    }

    public String getPrijmeniAR2() {
        return prijmeniAR2;
    }

    public void setPrijmeniAR2(String prijmeniAR2) {
        this.prijmeniAR2 = prijmeniAR2;
    }

    public String getIdFacrAR2() {
        return idFacrAR2;
    }

    public void setIdFacrAR2(String idFacrAR2) {
        this.idFacrAR2 = idFacrAR2;
    }
}
