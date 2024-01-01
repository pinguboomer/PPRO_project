package com.example.ppro_project.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Soutez")
public class Soutez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zkratka")
    public String zkratka;

    @Column(name = "soutez")
    public String soutez;

    @Column(name = "kategorie")
    public String kategorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
