package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Hodnocenivlastnost")
public class HodnoceniVlastnost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idHodnoceni")
    public int idHodnoceni;

    @Column(name = "idVlastnost")
    public int idVlastnost;

    @Column(name = "typ")
    public int typ;

    public HodnoceniVlastnost(){

    }
    public HodnoceniVlastnost(int idHodnoceni, int idVlastnost, int typ) {
        this.idHodnoceni = idHodnoceni;
        this.idVlastnost = idVlastnost;
        this.typ = typ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHodnoceni() {
        return idHodnoceni;
    }

    public void setIdHodnoceni(int idHodnoceni) {
        this.idHodnoceni = idHodnoceni;
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
}