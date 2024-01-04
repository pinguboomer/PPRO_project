package com.example.ppro_project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Hodnoceni")
public class Hodnoceni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idZprava")
    public int idZprava;

    @Column(name = "roler")
    public String roleR;

    @Column(name = "znamka")
    public String znamka = "8.3";

    public String getZnamkaHCH() {
        return znamkaHCH;
    }

    public void setZnamkaHCH(String znamkaHCH) {
        this.znamkaHCH = znamkaHCH;
    }

    public String znamkaHCH;

    @Column(name = "obtiznost")
    public String obtiznost = "Normální";

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
