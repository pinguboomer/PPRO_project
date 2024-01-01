package com.example.ppro_project.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Utkani")
public class Utkani {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idUtkani")
    public String idUtkani;

    @Column(name = "datum")
    public Date datum;

    @Column(name = "domaci")
    public String domaci;

    @Column(name = "hoste")
    public String hoste;

    public String getIdUtkani() {
        return idUtkani;
    }

    public void setIdUtkani(String idUtkani) {
        this.idUtkani = idUtkani;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
