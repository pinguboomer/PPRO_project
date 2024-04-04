package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Hodnocenivlastnost")
public class HodnoceniVlastnost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idPopis")
    public int idPopis;

    @Column(name = "idVlastnost")
    public int idVlastnost;

    @Column(name = "typ")
    public int typ;

    @Column(name = "idClen")
    public int idClen;

    @Column(name = "minuta")
    public String minuta;

    @Column(name = "situace")
    public String situace;

    public HodnoceniVlastnost(){

    }
    public HodnoceniVlastnost(int idPopis, int idVlastnost, int typ, String minuta, String situace) {
        this.idPopis = idPopis;
        this.idVlastnost = idVlastnost;
        this.typ = typ;
        this.minuta = minuta;
        this.situace = situace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPopis() {
        return idPopis;
    }

    public void setIdPopis(int idPopis) {
        this.idPopis = idPopis;
    }

    public int getIdVlastnost() {
        return idVlastnost;
    }

    public void setIdVlastnost(int idVlastnost) {
        this.idVlastnost = idVlastnost;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }


    public int getIdClen() {
        return idClen;
    }

    public void setIdClen(int idClen) {
        this.idClen = idClen;
    }

    public String getMinuta() {
        return minuta;
    }

    public void setMinuta(String minuta) {
        this.minuta = minuta;
    }

    public String getSituace() {
        return situace;
    }

    public void setSituace(String situace) {
        this.situace = situace;
    }
}