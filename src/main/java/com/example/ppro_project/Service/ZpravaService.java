package com.example.ppro_project.Service;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Posudek;
import com.example.ppro_project.Model.Zprava;
import com.example.ppro_project.Repository.ClenRepository;
import com.example.ppro_project.Repository.ZpravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.DELEGAT;
import static com.example.ppro_project.Constants.Constants.R;

@Service
public class ZpravaService {

    private final ZpravaRepository zpravaRepository;

    @Autowired
    public ZpravaService(ZpravaRepository zpravaRepository) {
        this.zpravaRepository = zpravaRepository;
    }

    public Zprava getZpravaByIdUtkani(String idUtkani) {
        Zprava zprava = zpravaRepository.findByIdUtkani(idUtkani);
        if (zprava != null) {
            zprava.dekodujNastaveni();
        }
        return zprava;
    }

    public int getPocetZpravByIdDFA(int idDFA) {
        return zpravaRepository.countByIdDFA(idDFA);
    }

    public int getPocetZpravByIdR(int idR) {
        return zpravaRepository.countByIdR(idR);
    }

    public List<Zprava> getZpravyByIdDFA(int idDFA) {
        return zpravaRepository.findByIdDfa(idDFA);
    }

    public List<Zprava> getZpravyByIdRozhodci(int idR) {
        return zpravaRepository.findByIdRorIdAr1orIdAr2(idR, idR, idR);
    }

    public List<Posudek> getZpravyByIdDFARozpracovane(int idDFA) {
        List<Object[]> objects = zpravaRepository.findPosudekByDFARozpracovane(idDFA);
        List<Posudek> posudky = new ArrayList<>();
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String prijmeni = (String) object[index++];
            String jmeno = (String) object[index++];
            String idFacr = (String) object[index++];
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek));
        }
        return posudky;
    }

    public List<Posudek> getVsechnyPosudky() {
        List<Object[]> objects = zpravaRepository.findVsechnyPosudky();
        List<Posudek> posudky = new ArrayList<>();
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String idFacr = (String) object[index++];
            String prijmeni = (String) object[index++];
            String jmeno = (String) object[index++];
            String znamka = (String) object[index++];
            String znamka2 = (String) object[index++];
            String idFacrDFA = Objects.toString(object[index++], "");
            String prijmeniDFA = Objects.toString(object[index++], "");
            String jmenoDFA = Objects.toString(object[index++], "");
            String idFacrAR1 = Objects.toString(object[index++], "");
            String prijmeniAR1 = Objects.toString(object[index++], "");
            String jmenoAR1 = Objects.toString(object[index++], "");
            String idFacrAR2 = Objects.toString(object[index++], "");
            String prijmeniAR2 = Objects.toString(object[index++], "");
            String jmenoAR2 = Objects.toString(object[index], "");
            if(znamka.equals("7.8") || znamka.equals("7.9")){
                znamka = znamka + " (*" + znamka2 + ")";
            }
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek, jmenoDFA, prijmeniDFA, idFacrDFA, znamka,
                    jmenoAR1, prijmeniAR1, idFacrAR1,
                    jmenoAR2, prijmeniAR2, idFacrAR2));
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek, jmenoDFA, prijmeniDFA, idFacrDFA, znamka,
                    jmenoAR1, prijmeniAR1, idFacrAR1,
                    jmenoAR2, prijmeniAR2, idFacrAR2));
        }
        return posudky;
    }

    public List<Posudek> getPosudkyByIdDFA(int idDFA) {
        List<Object[]> objects = zpravaRepository.findPosudekByDFA(idDFA);
        List<Posudek> posudky = new ArrayList<>();
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String prijmeni = (String) object[index++];
            String jmeno = (String) object[index++];
            String idFacr = (String) object[index++];
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr, domaci,
                    hoste, vysledek));
        }
        return posudky;
    }

    public List<Posudek> getPosudkyByIdR(int idR, String idFacr) {
        List<Object[]> objects = zpravaRepository.findPosudekByR(idR);
        List<Posudek> posudky = new ArrayList<>();
        String prijmeni = null;
        String jmeno = null;
        String idFacr2 = null;
        String znamka = null;
        String znamka2 = null;
        for (Object[] object : objects) {
            int index = 0;
            String idUtkani = (String) object[index++];
            String vysledek = (String) object[index++];
            String domaci = (String) object[index++];
            String hoste = (String) object[index++];
            String roleR = (String) object[index++];
            String idFacrTemp = (String) object[index++];
            String prijmeniTemp = (String) object[index++];
            String jmenoTemp = (String) object[index++];
           // String idFacrDFA= (String) object[index++];
          //  String prijmeniDFA = (String) object[index++];
          //  String jmenoDFA = (String) object[index++];
            String znamkaTemp = (String) object[index++];
            String znamka2Temp = (String) object[index++];
            if(roleR.equals(R)){
                idFacr2 = idFacrTemp;
                prijmeni = prijmeniTemp;
                jmeno = jmenoTemp;
            }

            if(idFacr2 != null && Objects.equals(idFacrTemp, idFacr)){
                znamka = znamkaTemp;
                znamka2 = znamka2Temp;
            }

            if(idFacrTemp == null || znamka == null){
                continue;
            }
            posudky.add(new Posudek(idUtkani, jmeno, prijmeni, idFacr2, domaci,
                    hoste, vysledek, roleR));
            idFacr2 = null;
            znamka = null;

        }
        return posudky;
    }

    public Zprava save(Zprava zprava) {
        zprava.nastavDobuHry();
        return zpravaRepository.save(zprava);
    }
}
