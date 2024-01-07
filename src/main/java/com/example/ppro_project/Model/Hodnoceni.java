package com.example.ppro_project.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Hodnoceni")
public class Hodnoceni {

    public Hodnoceni(){

    }

    public Hodnoceni(String roleR, int idZprava){
        this.roleR = roleR;
        this.idZprava = idZprava;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idZprava")
    public int idZprava;

    @Column(name = "roler")
    public String roleR;

    @Column(name = "znamka")
    public String znamka = "8.3";

    @Column(name = "znamka2")
    public String znamka2;

    @Column(name = "obtiznost")
    public String obtiznost = "Normální";

    @Transient
    public HodnoceniPopis[] hodnoceniPopisList;

    public HodnoceniPopis[] getHodnoceniPopisList() {
        return hodnoceniPopisList;
    }

    public void setHodnoceniPopisList(HodnoceniPopis[] hodnoceniPopisList) {
        this.hodnoceniPopisList = hodnoceniPopisList;
    }

    public void setIdHodnoceniToList(){
        for (int i = 0; i < hodnoceniPopisList.length; i++) {
            hodnoceniPopisList[i].idHodnoceni = getId();
        }
    }

    public String getZnamka2() {
        return znamka2;
    }

    public void setZnamka2(String znamka2) {
        this.znamka2 = znamka2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdZprava() {
        return idZprava;
    }

    public void setIdZprava(int idZprava) {
        this.idZprava = idZprava;
    }

    public String getRoleR() {
        return roleR;
    }

    public void setRoleR(String roleR) {
        this.roleR = roleR;
    }

    public String getZnamka() {
        return znamka;
    }

    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }

    public String getObtiznost() {
        return obtiznost;
    }

    public void setObtiznost(String obtiznost) {
        this.obtiznost = obtiznost;
    }
}
