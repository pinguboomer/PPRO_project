package com.example.ppro_project.Controller;

import com.example.ppro_project.Model.*;
import com.example.ppro_project.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.ClenController.*;
import static com.example.ppro_project.Controller.HodnoceniController.*;
import static com.example.ppro_project.Controller.HodnoceniPopisController.hodnoceniPopisService;
import static com.example.ppro_project.Controller.HodnoceniVlastnostController.hodnoceniVlastnostService;
import static com.example.ppro_project.Controller.SoutezController.soutezService;
import static com.example.ppro_project.Controller.UtkaniController.hledaneUtkani;
import static com.example.ppro_project.Controller.UtkaniController.utkaniService;
import static com.example.ppro_project.Controller.VlastnostController.*;

@Controller
public class ZpravaController {

    public static ZpravaService zpravaService;
    public static KompletniZprava kompletniZprava;


    @Autowired
    public ZpravaController(ZpravaService zpravaService) {
        this.zpravaService = zpravaService;
    }


    @GetMapping("/nova_zprava")
    public String novaZprava(Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            kompletniZprava = null;
            return "redirect:/";
        }

        if (kompletniZprava.utkani == null) {
            vynulujParametryZpravy();
        }

        pridejAtributyDoModelu(model);

        return "nova_zprava";
    }


    @PostMapping("/nova_zprava/vyhledejUtkani")
    public String vyhledejUtkani(@Valid @ModelAttribute("utkani") Utkani utkani,
                                 BindingResult br, Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            kompletniZprava = null;
            return "redirect:/";
        }
        hledaneUtkani = utkani;
        pridejAtributyDoModelu(model);
        if (utkani == null || utkani.idUtkani == null || utkani.idUtkani.isEmpty()) {
            br.rejectValue("idUtkani", "error.user", "Chybné údaje");
            kompletniZprava = new KompletniZprava();
            return "nova_zprava";
        }
        Utkani utkaniNalezene = utkaniService.getUtkaniByIdUtkani(utkani.idUtkani);
        if (utkaniNalezene == null) {
            br.rejectValue("idUtkani", "error.user", "Zpráva nenalezena");
            kompletniZprava = new KompletniZprava();
            return "nova_zprava";
        }
        Zprava zprava = zpravaService.getZpravaByIdUtkani(utkani.idUtkani);
        if (zprava != null && zprava.idDFA != prihlasenyUzivatel.getId()) {
            br.rejectValue("idUtkani", "error.user",
                    "Na toto utkání již napsal/píše zprávu jiný delegát");
            kompletniZprava = new KompletniZprava();
            return "nova_zprava";
        }

        if (zprava == null) {
            kompletniZprava = new KompletniZprava();
            kompletniZprava.zprava = new Zprava();
            kompletniZprava.zprava.idUtkani = utkani.idUtkani;
            kompletniZprava.utkani = utkaniNalezene;
            kompletniZprava.dfa = prihlasenyUzivatel;
            kompletniZprava.zprava.idDFA = prihlasenyUzivatel.getId();
        } else {
            kompletniZprava.zprava = zprava;
            List<Hodnoceni> hodnoceni = hodnoceniService.getHodnoceniByIdZprava(zprava.getId());
            if (hodnoceni.isEmpty()) {
                kompletniZprava.hodnoceniR = new Hodnoceni(R, zprava.getId());
                kompletniZprava.hodnoceniAR1 = new Hodnoceni(AR1, zprava.getId());
                kompletniZprava.hodnoceniAR2 = new Hodnoceni(AR2, zprava.getId());
            } else {
                Clen rozhodci = clenService.getClenById(zprava.idR);
                Clen AR1 = clenService.getClenById(zprava.idAR1);
                Clen AR2 = clenService.getClenById(zprava.idAR2);
                Clen dfa = clenService.getClenById(zprava.idDFA);
                Clen TD = clenService.getClenById(zprava.idTD);
                nastavRozhodciVeZprave(rozhodci, AR1, AR2, dfa, TD);
                for (Hodnoceni hodnoceniVal : hodnoceni) {
                    dekodujHodnoceniDoZpravy(hodnoceniVal);
                    List<HodnoceniPopis> hodnoceniPopis =
                            hodnoceniPopisService.getByIdHodnoceni(hodnoceniVal.getId());
                    if (!hodnoceniPopis.isEmpty()) {
                        dekodujDoHodnoceniPopis(hodnoceniPopis, hodnoceniVal);
                    }
                }
            }

        }
        Soutez soutezNalezena = soutezService.getSoutezByZkratka(utkani.idUtkani);
        utkaniNalezene.dekodujKoloZIDUtkani(utkaniNalezene.idUtkani);
        hledaneUtkani = utkaniNalezene;
        kompletniZprava.utkani = utkaniNalezene;
        kompletniZprava.soutez = soutezNalezena;
        model.addAttribute("kompletniZprava", kompletniZprava);
        return "redirect:/nova_zprava";
    }

    private void dekodujDoHodnoceniPopis(List<HodnoceniPopis> hodnoceniPopis, Hodnoceni hodnoceniVal) {
        for (int i = 0; i < hodnoceniPopis.size(); i++) {
            if (Objects.equals(hodnoceniVal.roleR, R)) {
                if (i < 8) {
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[i] = hodnoceniPopis.get(i);
                    if (i < 5) {
                        kompletniZprava.hodnoceniR.hodnoceniPopisList[i].hodnoceniVlastnostArray
                                = new HodnoceniVlastnost[5];
                        List<HodnoceniVlastnost> hodnoceniVlastnost =
                                hodnoceniVlastnostService.
                                        getVlastnostiIdByIdPopis(hodnoceniPopis.get(i).getId());
                        if (!hodnoceniVlastnost.isEmpty()) {
                            for (int j = 0; j < hodnoceniVlastnost.size(); j++) {
                                if (j < 5) {
                                    kompletniZprava.hodnoceniR.hodnoceniPopisList[i].
                                            hodnoceniVlastnostArray[j] = hodnoceniVlastnost.get(j);
                                }
                            }
                        }
                    }
                }
                kompletniZprava.hodnoceniR.hodnoceniPopisList[i].getHodnoceniVlastnostArrayToInputValue();
            }
            if (Objects.equals(hodnoceniVal.roleR, AR1)) {
                if (i < 2) {
                    kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i] = hodnoceniPopis.get(i);
                    kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].hodnoceniVlastnostArray
                            = new HodnoceniVlastnost[5];
                    List<HodnoceniVlastnost> hodnoceniVlastnost =
                            hodnoceniVlastnostService.
                                    getVlastnostiIdByIdPopis(hodnoceniPopis.get(i).getId());
                    if (!hodnoceniVlastnost.isEmpty()) {
                        for (int j = 0; j < hodnoceniVlastnost.size(); j++) {
                            if (j < 5) {
                                kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].
                                        hodnoceniVlastnostArray[j] = hodnoceniVlastnost.get(j);
                            }
                        }
                    }
                }
                kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].getHodnoceniVlastnostArrayToInputValue();
            }
            if (Objects.equals(hodnoceniVal.roleR, AR2)) {
                if (i < 2) {
                    kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i] = hodnoceniPopis.get(i);
                    kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].hodnoceniVlastnostArray
                            = new HodnoceniVlastnost[5];
                    List<HodnoceniVlastnost> hodnoceniVlastnost =
                            hodnoceniVlastnostService.
                                    getVlastnostiIdByIdPopis(hodnoceniPopis.get(i).getId());
                    if (!hodnoceniVlastnost.isEmpty()) {
                        for (int j = 0; j < hodnoceniVlastnost.size(); j++) {
                            if (j < 5) {
                                kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].
                                        hodnoceniVlastnostArray[j] = hodnoceniVlastnost.get(j);
                            }
                        }
                    }
                }
                kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].getHodnoceniVlastnostArrayToInputValue();
            }
        }
    }


    @PostMapping("/nova_zprava/ulozit")
    public String ulozZpravu(@Valid @ModelAttribute("kompletniZprava")
                             KompletniZprava kompletniZpravaModel,
                             BindingResult br, Model model) {
        if (!jePrihlasenUzivatel() || prihlasenyUzivatel.getRole() == ROZHODCI) {
            kompletniZprava = null;
            return "redirect:/";
        }
        if (kompletniZpravaModel.zprava == null) {
            return "nova_zprava";
        }
        int idZprava = kompletniZprava.zprava.getId();
        kompletniZprava.zprava = kompletniZpravaModel.zprava;
        kompletniZprava.zprava.setId(idZprava);
        kompletniZprava.dfa = prihlasenyUzivatel;
        dekodujDelegovaneRozhodci(kompletniZpravaModel);
        kompletniZprava.prevedIdClenuDoZpravy();
        kompletniZprava.zprava.idUtkani = kompletniZprava.utkani.idUtkani;
        kompletniZprava.zprava = zpravaService.save(kompletniZprava.zprava);

        dekodujHodnoceniDoZpravy(kompletniZpravaModel);
        kompletniZprava.hodnoceniR = hodnoceniService.save(kompletniZprava.hodnoceniR);
        kompletniZprava.hodnoceniAR1 = hodnoceniService.save(kompletniZprava.hodnoceniAR1);
        kompletniZprava.hodnoceniAR2 = hodnoceniService.save(kompletniZprava.hodnoceniAR2);

        kompletniZprava.hodnoceniR.setIdHodnoceniToList();
        kompletniZprava.hodnoceniAR1.setIdHodnoceniToList();
        kompletniZprava.hodnoceniAR2.setIdHodnoceniToList();

        dekodujHodnoceniPopis(kompletniZpravaModel);
        ulozVsechnyPopisy();

        pridejAtributyDoModelu(model);
        return "redirect:/nova_zprava";
    }

    private void ulozVsechnyPopisy() {
        HodnoceniPopis[] hodnoceniPopisTemp = new HodnoceniPopis[5];
        for (int i = 0; i < kompletniZprava.hodnoceniR.hodnoceniPopisList.length; i++) {
            hodnoceniPopisTemp[i] =
                    hodnoceniPopisService.save(kompletniZprava.hodnoceniR.hodnoceniPopisList[i]);
        }
        kompletniZprava.hodnoceniR.hodnoceniPopisList = hodnoceniPopisTemp;
        for (int i = 0; i < kompletniZprava.hodnoceniAR1.hodnoceniPopisList.length; i++) {
            hodnoceniPopisTemp[i] =
                    hodnoceniPopisService.save(kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i]);
        }
        kompletniZprava.hodnoceniAR1.hodnoceniPopisList = hodnoceniPopisTemp;
        for (int i = 0; i < kompletniZprava.hodnoceniAR2.hodnoceniPopisList.length; i++) {
            hodnoceniPopisTemp[i] =
                    hodnoceniPopisService.save(kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i]);
        }
        kompletniZprava.hodnoceniAR2.hodnoceniPopisList = hodnoceniPopisTemp;
    }

    private void dekodujHodnoceniPopis(KompletniZprava kompletniZpravaModel) {
        for (int i = 0; i < kompletniZprava.hodnoceniR.hodnoceniPopisList.length; i++) {
            kompletniZprava.hodnoceniR.hodnoceniPopisList[i].popis =
                    kompletniZpravaModel.hodnoceniR.hodnoceniPopisList[i].popis;
            kompletniZprava.hodnoceniR.hodnoceniPopisList[i].celkovaZnamka =
                    kompletniZpravaModel.hodnoceniR.hodnoceniPopisList[i].celkovaZnamka;
        }
        for (int i = 0; i < kompletniZprava.hodnoceniAR1.hodnoceniPopisList.length; i++) {
            kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].popis =
                    kompletniZpravaModel.hodnoceniAR1.hodnoceniPopisList[i].popis;
            kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].celkovaZnamka =
                    kompletniZpravaModel.hodnoceniAR1.hodnoceniPopisList[i].celkovaZnamka;
        }
        for (int i = 0; i < kompletniZprava.hodnoceniAR2.hodnoceniPopisList.length; i++) {
            kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].popis =
                    kompletniZpravaModel.hodnoceniAR2.hodnoceniPopisList[i].popis;
            kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].celkovaZnamka =
                    kompletniZpravaModel.hodnoceniAR2.hodnoceniPopisList[i].celkovaZnamka;
        }
    }

    private void nastavRozhodciVeZprave(Clen rozhodci, Clen AR1, Clen AR2, Clen DFA, Clen TD) {
        if (rozhodci != null) {
            kompletniZprava.r = rozhodci;
        } else {
            kompletniZprava.r = new Clen();
        }
        if (AR1 != null) {
            kompletniZprava.ar1 = AR1;
        } else {
            kompletniZprava.ar1 = new Clen();
        }
        if (AR2 != null) {
            kompletniZprava.ar2 = AR2;
        } else {
            kompletniZprava.ar2 = new Clen();
        }
        if (DFA != null) {
            kompletniZprava.dfa = DFA;
        } else {
            kompletniZprava.dfa = new Clen();
        }
        if (TD != null) {
            kompletniZprava.td = TD;
        } else {
            kompletniZprava.td = new Clen();
        }
    }

    private void vynulujParametryZpravy() {
        kompletniZprava = new KompletniZprava();

        hledaneUtkani = new Utkani();
    }

    private void pridejAtributyDoModelu(Model model) {
        model.addAttribute("clen", prihlasenyUzivatel);
        model.addAttribute("utkani", hledaneUtkani);
        model.addAttribute("kompletniZprava", kompletniZprava);
        model.addAttribute("rozhodciList", rozhodciList);
        model.addAttribute("delegatiList", delegatiList);
        model.addAttribute("vlastnostiListPF", vlastnostiListPF);
        model.addAttribute("vlastnostiListOT", vlastnostiListOT);
        model.addAttribute("vlastnostiListFyzicka", vlastnostiListFyzicka);
        model.addAttribute("vlastnostiListSpoluprace", vlastnostiListSpoluprace);
        model.addAttribute("vlastnostiListKomentar", vlastnostiListKomentar);
        model.addAttribute("vlastnostiListARPF", vlastnostiListARPF);
        model.addAttribute("vlastnostiListARPohyb", vlastnostiListARPohyb);
    }

    private void dekodujHodnoceniDoZpravy(KompletniZprava kompletniZpravaModel) {
        kompletniZprava.hodnoceniR.obtiznost = kompletniZpravaModel.hodnoceniR.obtiznost;
        kompletniZprava.hodnoceniR.znamka = kompletniZpravaModel.hodnoceniR.znamka;
        kompletniZprava.hodnoceniR.znamka2 = kompletniZpravaModel.hodnoceniR.znamka2;
        kompletniZprava.hodnoceniR.idZprava = kompletniZprava.zprava.getId();
        kompletniZprava.hodnoceniR.roleR = R;

        kompletniZprava.hodnoceniAR1.obtiznost = kompletniZpravaModel.hodnoceniAR1.obtiznost;
        kompletniZprava.hodnoceniAR1.znamka = kompletniZpravaModel.hodnoceniAR1.znamka;
        kompletniZprava.hodnoceniAR1.znamka2 = kompletniZpravaModel.hodnoceniAR1.znamka2;
        kompletniZprava.hodnoceniAR1.idZprava = kompletniZprava.zprava.getId();
        kompletniZprava.hodnoceniAR1.roleR = AR1;

        kompletniZprava.hodnoceniAR2.obtiznost = kompletniZpravaModel.hodnoceniAR2.obtiznost;
        kompletniZprava.hodnoceniAR2.znamka = kompletniZpravaModel.hodnoceniAR2.znamka;
        kompletniZprava.hodnoceniAR2.znamka2 = kompletniZpravaModel.hodnoceniAR2.znamka2;
        kompletniZprava.hodnoceniAR2.idZprava = kompletniZprava.zprava.getId();
        kompletniZprava.hodnoceniAR2.roleR = AR2;
    }

    private void dekodujHodnoceniDoZpravy(Hodnoceni hodnoceni) {
        if (Objects.equals(hodnoceni.roleR, R)) {
            kompletniZprava.hodnoceniR.setId(hodnoceni.getId());
            kompletniZprava.hodnoceniR.obtiznost = hodnoceni.obtiznost;
            kompletniZprava.hodnoceniR.znamka = hodnoceni.znamka;
            kompletniZprava.hodnoceniR.znamka2 = hodnoceni.znamka2;
            kompletniZprava.hodnoceniR.idZprava = kompletniZprava.zprava.getId();
            kompletniZprava.hodnoceniR.roleR = hodnoceni.roleR;
        } else if (Objects.equals(hodnoceni.roleR, AR1)) {
            kompletniZprava.hodnoceniAR1.setId(hodnoceni.getId());
            kompletniZprava.hodnoceniAR1.obtiznost = hodnoceni.obtiznost;
            kompletniZprava.hodnoceniAR1.znamka = hodnoceni.znamka;
            kompletniZprava.hodnoceniAR1.znamka2 = hodnoceni.znamka2;
            kompletniZprava.hodnoceniAR1.idZprava = kompletniZprava.zprava.getId();
            kompletniZprava.hodnoceniAR1.roleR = hodnoceni.roleR;

        } else if (Objects.equals(hodnoceni.roleR, AR2)) {
            kompletniZprava.hodnoceniAR2.setId(hodnoceni.getId());
            kompletniZprava.hodnoceniAR2.obtiznost = hodnoceni.obtiznost;
            kompletniZprava.hodnoceniAR2.znamka = hodnoceni.znamka;
            kompletniZprava.hodnoceniAR2.znamka2 = hodnoceni.znamka2;
            kompletniZprava.hodnoceniAR2.idZprava = kompletniZprava.zprava.getId();
            kompletniZprava.hodnoceniAR2.roleR = hodnoceni.roleR;
        }

    }

    private void dekodujDelegovaneRozhodci(KompletniZprava kompletniZpravaModel) {
        if (kompletniZpravaModel.r != null && !kompletniZpravaModel.r.idFacr.isEmpty()) {
            Clen rozhodci = clenService.getClenByIdFacrAndRole(kompletniZpravaModel.r.idFacr, ROZHODCI);
            if (rozhodci != null) {
                kompletniZprava.r = rozhodci;
            } else {
                kompletniZprava.r = new Clen();
            }
        } else {
            kompletniZprava.r = new Clen();
        }
        if (kompletniZpravaModel.ar1 != null && !kompletniZpravaModel.ar1.idFacr.isEmpty()) {
            Clen ar1 = clenService.getClenByIdFacrAndRole(kompletniZpravaModel.ar1.idFacr, ROZHODCI);
            if (ar1 != null) {
                kompletniZprava.ar1 = ar1;
            } else {
                kompletniZprava.ar1 = new Clen();
            }
        } else {
            kompletniZprava.ar1 = new Clen();
        }
        if (kompletniZpravaModel.ar2 != null && !kompletniZpravaModel.ar2.idFacr.isEmpty()) {
            Clen ar2 = clenService.getClenByIdFacrAndRole(kompletniZpravaModel.ar2.idFacr, ROZHODCI);
            if (ar2 != null) {
                kompletniZprava.ar2 = ar2;
            } else {
                kompletniZprava.ar2 = new Clen();
            }
        } else {
            kompletniZprava.ar2 = new Clen();
        }
        if (kompletniZpravaModel.td != null && !kompletniZpravaModel.td.idFacr.isEmpty()) {
            Clen td = clenService.getClenByIdFacrAndRole(kompletniZpravaModel.td.idFacr, DELEGAT);
            if (td != null) {
                kompletniZprava.td = td;
            } else {
                kompletniZprava.td = new Clen();
            }
        } else {
            kompletniZprava.td = new Clen();
        }
    }

}
