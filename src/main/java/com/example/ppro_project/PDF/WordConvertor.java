package com.example.ppro_project.PDF;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Model.Vlastnost;
import org.apache.poi.xwpf.usermodel.*;

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
    public static List<String> minuty;
    public static List<String> situace;
    public static String ostatniText;

    private static XWPFDocument getDocument() throws IOException {
        XWPFDocument doc = new XWPFDocument(
                new FileInputStream(
                        "C:\\Users\\fanda\\IdeaProjects\\PPRO_project\\src\\main\\resources\\static\\word\\zprava_template.docx"));
        return doc;
    }

    public static XWPFDocument printParts() throws IOException {
        dekodujCleny();
        dekodujVlastnosti();
        dekodujOstatni();
        XWPFDocument doc = getDocument();
        List<IBodyElement> bodyElements = doc.getBodyElements();
        String aktualniTypR = "";
        nastavRadkySituace(bodyElements);
        bodyElements = doc.getBodyElements();
        for (IBodyElement bodyElement : bodyElements) {
            if (bodyElement instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) bodyElement;
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    int indexCells = 0;

                    for (XWPFTableCell tableCell : tableCells) {
                        System.out.println("recurs text cell PRED: {} " + tableCell.getText());
                        if (tableCell.getText().contains("_text")) {
                            prepisTableCell(tableCell);
                        } else if (tableCell.getText().contains("ASISTENT ROZHODČÍHO 1")) {
                            aktualniTypR = AR1;
                        } else if (tableCell.getText().contains("ASISTENT ROZHODČÍHO 2")) {
                            aktualniTypR = AR2;
                        } else if (tableCell.getText().contains("ROZHODČÍ")) {
                            aktualniTypR = R;
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
                    for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                        paragraph.removeRun(i);
                    }
                    XWPFRun newRun = paragraph.createRun();
                    newRun.setText(dfa.jmeno + " " + dfa.prijmeni);
                }
            }
        }
        return doc;
        //saveDocument(doc);
    }

    private static void dekodujOstatni() {
        ostatniText = "";
        if(kompletniZprava.hodnoceniR != null && kompletniZprava.hodnoceniR.hodnoceniPopisList != null){
            if(kompletniZprava.hodnoceniR.hodnoceniPopisList.length > 5 &&
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[5].popis.length() > 0){
                ostatniText = "Pořadatelé: " + kompletniZprava.hodnoceniR.hodnoceniPopisList[5].popis;
            }
            if(kompletniZprava.hodnoceniR.hodnoceniPopisList.length > 6 &&
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[6].popis.length() > 0){
                ostatniText += "\nDiváci: " + kompletniZprava.hodnoceniR.hodnoceniPopisList[6].popis;
            }
            if(kompletniZprava.hodnoceniR.hodnoceniPopisList.length > 7 &&
                    kompletniZprava.hodnoceniR.hodnoceniPopisList[7].popis.length() > 0){
                ostatniText += "\nJiné: " + kompletniZprava.hodnoceniR.hodnoceniPopisList[7].popis;
            }
        }
    }

    private static void nastavRadkySituace(List<IBodyElement> bodyElements) {
        if (situace.size() > 4) {
            for (IBodyElement bodyElement : bodyElements) {
                if (bodyElement instanceof XWPFTable) {
                    XWPFTable table = (XWPFTable) bodyElement;
                    List<XWPFTableRow> rows = table.getRows();
                    boolean spravnaTable = false;
                    for (XWPFTableRow row : rows) {
                        List<XWPFTableCell> tableCells = row.getTableCells();

                        for (XWPFTableCell tableCell : tableCells) {
                            if (tableCell.getText().contains("situace_5_text")) {
                                spravnaTable = true;
                            }
                        }
                    }
                    if (spravnaTable) {
                        for (int i = 4; i < situace.size(); i++) {
                            if (i == 4) {
                                XWPFTableRow row = table.getRow(table.getRows().size() - 1);
                                XWPFTableCell cellMinuta = row.getCell(0);
                                XWPFTableCell cellSituace = row.getCell(1);
                                cellMinuta.setText(minuty.get(i));
                                cellSituace.setText(situace.get(i));
                                continue;
                            }
                            table.addRow(table.getRow(table.getRows().size() - 1),
                                    table.getRows().size() - 1);
                            XWPFTableRow row = table.getRow(table.getRows().size() - 1);
                            XWPFTableCell cellMinuta = row.getCell(0);
                            XWPFTableCell cellSituace = row.getCell(1);
                            if (cellMinuta != null && cellSituace != null) {
                                cellMinuta.setText(minuty.get(i));
                                cellSituace.setText(situace.get(i));
                            }

                        }
                        break;
                    }
                }
            }
        }
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
        dekodujMinutyASituace();
    }

    private static void dekodujMinutyASituace() {
        minuty = new ArrayList<>();
        situace = new ArrayList<>();
        for (int i = 0; i < kompletniZprava.hodnoceniR.hodnoceniPopisList.length; i++) {
            if (kompletniZprava.hodnoceniR.hodnoceniPopisList[i].hodnoceniVlastnostArray == null) {
                continue;
            }
            for (int j = 0; j
                    < kompletniZprava.hodnoceniR.hodnoceniPopisList[i].hodnoceniVlastnostArray.length; j++) {
                String minuta =
                        kompletniZprava.hodnoceniR.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].minuta;
                String situaceStr =
                        kompletniZprava.hodnoceniR.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].situace;
                if (situaceStr != null && situaceStr.length() > 0) {
                    minuty.add(minuta);
                    situace.add(situaceStr);
                }
            }
        }
        for (int i = 0; i < kompletniZprava.hodnoceniAR1.hodnoceniPopisList.length; i++) {
            if (kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].hodnoceniVlastnostArray == null) {
                continue;
            }
            for (int j = 0; j
                    < kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].hodnoceniVlastnostArray.length; j++) {
                String minuta =
                        kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].minuta;
                String situaceStr =
                        kompletniZprava.hodnoceniAR1.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].situace;
                if (situaceStr != null && situaceStr.length() > 0) {
                    minuty.add(minuta);
                    situace.add(situaceStr);
                }
            }
        }
        for (int i = 0; i < kompletniZprava.hodnoceniAR2.hodnoceniPopisList.length; i++) {
            if (kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].hodnoceniVlastnostArray == null) {
                continue;
            }
            for (int j = 0; j
                    < kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].hodnoceniVlastnostArray.length; j++) {
                String minuta =
                        kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].minuta;
                String situaceStr =
                        kompletniZprava.hodnoceniAR2.hodnoceniPopisList[i].hodnoceniVlastnostArray[j].situace;
                if (situaceStr != null && situaceStr.length() > 0) {
                    minuty.add(minuta);
                    situace.add(situaceStr);
                }
            }
        }
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
            tableCell.setText((kompletniZprava.utkani.kolo) + ".");
        } else if (tableCell.getText().contains("zacatek_utkani_text")) {
            tableCell.setText(kompletniZprava.utkani.datum.getHours()
                    + ":" + kompletniZprava.utkani.minutyDvojciferne());
        } else if (tableCell.getText().contains("soutez_text")) {
            tableCell.setText(kompletniZprava.soutez.soutez);
        } else if (tableCell.getText().contains("datum_text")) {
            tableCell.setText(kompletniZprava.utkani.datum.getDay()
                    + ". " + kompletniZprava.utkani.datum.getMonth() + ". " +
                    (kompletniZprava.utkani.datum.getYear() + 1900));
        } else if (tableCell.getText().contains("idutkani_text")) {
            tableCell.setText(kompletniZprava.utkani.idUtkani);
        } else if (tableCell.getText().contains("doba_hry_text")) {
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
        } else if (tableCell.getText().contains("znamka_r_text")) {
            tableCell.setText(kompletniZprava.hodnoceniR.znamka);
        } else if (tableCell.getText().contains("znamka_r_2_text")) {
            if (kompletniZprava.hodnoceniR.znamka2 != null) {
                tableCell.setText(kompletniZprava.hodnoceniR.znamka2);
            } else {
                tableCell.setText("");
            }
        } else if (tableCell.getText().contains("obtiznost_r_text")) {
            tableCell.setText(kompletniZprava.hodnoceniR.obtiznost);
        } else if (tableCell.getText().contains("znamka_ar1_text")) {
            tableCell.setText(kompletniZprava.hodnoceniAR1.znamka);
        } else if (tableCell.getText().contains("znamka_ar1_2_text")) {
            if (kompletniZprava.hodnoceniAR1.znamka2 != null) {
                tableCell.setText(kompletniZprava.hodnoceniAR1.znamka2);
            } else {
                tableCell.setText("");
            }
        } else if (tableCell.getText().contains("obtiznost_ar1_text")) {
            tableCell.setText(kompletniZprava.hodnoceniAR1.obtiznost);
        } else if (tableCell.getText().contains("znamka_ar2_text")) {
            tableCell.setText(kompletniZprava.hodnoceniAR2.znamka);
        } else if (tableCell.getText().contains("znamka_ar2_2_text")) {
            if (kompletniZprava.hodnoceniAR2.znamka2 != null) {
                tableCell.setText(kompletniZprava.hodnoceniAR2.znamka2);
            } else {
                tableCell.setText("");
            }
        } else if (tableCell.getText().contains("obtiznost_ar2_text")) {
            tableCell.setText(kompletniZprava.hodnoceniAR2.obtiznost);

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
        } else if (tableCell.getText().contains("minuta_1_text")) {
            String minuta = "";
            if (situace.size() > 0) {
                minuta = minuty.get(0);
            }
            tableCell.setText(minuta);
        } else if (tableCell.getText().contains("situace_1_text")) {
            String situaceStr = "";
            if (situace.size() > 0) {
                situaceStr = situace.get(0);
            }
            tableCell.setText(situaceStr);
        } else if (tableCell.getText().contains("minuta_2_text")) {
            String minuta = "";
            if (situace.size() > 1) {
                minuta = minuty.get(1);
            }
            tableCell.setText(minuta);
        } else if (tableCell.getText().contains("situace_2_text")) {
            String situaceStr = "";
            if (situace.size() > 1) {
                situaceStr = situace.get(1);
            }
            tableCell.setText(situaceStr);
        } else if (tableCell.getText().contains("minuta_3_text")) {
            String minuta = "";
            if (situace.size() > 2) {
                minuta = minuty.get(2);
            }
            tableCell.setText(minuta);
        } else if (tableCell.getText().contains("situace_3_text")) {
            String situaceStr = "";
            if (situace.size() > 2) {
                situaceStr = situace.get(2);
            }
            tableCell.setText(situaceStr);
        } else if (tableCell.getText().contains("minuta_4_text")) {
            String minuta = "";
            if (situace.size() > 3) {
                minuta = minuty.get(3);
            }
            tableCell.setText(minuta);
        } else if (tableCell.getText().contains("situace_4_text")) {
            String situaceStr = "";
            if (situace.size() > 3) {
                situaceStr = situace.get(3);
            }
            tableCell.setText(situaceStr);
        }else if (tableCell.getText().contains("minuta_5_text")) {
            String minuta = "";
            if (situace.size() > 4) {
                minuta = minuty.get(4);
            }
            tableCell.setText(minuta);
        } else if (tableCell.getText().contains("situace_5_text")) {
            String situaceStr = "";
            if (situace.size() > 4) {
                situaceStr = situace.get(4);
            }
            tableCell.setText(situaceStr);
        }
        else if (tableCell.getText().contains("pk_text")) {
            String temp = "NE";
            if (kompletniZprava.zprava.pk) {
                temp = "ANO";
            }
            tableCell.setText(temp);
        }
        else if (tableCell.getText().contains("ck_text")) {
            String temp = "NE";
            if (kompletniZprava.zprava.ck) {
                temp = "ANO";
            }
            tableCell.setText(temp);
        }
        else if (tableCell.getText().contains("zaznam_text")) {
            String temp = "NE";
            if (kompletniZprava.zprava.zaznam) {
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("poradatele_text")) {
            String temp = "NE";
            if (kompletniZprava.zprava.poradatele) {
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("stk_text")) {
            String temp = "NE";
            if(kompletniZprava.zprava.stk){
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("dk_text")) {
            String temp = "NE";
            if(kompletniZprava.zprava.dk){
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("kr_text")) {
            String temp = "NE";
            if(kompletniZprava.zprava.kr){
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("divaci_text")) {
            String temp = "NE";
            if(kompletniZprava.zprava.divaci){
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("zraneni_text")) {
            String temp = "NE";
            if(kompletniZprava.zprava.zraneni){
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("konfrontace_text")) {
            String temp = "NE";
            if (kompletniZprava.zprava.konfrontace) {
                temp = "ANO";
            }
            tableCell.setText(temp);
        } else if (tableCell.getText().contains("ostatni_text")) {
            tableCell.setText(ostatniText);
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
