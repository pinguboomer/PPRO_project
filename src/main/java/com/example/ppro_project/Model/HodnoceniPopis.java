package com.example.ppro_project.Model;

import jakarta.persistence.*;

import static java.lang.Float.isNaN;

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


    @Transient
    public String hodnoceniVlastnostInputStringMinuty1;

    @Transient
    public String hodnoceniVlastnostInputStringMinuty2;

    @Transient
    public String hodnoceniVlastnostInputStringMinuty3;

    @Transient
    public String hodnoceniVlastnostInputStringMinuty4;
    @Transient
    public String hodnoceniVlastnostInputStringMinuty5;

    @Transient
    public String hodnoceniVlastnostInputStringSituace1;

    @Transient
    public String hodnoceniVlastnostInputStringSituace2;

    @Transient
    public String hodnoceniVlastnostInputStringSituace3;

    @Transient
    public String hodnoceniVlastnostInputStringSituace4;

    @Transient
    public String hodnoceniVlastnostInputStringSituace5;

    public void setHodnoceniVlastnostMinutyASituaceToInput() {
        if(hodnoceniVlastnostArray == null){
            return;
        }
        for (int i = 0; i < hodnoceniVlastnostArray.length; i++) {
            if(hodnoceniVlastnostArray[i] == null){
                continue;
            }
            String minuta = hodnoceniVlastnostArray[i].minuta;
            String situace = hodnoceniVlastnostArray[i].situace;
            if(i == 0){
                hodnoceniVlastnostInputStringMinuty1 = minuta;
                hodnoceniVlastnostInputStringSituace1 = situace;
            } else if(i == 1){
                hodnoceniVlastnostInputStringMinuty2 = minuta;
                hodnoceniVlastnostInputStringSituace2 = situace;
            } else if(i == 2){
                hodnoceniVlastnostInputStringMinuty3 = minuta;
                hodnoceniVlastnostInputStringSituace3 = situace;
            } else if(i == 3){
                hodnoceniVlastnostInputStringMinuty4 = minuta;
                hodnoceniVlastnostInputStringSituace4 = situace;
            } else if(i == 4){
                hodnoceniVlastnostInputStringMinuty5 = minuta;
                hodnoceniVlastnostInputStringSituace5 = situace;
            }
        }
    }

    public void setHodnoceniVlastnostMinutyASituaceToArray() {
        if(hodnoceniVlastnostArray == null){
            return;
        }
        for (int i = 0; i < hodnoceniVlastnostArray.length; i++) {
            String minuta = "";
            String situace = "";
            if(i == 0){
                minuta = hodnoceniVlastnostInputStringMinuty1;
                situace = hodnoceniVlastnostInputStringSituace1;
            } else if(i == 1){
                minuta = hodnoceniVlastnostInputStringMinuty2;
                situace = hodnoceniVlastnostInputStringSituace2;
            } else if(i == 2){
                minuta = hodnoceniVlastnostInputStringMinuty3;
                situace = hodnoceniVlastnostInputStringSituace3;
            } else if(i == 3){
                minuta = hodnoceniVlastnostInputStringMinuty4;
                situace = hodnoceniVlastnostInputStringSituace4;
            } else if(i == 4){
                minuta = hodnoceniVlastnostInputStringMinuty5;
                situace = hodnoceniVlastnostInputStringSituace5;
            }
            hodnoceniVlastnostArray[i].minuta = minuta;
            hodnoceniVlastnostArray[i].situace = situace;
        }
    }

    public void getHodnoceniVlastnostArrayToInputValue() {
        hodnoceniVlastnostInputString = "";
        if(hodnoceniVlastnostArray == null){
            return;
        }
        for (int i = 0; i < hodnoceniVlastnostArray.length; i++) {
            hodnoceniVlastnostInputString += ",";
            if (hodnoceniVlastnostArray[i] != null) {
                if(hodnoceniVlastnostArray[i].idVlastnost > 0) {
                    hodnoceniVlastnostInputString += hodnoceniVlastnostArray[i].idVlastnost;
                    if (hodnoceniVlastnostArray[i].typ == 0) {
                        hodnoceniVlastnostInputString += "-";
                    } else {
                        hodnoceniVlastnostInputString += "+";
                    }
                }
            }
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

    public void dekodujInpuStringDoPoleVlastnosti() {
        // Rozdělíme vstupní řetězec podle čárky
        String[] items = hodnoceniVlastnostInputString.split(",");
        int index = 0;
        for (String item : items) {
            int jeKlad = 0;
            String idVlastnost = "";
            if (item.indexOf('+') != -1) {
                idVlastnost = item.replace("+", "");
                jeKlad = 1;
            } else if (item.indexOf('-') != -1) {
                idVlastnost = item.replace("-", "");
            }
            if (idVlastnost.isEmpty()) {
                continue;
            }
            int foo;
            try {
                foo = Integer.parseInt(idVlastnost);
            } catch (NumberFormatException e) {
                foo = -1;
            }
            if (foo < 0) {
                continue;
            }
            hodnoceniVlastnostArray[index] = new HodnoceniVlastnost();
            hodnoceniVlastnostArray[index].idPopis = id;
            hodnoceniVlastnostArray[index].idVlastnost = foo;
            hodnoceniVlastnostArray[index].typ = jeKlad;
            index++;
            if (index >= 5) {
                break;
            }
        }
        for (int i = index; i < 5; i++) {
            hodnoceniVlastnostArray[index++] = new HodnoceniVlastnost();
        }
    }

    public String getHodnoceniVlastnostInputStringMinuty1() {
        return hodnoceniVlastnostInputStringMinuty1;
    }

    public void setHodnoceniVlastnostInputStringMinuty1(String hodnoceniVlastnostInputStringMinuty1) {
        this.hodnoceniVlastnostInputStringMinuty1 = hodnoceniVlastnostInputStringMinuty1;
    }

    public String getHodnoceniVlastnostInputStringMinuty2() {
        return hodnoceniVlastnostInputStringMinuty2;
    }

    public void setHodnoceniVlastnostInputStringMinuty2(String hodnoceniVlastnostInputStringMinuty2) {
        this.hodnoceniVlastnostInputStringMinuty2 = hodnoceniVlastnostInputStringMinuty2;
    }

    public String getHodnoceniVlastnostInputStringMinuty3() {
        return hodnoceniVlastnostInputStringMinuty3;
    }

    public void setHodnoceniVlastnostInputStringMinuty3(String hodnoceniVlastnostInputStringMinuty3) {
        this.hodnoceniVlastnostInputStringMinuty3 = hodnoceniVlastnostInputStringMinuty3;
    }

    public String getHodnoceniVlastnostInputStringMinuty4() {
        return hodnoceniVlastnostInputStringMinuty4;
    }

    public void setHodnoceniVlastnostInputStringMinuty4(String hodnoceniVlastnostInputStringMinuty4) {
        this.hodnoceniVlastnostInputStringMinuty4 = hodnoceniVlastnostInputStringMinuty4;
    }

    public String getHodnoceniVlastnostInputStringMinuty5() {
        return hodnoceniVlastnostInputStringMinuty5;
    }

    public void setHodnoceniVlastnostInputStringMinuty5(String hodnoceniVlastnostInputStringMinuty5) {
        this.hodnoceniVlastnostInputStringMinuty5 = hodnoceniVlastnostInputStringMinuty5;
    }

    public String getHodnoceniVlastnostInputStringSituace1() {
        return hodnoceniVlastnostInputStringSituace1;
    }

    public void setHodnoceniVlastnostInputStringSituace1(String hodnoceniVlastnostInputStringSituace1) {
        this.hodnoceniVlastnostInputStringSituace1 = hodnoceniVlastnostInputStringSituace1;
    }

    public String getHodnoceniVlastnostInputStringSituace2() {
        return hodnoceniVlastnostInputStringSituace2;
    }

    public void setHodnoceniVlastnostInputStringSituace2(String hodnoceniVlastnostInputStringSituace2) {
        this.hodnoceniVlastnostInputStringSituace2 = hodnoceniVlastnostInputStringSituace2;
    }

    public String getHodnoceniVlastnostInputStringSituace3() {
        return hodnoceniVlastnostInputStringSituace3;
    }

    public void setHodnoceniVlastnostInputStringSituace3(String hodnoceniVlastnostInputStringSituace3) {
        this.hodnoceniVlastnostInputStringSituace3 = hodnoceniVlastnostInputStringSituace3;
    }

    public String getHodnoceniVlastnostInputStringSituace4() {
        return hodnoceniVlastnostInputStringSituace4;
    }

    public void setHodnoceniVlastnostInputStringSituace4(String hodnoceniVlastnostInputStringSituace4) {
        this.hodnoceniVlastnostInputStringSituace4 = hodnoceniVlastnostInputStringSituace4;
    }

    public String getHodnoceniVlastnostInputStringSituace5() {
        return hodnoceniVlastnostInputStringSituace5;
    }

    public void setHodnoceniVlastnostInputStringSituace5(String hodnoceniVlastnostInputStringSituace5) {
        this.hodnoceniVlastnostInputStringSituace5 = hodnoceniVlastnostInputStringSituace5;
    }
}