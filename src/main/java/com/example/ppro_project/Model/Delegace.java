package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Delegace")
public class Delegace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUtkani")
    private Utkani utkani;

    @ManyToOne
    @JoinColumn(name = "idR")
    private Clen r;

    @ManyToOne
    @JoinColumn(name = "idAr1")
    private Clen ar1;

    @ManyToOne
    @JoinColumn(name = "idAr2")
    private Clen ar2;

    @ManyToOne
    @JoinColumn(name = "idDfa")
    private Clen dfa;

    @ManyToOne
    @JoinColumn(name = "idTd")
    private Clen td;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utkani getUtkani() {
        return utkani;
    }

    public void setUtkani(Utkani utkani) {
        this.utkani = utkani;
    }

    public Clen getR() {
        return r;
    }

    public void setR(Clen r) {
        this.r = r;
    }

    public Clen getAr1() {
        return ar1;
    }

    public void setAr1(Clen ar1) {
        this.ar1 = ar1;
    }

    public Clen getAr2() {
        return ar2;
    }

    public void setAr2(Clen ar2) {
        this.ar2 = ar2;
    }

    public Clen getDfa() {
        return dfa;
    }

    public void setDfa(Clen dfa) {
        this.dfa = dfa;
    }

    public Clen getTd() {
        return td;
    }

    public void setTd(Clen td) {
        this.td = td;
    }
}