package com.example.ppro_project.Model;

import java.util.ArrayList;

public class KompletniZprava {

    public Zprava zprava;

    public Utkani utkani;
    public Soutez soutez;
    public Hodnoceni hodnoceniR;
    public Hodnoceni hodnoceniAR1;
    public Hodnoceni hodnoceniAR2;

    public Clen r;
    public Clen ar1;
    public Clen ar2;
    public Clen dfa;
    public Clen td;

    public KompletniZprava() {
        utkani = new Utkani();
        zprava = new Zprava();
        soutez = new Soutez();
        r = new Clen();
        ar1 = new Clen();
        ar2 = new Clen();
        dfa = new Clen();
        td = new Clen();
        hodnoceniR = new Hodnoceni();
        hodnoceniAR1 = new Hodnoceni();
        hodnoceniAR2 = new Hodnoceni();

        hodnoceniR.hodnoceniPopisList = new HodnoceniPopis[8];
        hodnoceniAR1.hodnoceniPopisList = new HodnoceniPopis[2];
        hodnoceniAR2.hodnoceniPopisList = new HodnoceniPopis[2];

        naplnListPopisuProR();
        naplnListPopisuProAR(true);
        naplnListPopisuProAR(false);
    }

    private void naplnListPopisuProAR(boolean ar1) {

        if (ar1) {
            hodnoceniAR1.hodnoceniPopisList[0] = new HodnoceniPopis("A1");
            hodnoceniAR1.hodnoceniPopisList[1] = new HodnoceniPopis("A2");
            hodnoceniAR1.hodnoceniPopisList[0].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
            hodnoceniAR1.hodnoceniPopisList[1].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
        } else {
            hodnoceniAR2.hodnoceniPopisList[0] = new HodnoceniPopis("A1");
            hodnoceniAR2.hodnoceniPopisList[1] = new HodnoceniPopis("A2");
            hodnoceniAR2.hodnoceniPopisList[0].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
            hodnoceniAR2.hodnoceniPopisList[1].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
        }

    }

    private void naplnListPopisuProR() {
        hodnoceniR.hodnoceniPopisList[0] = new HodnoceniPopis("R1");
        hodnoceniR.hodnoceniPopisList[1] = new HodnoceniPopis("R2");
        hodnoceniR.hodnoceniPopisList[2] = new HodnoceniPopis("R3");
        hodnoceniR.hodnoceniPopisList[3] = new HodnoceniPopis("R4");

        hodnoceniR.hodnoceniPopisList[4] = new HodnoceniPopis("KS");

        hodnoceniR.hodnoceniPopisList[5] = new HodnoceniPopis("OP");
        hodnoceniR.hodnoceniPopisList[6] = new HodnoceniPopis("OD");
        hodnoceniR.hodnoceniPopisList[7] = new HodnoceniPopis("OJ");

        hodnoceniR.hodnoceniPopisList[0].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
        hodnoceniR.hodnoceniPopisList[1].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
        hodnoceniR.hodnoceniPopisList[2].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
        hodnoceniR.hodnoceniPopisList[3].hodnoceniVlastnostArray = new HodnoceniVlastnost[5];
    }

    public Zprava getZprava() {
        return zprava;
    }

    public void setZprava(Zprava zprava) {
        this.zprava = zprava;
    }

    public Soutez getSoutez() {
        return soutez;
    }

    public void setSoutez(Soutez soutez) {
        this.soutez = soutez;
    }

    public Utkani getUtkani() {
        return utkani;
    }

    public void setUtkani(Utkani utkani) {
        this.utkani = utkani;
    }

    public Hodnoceni getHodnoceniR() {
        return hodnoceniR;
    }

    public void setHodnoceniR(Hodnoceni hodnoceniR) {
        this.hodnoceniR = hodnoceniR;
    }

    public Hodnoceni getHodnoceniAR1() {
        return hodnoceniAR1;
    }

    public void setHodnoceniAR1(Hodnoceni hodnoceniAR1) {
        this.hodnoceniAR1 = hodnoceniAR1;
    }

    public Hodnoceni getHodnoceniAR2() {
        return hodnoceniAR2;
    }

    public void setHodnoceniAR2(Hodnoceni hodnoceniAR2) {
        this.hodnoceniAR2 = hodnoceniAR2;
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

    public void prevedIdClenuDoZpravy() {
        zprava.idR = r.getId();
        zprava.idAR1 = ar1.getId();
        zprava.idAR2 = ar2.getId();
        zprava.idDFA = dfa.getId();
        zprava.idTD = td.getId();
    }
}
