package com.example.ppro_project.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Soutez")
public class Soutez {

    @Id
    @Column(name = "zkratka")
    public String zkratka;

    @Column(name = "soutez")
    public String soutez;

    @Column(name = "kategorie")
    public String kategorie;

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getSoutez() {
        return soutez;
    }

    public void setSoutez(String soutez) {
        this.soutez = soutez;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
}
