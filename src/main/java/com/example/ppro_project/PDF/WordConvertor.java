package com.example.ppro_project.PDF;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Model.Vlastnost;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ppro_project.Constants.Constants.*;
import static com.example.ppro_project.Controller.ClenController.clenService;
import static com.example.ppro_project.Controller.VlastnostController.vlastnostService;
import static com.example.ppro_project.Controller.ZpravaController.kompletniZprava;

public class WordConvertor {

    public static Clen r;
    public static Clen ar1;
    public static Clen ar2;
    public static Clen dfa;

    public static Clen td;

    public static List<Vlastnost> vlastnostRPlus;
    public static List<Vlastnost> vlastnostRMinus;
    public static List<Vlastnost> vlastnostAR1Plus;
    public static List<Vlastnost> vlastnostAR1Minus;
    public static List<Vlastnost> vlastnostAR2Plus;
    public static List<Vlastnost> vlastnostAR2Minus;

    private static XWPFDocument getDocument() throws IOException {
        XWPFDocument doc = new XWPFDocument(
                new FileInputStream(
                        "C:\\Users\\fanda\\IdeaProjects\\PPRO_project\\src\\main\\resources\\static\\word\\word_template.docx"));
        return doc;
    }

    public static XWPFDocument printParts() throws IOException {
        dekodujCleny();
        dekodujVlastnosti();
        XWPFDocument doc = getDocument();
        List<IBodyElement> bodyElements = doc.getBodyElements();
        String aktualniTypR = "";
        for (IBodyElement bodyElement : bodyElements) {
            if (bodyElement instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) bodyElement;
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    int indexCells = 0;

                    for (XWPFTableCell tableCell : tableCells) {
                        if (tableCell.getText().contains("_text")) {
                            prepisTableCell(tableCell);
                        } else if (tableCell.getText().contains("ROZHODČÍ")) {
                            aktualniTypR = R;
                        } else if (tableCell.getText().contains("ASISTENT ROZHODČÍHO 1")) {
                            aktualniTypR = AR1;
                        } else if (tableCell.getText().contains("ASISTENT ROZHODČÍHO 2")) {
                            aktualniTypR = AR2;
                        }
                        System.out.println("recurs text cell: {} " + tableCell.getText());
                        dekodujZnamku(tableCells, indexCells, tableCell, aktualniTypR);
                        indexCells++;
                    }
                }
            } else if (bodyElement instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) bodyElement;
                System.out.println("paragraph " + paragraph.getText());
                if (paragraph.getText().contains("jmeno_prijmeni_dfa_text")) {

                    //tableCell.setText(dfa.jmeno + " " + dfa.prijmeni);
                }
            }
        }
        return doc;
        //saveDocument(doc);
    }

    private static void dekodujZnamku(List<XWPFTableCell> tableCells,
                                      int indexCells, XWPFTableCell tableCell, String aktualniTypR) {
        if (tableCell.getText()
                .contains("Aplikace a interpretace pravidel fotbalu, taktika řízení utkání:")) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[0].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Disciplinární opatření, zvládání hráčů a funkcionářů družstev:")) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[1].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Pohyb a poziční postavení:") && aktualniTypR == R) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[2].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Osobnost rozhodčího, spolupráce")) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[3].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Aplikace a interpretace pravidel fotbalu, spolupráce s R:")
                && aktualniTypR == AR1) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniAR1.hodnoceniPopisList[0].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Pohyb a poziční postavení:") && aktualniTypR == AR1) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniAR1.hodnoceniPopisList[1].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Aplikace a interpretace pravidel fotbalu, spolupráce s R:")
                && aktualniTypR == AR2) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniAR2.hodnoceniPopisList[0].celkovaZnamka);
        } else if (tableCell.getText()
                .contains("Pohyb a poziční postavení:") && aktualniTypR == AR2) {
            vyplnZnamku(tableCells, indexCells,
                    kompletniZprava.hodnoceniAR2.hodnoceniPopisList[1].celkovaZnamka);
        }
    }

    private static void vyplnZnamku(List<XWPFTableCell> tableCells, int indexCells, int celkovaZnamka) {
        for (int i = indexCells + 1; i < indexCells + 5; i++) {
            if (tableCells.get(i).getText().contains(String.valueOf(celkovaZnamka))) {
                String tableCellText = tableCells.get(i).getText();
                tableCellText = tableCellText.replace("☐", "☒");
                tableCells.get(i).setText(tableCellText);
            }
        }
    }

    private static void dekodujVlastnosti() {
        vlastnostRPlus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniR.hodnoceniPopisList, PLUS);
        vlastnostRMinus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniR.hodnoceniPopisList, MINUS);
        vlastnostAR1Plus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniAR1.hodnoceniPopisList, PLUS);
        vlastnostAR1Minus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniAR1.hodnoceniPopisList, MINUS);
        vlastnostAR2Plus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniAR2.hodnoceniPopisList, PLUS);
        vlastnostAR2Minus = dekodujVlastnostiHodnoceni(kompletniZprava.hodnoceniAR2.hodnoceniPopisList, MINUS);
    }

    private static List<Vlastnost> dekodujVlastnostiHodnoceni(HodnoceniPopis[] hodnoceniPopisList,
                                                              int typ) {
        List<Vlastnost> vlastnosti = new ArrayList<>();
        for (int i = 0; i < hodnoceniPopisList.length; i++) {
            if (hodnoceniPopisList[i].hodnoceniVlastnostArray == null) {
                continue;
            }
            for (int j = 0; j < hodnoceniPopisList[i].hodnoceniVlastnostArray.length; j++) {
                if (hodnoceniPopisList[i].hodnoceniVlastnostArray[j] != null) {
                    int idVlastnost = hodnoceniPopisList[i].hodnoceniVlastnostArray[j].idVlastnost;
                    if (idVlastnost > 0 &&
                            typ == hodnoceniPopisList[i].hodnoceniVlastnostArray[j].typ) {
                        Vlastnost ziskanaVlastnost = vlastnostService.getVlastnostiById(idVlastnost);
                        if (ziskanaVlastnost != null) {
                            vlastnosti.add(ziskanaVlastnost);
                        }
                    }
                }
            }
        }
        return vlastnosti;
    }

    private static void dekodujCleny() {
        r = dekodujClena(kompletniZprava.r);
        ar1 = dekodujClena(kompletniZprava.ar1);
        ar2 = dekodujClena(kompletniZprava.ar2);
        dfa = dekodujClena(kompletniZprava.dfa);
        td = dekodujClena(kompletniZprava.td);
    }

    private static Clen dekodujClena(Clen clen) {
        Clen ziskanyClen = clenService.getClenById(clen.getId());
        if (ziskanyClen == null) {
            ziskanyClen = new Clen();
        }
        return ziskanyClen;
    }

    private static void prepisTableCell(XWPFTableCell tableCell) {
        if (tableCell.getText().contains("kolo_text")) {
            tableCell.setText(String.valueOf(kompletniZprava.utkani.kolo));
        } else if (tableCell.getText().contains("zacatek_utkani_text")) {
            tableCell.setText(kompletniZprava.utkani.datum.getHours()
                    + ":" + kompletniZprava.utkani.minutyDvojciferne());
        } else if (tableCell.getText().contains("idutkani_text")) {
            tableCell.setText(kompletniZprava.utkani.idUtkani);
        } else if (tableCell.getText().contains("doba_hry_1_text")) {
            tableCell.setText(kompletniZprava.zprava.dobaHryPrvniPolocas
                    + "/" + kompletniZprava.zprava.dobaHryDruhyPolocas);
        } else if (tableCell.getText().contains("doba_hry_2_text")) {
            tableCell.setText(kompletniZprava.zprava.dobaHryDruhyPolocas);
        } else if (tableCell.getText().contains("domaci_text")) {
            tableCell.setText(kompletniZprava.utkani.domaci);
        } else if (tableCell.getText().contains("hoste_text")) {
            tableCell.setText(kompletniZprava.utkani.hoste);
        } else if (tableCell.getText().contains("vysledek_text")) {
            tableCell.setText(kompletniZprava.zprava.vysledek);
        } else if (tableCell.getText().contains("polocas_text")) {
            tableCell.setText(kompletniZprava.zprava.polocas);
        } else if (tableCell.getText().contains("prijmeni_r_text")) {
            tableCell.setText(r.prijmeni);
        } else if (tableCell.getText().contains("jmeno_r_text")) {
            tableCell.setText(r.jmeno);
        } else if (tableCell.getText().contains("idfacr_r_text")) {
            tableCell.setText(r.idFacr);
        } else if (tableCell.getText().contains("prijmeni_ar1_text")) {
            tableCell.setText(ar1.prijmeni);
        } else if (tableCell.getText().contains("jmeno_ar1_text")) {
            tableCell.setText(ar1.jmeno);
        } else if (tableCell.getText().contains("idfacr_ar1_text")) {
            tableCell.setText(ar1.idFacr);
        } else if (tableCell.getText().contains("prijmeni_ar2_text")) {
            tableCell.setText(ar2.prijmeni);
        } else if (tableCell.getText().contains("jmeno_ar2_text")) {
            tableCell.setText(ar2.jmeno);
        } else if (tableCell.getText().contains("idfacr_ar2_text")) {
            tableCell.setText(ar2.idFacr);
        } else if (tableCell.getText().contains("prijmeni_dfa_text")) {
            tableCell.setText(dfa.prijmeni);
        } else if (tableCell.getText().contains("jmeno_dfa_text")) {
            tableCell.setText(dfa.jmeno);
        } else if (tableCell.getText().contains("idfacr_dfa_text")) {
            tableCell.setText(dfa.idFacr);
        } else if (tableCell.getText().contains("prijmeni_td_text")) {
            tableCell.setText(td.prijmeni);
        } else if (tableCell.getText().contains("jmeno_td_text")) {
            tableCell.setText(td.jmeno);
        } else if (tableCell.getText().contains("idfacr_td_text")) {
            tableCell.setText(td.idFacr);
        } else if (tableCell.getText().contains("vlastnosti_r_plus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRPlus, 1, R);
        } else if (tableCell.getText().contains("vlastnosti_r_plus_2_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRPlus, 2, R);
        } else if (tableCell.getText().contains("vlastnosti_r_plus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRPlus, 3, R);
        } else if (tableCell.getText().contains("vlastnosti_r_minus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRMinus, 1, R);
        } else if (tableCell.getText().contains("vlastnosti_r_minus_2_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRMinus, 2, R);
        } else if (tableCell.getText().contains("vlastnosti_r_minus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostRMinus, 3, R);
        } else if (tableCell.getText().contains("vlastnosti_ar1_plus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR1Plus, 1, AR1);
        } else if (tableCell.getText().contains("vlastnosti_ar1_plus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR1Plus, 2, AR1);
        } else if (tableCell.getText().contains("vlastnosti_ar1_minus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR1Minus, 1, AR1);
        } else if (tableCell.getText().contains("vlastnosti_ar1_minus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR1Minus, 2, AR1);
        } else if (tableCell.getText().contains("vlastnosti_ar2_plus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR2Plus, 1, AR2);
        } else if (tableCell.getText().contains("vlastnosti_ar2_plus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR2Plus, 2, AR2);
        } else if (tableCell.getText().contains("vlastnosti_ar2_minus_1_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR2Minus, 1, AR2);
        } else if (tableCell.getText().contains("vlastnosti_ar2_minus_dalsi_text")) {
            vyplnVlastnostToCell(tableCell, vlastnostAR2Minus, 2, AR2);
        }
    }

    private static void vyplnVlastnostToCell(XWPFTableCell tableCell,
                                             List<Vlastnost> vlastnosti, int cast, String typR) {
        int pocetVlastnosti = vlastnosti.size();
        String vlastnostiText = "";
        if (Objects.equals(typR, R)) {
            if (cast == 1 && pocetVlastnosti > 0) {
                for (int i = 0; i < pocetVlastnosti / 3; i++) {
                    vlastnostiText += vlastnosti.get(i).getPopis();
                    if (i != (pocetVlastnosti / 3) - 1) {
                        vlastnostiText += ", ";
                    }
                }
            }
            if (cast == 2 && pocetVlastnosti > 1) {
                for (int i = (pocetVlastnosti / 3); i < pocetVlastnosti / 3 * 2; i++) {
                    vlastnostiText += vlastnosti.get(i).getPopis();
                    if (i != (pocetVlastnosti / 3 * 2) - 1) {
                        vlastnostiText += ", ";
                    }
                }
            }
            if (cast == 3 && pocetVlastnosti > 2) {
                for (int i = (pocetVlastnosti / 3 * 2); i < pocetVlastnosti; i++) {
                    vlastnostiText += vlastnosti.get(i).getPopis();
                    if (i != pocetVlastnosti - 1) {
                        vlastnostiText += ", ";
                    }
                }
            }
        } else {
            if (cast == 1 && pocetVlastnosti > 0) {
                if (pocetVlastnosti == 1) {
                    vlastnostiText += vlastnosti.get(0).getPopis();
                } else {
                    for (int i = 0; i < (pocetVlastnosti / 2); i++) {
                        vlastnostiText += vlastnosti.get(i).getPopis();
                        if (i != (pocetVlastnosti / 2) - 1) {
                            vlastnostiText += ", ";
                        }
                    }
                }
            }
            if (cast == 2 && pocetVlastnosti > 1) {
                for (int i = (pocetVlastnosti / 2); i < pocetVlastnosti; i++) {
                    vlastnostiText += vlastnosti.get(i).getPopis();
                    if (i != pocetVlastnosti - 1) {
                        vlastnostiText += ", ";
                    }
                }
            }
        }
        tableCell.setText(vlastnostiText);
    }

    public static void saveDocument(XWPFDocument doc) throws IOException {

        File file = new File("C:\\Users\\fanda\\IdeaProjects\\PPRO_project\\src\\main\\resources\\static\\word\\novy.docx");
        file.createNewFile();
        doc.write(new FileOutputStream(file));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
//        doc.write(baos);
        doc.close();
    }
}
