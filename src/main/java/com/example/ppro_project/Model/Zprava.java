package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Zprava")
public class Zprava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idUtkani")
    public int idUtkani;
    @Column(name = "idR")
    public int idR;
    @Column(name = "idAR1")
    public int idAR1;
    @Column(name = "idAR2")
    public int idAR2;
    @Column(name = "idTD")
    public int idTD;

    @Column(name = "dobahryprvnipolocas")
    public String dobaHryPrvniPolocas;

    @Column(name = "dobahrydruhypolocas")
    public String dobaHryDruhyPolocas;

    public int nastaveniPrvniPolocas = 0;

    public int nastaveniDruhyPolocas = 0;

    public void setIdAR1(int idAR1) {
        this.idAR1 = idAR1;
    }

    public int getNastaveniPrvniPolocas() {
        return nastaveniPrvniPolocas;
    }

    public void setNastaveniPrvniPolocas(int nastaveniPrvniPolocas) {
        this.nastaveniPrvniPolocas = nastaveniPrvniPolocas;
    }

    public int getNastaveniDruhyPolocas() {
        return nastaveniDruhyPolocas;
    }

    public void setNastaveniDruhyPolocas(int nastaveniDruhyPolocas) {
        this.nastaveniDruhyPolocas = nastaveniDruhyPolocas;
    }


    @Column(name = "vysledek")
    public String vysledek = "0:0";

    @Column(name = "polocas")
    public String polocas = "0:0";

    public int getIdUtkani() {
        return idUtkani;
    }

    public void setIdUtkani(int idUtkani) {
        this.idUtkani = idUtkani;
    }

    public String getVysledek() {
        return vysledek;
    }

    public void setVysledek(String vysledek) {
        this.vysledek = vysledek;
    }

    public String getPolocas() {
        return polocas;
    }

    public void setPolocas(String polocas) {
        this.polocas = polocas;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdAR1() {
        return idAR1;
    }

    public void setIdAAR1(int idAR1) {
        this.idAR1 = idAR1;
    }

    public int getIdAR2() {
        return idAR2;
    }

    public void setIdAR2(int idAR2) {
        this.idAR2 = idAR2;
    }

    public int getIdTD() {
        return idTD;
    }

    public void setIdTD(int idTD) {
        this.idTD = idTD;
    }

    public String getDobaHryPrvniPolocas() {
        return dobaHryPrvniPolocas;
    }

    public void setDobaHryPrvniPolocas(String dobaHryPrvniPolocas) {
        this.dobaHryPrvniPolocas = dobaHryPrvniPolocas;
    }

    public String getDobaHryDruhyPolocas() {
        return dobaHryDruhyPolocas;
    }

    public void setDobaHryDruhyPolocas(String dobaHryDruhyPolocas) {
        this.dobaHryDruhyPolocas = dobaHryDruhyPolocas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
