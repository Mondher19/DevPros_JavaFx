/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guii;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.RotateIn;
import animatefx.animation.Tada;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import entities.Tournoi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import services.TournoiServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class ListTournoiController implements Initializable {

    @FXML
    private TableView<Tournoi> TournoiTable;
    @FXML
    private TableColumn<Tournoi, String> vueColl;
    @FXML
    private TableColumn<Tournoi, String> descriptionColl;
    @FXML
    private TableColumn<Tournoi, String> actionsColl;
 
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    ObservableList<Tournoi> TournoiListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Tournoi, Integer> idcoll;
    @FXML
    private TableColumn<Tournoi, String> nbrColl;
    @FXML
    private TextField tfsearchmember;
    @FXML
    private Button imprimebtn;
    @FXML
    private ImageView refrechbtn;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Text titre;
   

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try{
        Connection con = MyDB.getInstance().getConnexion();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tournoi");
            
        while(rs.next()){
        TournoiListe.add(new Tournoi(rs.getInt("id_tour"),rs.getInt("nbr_joueur"),rs.getString("nom_tour"),rs.getString("desc_tour")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idcoll.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tour"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom_tour"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("desc_tour"));  
           nbrColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nbr_joueur"));  

            TournoiTable.setItems(TournoiListe);
            
              
       
            
             //add cell of button edit 
         Callback<TableColumn<Tournoi, String>, TableCell<Tournoi, String>> cellFoctory = (TableColumn<Tournoi, String> param) -> {
            // make cell containing buttons
            final TableCell<Tournoi, String> cell = new TableCell<Tournoi, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button btnModifier = new Button("Modifier");
                         Button btnsupp = new Button("Supprimer");

                  //supprimer
                        btnsupp.setOnMouseClicked((MouseEvent event) -> {
              
                            TournoiServices T = new TournoiServices();


       try{
//                                     T.deleteEmplacement(emplacementTable.getSelectionModel().getSelectedItem().getId_emplacement());
//                                       
//                                       
//           JOptionPane.showMessageDialog(null, "Data telah terhapus");
//           Emplacement selectedItem = emplacementTable.getSelectionModel().getSelectedItem();
//            emplacementTable.getItems().remove(selectedItem);       
Tournoi e = TournoiTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `tournoi` WHERE id_tour  ="+e.getId_tour();
                                connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
   } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }

                        }); 
                 
                        
                        
                        
                        
                        ////modifier
                        btnModifier.setOnMouseClicked((MouseEvent event) -> {                       
                if (TournoiTable.getSelectionModel().getSelectedItem() != null) {
            TournoiServices updateSer = new TournoiServices();
            
          
                 
  }       
                
                
                
                

             });

                        HBox managebtn = new HBox(btnModifier,btnsupp);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnModifier, new Insets(2, 2, 0, 3));
                         HBox.setMargin(btnsupp, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actionsColl.setCellFactory(cellFoctory);
         TournoiTable.setItems(TournoiListe);
         
    }
    
    
    
    @FXML
    private void refreshTable() {
         new RotateIn(refrechbtn).play();
          new FadeIn(titre).play();
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            TournoiListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tournoi");
        while(rs.next()){
        TournoiListe.add(new Tournoi(rs.getInt("id_tour"),rs.getInt("nbr_joueur"),rs.getString("nom_tour"),rs.getString("desc_tour")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
          idcoll.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tour"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom_tour"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("desc_tour"));  
           nbrColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nbr_joueur"));  

            TournoiTable.setItems(TournoiListe);
            
        
    }
    
        
     @FXML
    private void getAdd1(MouseEvent event) {
         try {
             Parent parent = FXMLLoader.load(getClass().getResource("/Guii/ModifierTournoi.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new  Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ListTournoiController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    

    @FXML
    private void getAdd(MouseEvent event) {
         try {
             Parent parent = FXMLLoader.load(getClass().getResource("/Guii/AjouterTournoi.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new  Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ListTournoiController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }


    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }
    
    
    @FXML
    private void imprimerTable(ActionEvent event) throws SQLException, DocumentException, ClassNotFoundException, BadElementException, IOException {
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from tournoi" );
            ResultSet rs = pt.executeQuery();


            
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("list.pdf"));
            
                  // Creating an ImageData object       
                 //PDImageXObject pdImage = PDImageXObject.createFromFile("/eclipse-workspace/java.jpeg",my_pdf_report); 

            my_pdf_report.open();
            
         String filename = "C:\\Users\\Adem_\\Downloads\\DevPros_PiDev_JavaFx-Adam_JavaFx\\DevPros_PiDev_JavaFx-Adam_JavaFx\\src\\1.png";
            Image image = Image.getInstance(filename);
               my_pdf_report.add(image);
                
            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("List Tournoi", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(pdfTitle);
            my_pdf_report.add(new Chunk("\n"));
            my_pdf_report.addCreationDate();
 
            //we have five columns in our table
            PdfPTable my_report_table = new PdfPTable(3);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("Nom Tournoi"));
            table_cell.setBackgroundColor(BaseColor.YELLOW);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Description"));
            table_cell.setBackgroundColor(BaseColor.YELLOW);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Nombre Joueur"));
            table_cell.setBackgroundColor(BaseColor.YELLOW);
            my_report_table.addCell(table_cell);
  


            while (rs.next()) {

                String nom = rs.getString("nom_tour");
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                String qt = rs.getString("desc_tour");
                table_cell = new PdfPCell(new Phrase(qt));
                my_report_table.addCell(table_cell);
                String prixP = rs.getString("nbr_joueur");
                table_cell = new PdfPCell(new Phrase(prixP));
                my_report_table.addCell(table_cell);
                
          
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.add(new Paragraph(" "));
     
       
            my_pdf_report.add(new Chunk("\n"));
            my_pdf_report.add(new Paragraph(" "));
            my_pdf_report.add(new Paragraph("MERCI"));

            System.out.println(my_pdf_report);
            my_pdf_report.close();
            JOptionPane.showMessageDialog(null, "imprimer avec succes");
            new Tada(imprimebtn).play();

            /* Close all DB related objects */
            rs.close();
            pt.close();
            con.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @FXML
    private void trierProduit(ActionEvent event) {

          TournoiServices ms = new TournoiServices();
        ObservableList<Tournoi> list = FXCollections.observableArrayList(ms.TrierParId());

            idcoll.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tour"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom_tour"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("desc_tour"));  
           nbrColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nbr_joueur"));  

            TournoiTable.setItems(list);
         new Bounce(TournoiTable).play();
    }
    
     @FXML
    private void search(KeyEvent event) {
 new Tada(TournoiTable).play();
        TournoiServices ms = new TournoiServices();
       ObservableList<Tournoi> list = FXCollections.observableArrayList(ms.RechercherProduit(tfsearchmember.getText()));

               idcoll.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tour"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom_tour"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("desc_tour"));  
           nbrColl.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nbr_joueur"));  

            TournoiTable.setItems(list);
         
         new Tada(tfsearchmember).play();
    }

    @FXML
    private void stat(ActionEvent event) {
        
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Guii/stat.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
  



  
    
}
