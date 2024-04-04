package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Zprava")
public class Zprava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idUtkani")
    public String idUtkani;
    @Column(name = "id_R")
    public int idR;
    @Column(name = "idAr1")
    public int idAR1;
    @Column(name = "idAr2")
    public int idAR2;
    @Column(name = "idDfa")
    public int idDFA;
    @Column(name = "idTd")
    public int idTD;
    @Column(name = "stav")
    public int stav;

    @Column(name = "dobahryprvnipolocas")
    public String dobaHryPrvniPolocas;

    @Column(name = "dobahrydruhypolocas")
    public String dobaHryDruhyPolocas;


    @Column(name = "vysledek")
    public String vysledek = "0:0";

    @Column(name = "polocas")
    public String polocas = "0:0";

    @Column(name = "pk")
    public boolean pk;
    @Column(name = "ck")
    public boolean ck;
    @Column(name = "zaznam")
    public boolean zaznam;
    @Column(name = "poradatele")
    public boolean poradatele;
    @Column(name = "stk")
    public boolean stk;
    @Column(name = "dk")
    public boolean dk;

    @Column(name = "kr")
    public boolean kr;
    @Column(name = "divaci")
    public boolean divaci;
    @Column(name = "zraneni")
    public boolean zraneni;
    @Column(name = "konfrontace")
    public boolean konfrontace;

    @Transient
    public int nastaveniPrvniPolocas = 0;

    @Transient
    public int nastaveniDruhyPolocas = 0;



    public void nastavDobuHry() {
        dobaHryPrvniPolocas = "45+" + nastaveniPrvniPolocas;
        dobaHryDruhyPolocas = "45+" + nastaveniDruhyPolocas;
    }

    public void dekodujNastaveni() {
        nastaveniPrvniPolocas = 0;
        nastaveniDruhyPolocas = 0;
        String[] casti = dobaHryPrvniPolocas.split("\\+");
        if(casti.length > 1){
            String hodnotaPoPlusu = casti[1];
            nastaveniPrvniPolocas = Integer.parseInt(hodnotaPoPlusu);
        }
        String[] casti2 = dobaHryDruhyPolocas.split("\\+");
        if(casti2.length > 1){
            String hodnotaPoPlusu = casti2[1];
            nastaveniDruhyPolocas = Integer.parseInt(hodnotaPoPlusu);
        }
    }

    public int getIdDFA() {
        return idDFA;
    }

    public void setIdDFA(int idDFA) {
        this.idDFA = idDFA;
    }

    public int getStav() {
        return stav;
    }

    public void setStav(int stav) {
        this.stav = stav;
    }

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

    public String getIdUtkani() {
        return idUtkani;
    }

    public void setIdUtkani(String idUtkani) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public boolean isCk() {
        return ck;
    }

    public void setCk(boolean ck) {
        this.ck = ck;
    }

    public boolean isZaznam() {
        return zaznam;
    }

    public void setZaznam(boolean zaznam) {
        this.zaznam = zaznam;
    }

    public boolean isPoradatele() {
        return poradatele;
    }

    public void setPoradatele(boolean poradatele) {
        this.poradatele = poradatele;
    }

    public boolean isStk() {
        return stk;
    }

    public void setStk(boolean stk) {
        this.stk = stk;
    }

    public boolean isDk() {
        return dk;
    }

    public void setDk(boolean dk) {
        this.dk = dk;
    }

    public boolean isKr() {
        return kr;
    }

    public void setKr(boolean kr) {
        this.kr = kr;
    }

    public boolean isDivaci() {
        return divaci;
    }

    public void setDivaci(boolean divaci) {
        this.divaci = divaci;
    }

    public boolean isZraneni() {
        return zraneni;
    }

    public void setZraneni(boolean zraneni) {
        this.zraneni = zraneni;
    }

    public boolean isKonfrontace() {
        return konfrontace;
    }

    public void setKonfrontace(boolean konfrontace) {
        this.konfrontace = konfrontace;
    }
}
