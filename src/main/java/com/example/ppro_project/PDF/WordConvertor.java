package com.example.ppro_project.PDF;
import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Vlastnost;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.example.ppro_project.Controller.ClenController.clenService;
import static com.example.ppro_project.Controller.VlastnostController.vlastnostService;
import static com.example.ppro_project.Controller.ZpravaController.kompletniZprava;

public class WordConvertor {

    private static XWPFDocument getDocument() throws IOException {
        XWPFDocument doc = new XWPFDocument(
                new FileInputStream(
                        "C:\\Users\\fanda\\IdeaProjects\\PPRO_project\\src\\main\\resources\\static\\word\\word_template.docx"));
        return doc;
    }

    public static void printParts() throws IOException {
        XWPFDocument doc = getDocument();
        List<IBodyElement> bodyElements = doc.getBodyElements();
        for (IBodyElement bodyElement : bodyElements) {
            //System.out.println("Body part type: {}" + bodyElement.getClass());
           // if ( bodyElement instanceof XWPFParagraph)
           // {
          //      XWPFParagraph para = (XWPFParagraph)bodyElement;
          //      System.out.println("paragraph text: {}" + para.getText());
         //   }
           if ( bodyElement instanceof XWPFTable)
            {
                XWPFTable table = (XWPFTable)bodyElement;
               // System.out.println("table text: {}" + table.getText());
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    //System.out.println("new row with cells: {}" +  row.getTableCells().size());
                    for (XWPFTableCell tableCell : tableCells) {
                        if(tableCell.getText().contains("_text")){
                            prepisTableCell(tableCell);
                        }
                        System.out.println("recurs text cell: {} " + tableCell.getText());
                    }
                }
            }
        }
        saveDocument(doc);
    }

    private static void prepisTableCell(XWPFTableCell tableCell) {
        if(tableCell.getText().contains("kolo_text")){
            tableCell.setText(String.valueOf(kompletniZprava.utkani.kolo));
        }
        else if(tableCell.getText().contains("zacatek_utkani_text")){
            tableCell.setText(kompletniZprava.utkani.datum.getHours()
                    + ":" + kompletniZprava.utkani.minutyDvojciferne());
        }
        else if(tableCell.getText().contains("idutkani_text")){
            tableCell.setText(kompletniZprava.utkani.idUtkani);
        }
        else if(tableCell.getText().contains("doba_hry_1_text")){
            tableCell.setText(kompletniZprava.zprava.dobaHryPrvniPolocas);
        }
        else if(tableCell.getText().contains("doba_hry_2_text")){
            tableCell.setText(kompletniZprava.zprava.dobaHryDruhyPolocas);
        }
        else if(tableCell.getText().contains("domaci_text")){
            tableCell.setText(kompletniZprava.utkani.domaci);
        }
        else if(tableCell.getText().contains("hoste_text")){
            tableCell.setText(kompletniZprava.utkani.hoste);
        }
        else if(tableCell.getText().contains("vysledek_text")){
            tableCell.setText(kompletniZprava.zprava.vysledek);
        }
        else if(tableCell.getText().contains("polocas_text")){
            tableCell.setText(kompletniZprava.zprava.polocas);
        }

        else if(tableCell.getText().contains("prijmeni_r_text")){
            Clen r = clenService.getClenById(kompletniZprava.r.getId());
            tableCell.setText(r.prijmeni);
        }
        else if(tableCell.getText().contains("jmeno_r_text")){
            Clen r = clenService.getClenById(kompletniZprava.r.getId());
            tableCell.setText(r.jmeno);
        }
        else if(tableCell.getText().contains("idfacr_r_text")){
            Clen r = clenService.getClenById(kompletniZprava.r.getId());
            tableCell.setText(r.idFacr);
        }
        else if(tableCell.getText().contains("prijmeni_ar1_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar1.getId());
            tableCell.setText(r.prijmeni);
        }
        else if(tableCell.getText().contains("jmeno_ar1_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar1.getId());
            tableCell.setText(r.jmeno);
        }
        else if(tableCell.getText().contains("idfacr_ar1_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar1.getId());
            tableCell.setText(r.idFacr);
        }
        else if(tableCell.getText().contains("prijmeni_ar2_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar2.getId());
            tableCell.setText(r.prijmeni);
        }
        else if(tableCell.getText().contains("jmeno_ar2_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar2.getId());
            tableCell.setText(r.jmeno);
        }
        else if(tableCell.getText().contains("idfacr_ar2_text")){
            Clen r = clenService.getClenById(kompletniZprava.ar2.getId());
            tableCell.setText(r.idFacr);
        }
        else if(tableCell.getText().contains("prijmeni_dfa_text")){
            Clen r = clenService.getClenById(kompletniZprava.dfa.getId());
            tableCell.setText(r.prijmeni);
        }
        else if(tableCell.getText().contains("jmeno_dfa_text")){
            Clen r = clenService.getClenById(kompletniZprava.dfa.getId());
            tableCell.setText(r.jmeno);
        }
        else if(tableCell.getText().contains("idfacr_dfa_text")){
            Clen r = clenService.getClenById(kompletniZprava.dfa.getId());
            tableCell.setText(r.idFacr);
        }
        else if(tableCell.getText().contains("prijmeni_td_text")){
            Clen r = clenService.getClenById(kompletniZprava.td.getId());
           // tableCell.setText(r.prijmeni);
        }
        else if(tableCell.getText().contains("jmeno_td_text")){
            Clen r = clenService.getClenById(kompletniZprava.td.getId());
           // tableCell.setText(r.jmeno);
        }
        else if(tableCell.getText().contains("idfacr_td_text")){
            Clen r = clenService.getClenById(kompletniZprava.td.getId());
          //  tableCell.setText(r.idFacr);
        }

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
