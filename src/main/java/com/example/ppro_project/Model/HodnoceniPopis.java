package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Hodnocenipopis")
public class HodnoceniPopis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idHodnoceni")
    public int idHodnoceni;

    @Column(name = "typ")
    public String typ;

    @Column(name = "celkovaznamka")
    public int celkovaZnamka = 1;

    @Column(name = "popis")
    public String popis;

    @Transient
    public HodnoceniVlastnost[] hodnoceniVlastnostArray;

    @Transient
    public String hodnoceniVlastnostInputString;


    public void setIdHodnoceniToArray() {
        for (int i = 0; i < hodnoceniVlastnostArray.length; i++) {
            hodnoceniVlastnostArray[i].idPopis = id;
        }
    }

    public void getHodnoceniVlastnostArrayToInputValue() {
        hodnoceniVlastnostInputString = "";
        if(hodnoceniVlastnostArray == null){
            return;
        }
        for (int i = 0; i < hodnoceniVlastnostArray.length; i++) {
            if (hodnoceniVlastnostArray[i] != null) {
                hodnoceniVlastnostInputString += hodnoceniVlastnostArray[i].idVlastnost;
                if (hodnoceniVlastnostArray[i].typ == 0) {
                    hodnoceniVlastnostInputString += "-";
                } else {
                    hodnoceniVlastnostInputString += "+";
                }
            }
            hodnoceniVlastnostInputString += ",";
        }
    }

    public HodnoceniVlastnost[] getHodnoceniVlastnostArray() {
        return hodnoceniVlastnostArray;
    }

    public void setHodnoceniVlastnostArray(HodnoceniVlastnost[] hodnoceniVlastnostArray) {
        this.hodnoceniVlastnostArray = hodnoceniVlastnostArray;
    }

    public int getId() {
        return id;
    }

    public HodnoceniPopis() {

    }

    public HodnoceniPopis(String typ) {
        this.typ = typ;
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

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getCelkovaZnamka() {
        return celkovaZnamka;
    }

    public void setCelkovaZnamka(int celkovaZnamka) {
        this.celkovaZnamka = celkovaZnamka;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getHodnoceniVlastnostInputString() {
        return hodnoceniVlastnostInputString;
    }

    public void setHodnoceniVlastnostInputString(String hodnoceniVlastnostInputString) {
        this.hodnoceniVlastnostInputString = hodnoceniVlastnostInputString;
    }
}