package com.example.ppro_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "Clen")
public class Clen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "idFacr")
    public String idFacr;

    @Column(name = "heslo")
    public String heslo;

    @Column(name = "jmeno")
    public String jmeno;

    @Column(name = "prijmeni")
    public String prijmeni;

    @Column(name = "telefon")
    public String telefon;

    @Column(name = "email")
    public String email;

    @Column(name = "role")
    public String role;

    @Column(name = "jednotka")
    public String jednotka;

    @Column(name = "mesto")
    public String mesto;

    @Column(name = "psc")
    public String psc;


    @Column(name = "ulice")
    public String ulice;

    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getIdFacr() {
        return idFacr;
    }

    public void setIdFacr(String idFacr) {
        this.idFacr = idFacr;
    }


}
