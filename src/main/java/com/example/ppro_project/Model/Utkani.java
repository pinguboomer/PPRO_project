package com.example.ppro_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "Utkani")
public class Utkani {

    @Id
    @Column(name = "idUtkani")
    public String idUtkani;

    @Column(name = "datum")
    public Date datum;

    @Column(name = "domaci")
    public String domaci;

    @Column(name = "hoste")
    public String hoste;

    @Column(name = "stadion")
    public String stadion;


    public void dekodujKoloZIDUtkani(String idUtkani) {
        if (idUtkani.length() > 4) {
            String koloStr = idUtkani.substring(idUtkani.length() - 4, idUtkani.length() - 2);
            try {
                kolo = Integer.parseInt(koloStr);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public String minutyDvojciferne(){
        return String.format("%02d", datum.getMinutes());
    }

    @Transient
    public int kolo;

    public int getKolo() {
        return kolo;
    }

    public void setKolo(int kolo) {
        this.kolo = kolo;
    }

    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

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

}
