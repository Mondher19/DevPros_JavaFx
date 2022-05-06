/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import Services.ServiceEquipement;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.text.Document;
import static sun.net.www.MimeTable.loadTable;



import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


/**
 *
 * @author Mondh
 */
public class Pdf {
    
        


  public void stagePdf() throws FileNotFoundException, DocumentException 
    {
        Document document = new Document();

        ServiceEquipement jp = new  ServiceEquipement();
        List<ServiceEquipement> res= (List<ServiceEquipement>) loadTable();
        PdfWriter.getInstance(document, new FileOutputStream (new File ("journal.pdf")));
        document.open();
       Pdff pdf = new Pdff();
        for (Journal_projet o :res) {

            Paragraph Journal_projet= new Paragraph ("journal n°"+ o.getId_jp());
           Journal_projet.setAlignment (Element.ALIGN_CENTER);
            document.add (Journal_projet);
            Paragraph date= new Paragraph ("ID_Oeuvre : " + o.getDate());
            date.setAlignment (Element.ALIGN_LEFT);
            Paragraph tache = new Paragraph ("tache : " + o.getTache());
           tache.setAlignment (Element.ALIGN_LEFT);
            Paragraph  avis = new Paragraph (" avis: " + o.getAvis());
            avis.setAlignment (Element.ALIGN_LEFT);
          
           
            document.add (date);
            document.add (tache);
            document.add (avis);
      
            


        }
        document.close ();
    }
    
}
