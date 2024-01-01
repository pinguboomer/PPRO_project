package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Vlastnost")
public class Vlastnost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "popis")
    private String popis;

    @Column(name = "kategorie")
    private int kategorie;

    public int getKategorie() {
        return kategorie;
    }

    public void setKategorie(int kategorie) {
        this.kategorie = kategorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
}
