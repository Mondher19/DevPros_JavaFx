/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.CrudActualites;
import Utils.MyDB;
import entities.Actualites;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import org.controlsfx.control.Notifications;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AffichageActualitesController implements Initializable {

    @FXML
    private TableView<Actualites> Tableau;
    @FXML
    private Button buttajout;
    @FXML
    private Button buttsupprimer;
    @FXML
    private Button buttmodif;
int id_selected ;
    @FXML
    private TableColumn<Actualites, String> colnom;
    @FXML
    private TableColumn<Actualites, String> coldesc;
    @FXML
    private TableColumn<Actualites, String> colimg;
    @FXML
    private TableColumn<Actualites, Integer> colid;
    @FXML
    private TableColumn<Actualites, String> colcateg;
    @FXML
    private TableColumn<Actualites, Integer> colcategid;
    @FXML
    private TextField nom_champ;
    @FXML
    private TextField desc_champ;
    @FXML
    private TextField img_champ;
    @FXML
    private Label label_id;
    @FXML
    private ComboBox<Integer> id_cat;
    @FXML
    private TextField filterField;
     Connection mc;
    PreparedStatement ste;
    /**
     * Initializes the controller class.
     */
    ObservableList<Actualites>actList;
    Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private Button pdf;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        actList =FXCollections.observableArrayList();
        // TODO
         try{
             
                 Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `actualites` ");
        while(rst.next()){
        actList.add(new Actualites(rst.getString(4),rst.getString(2),rst.getString(3),rst.getInt(1),rst.getInt(6),rst.getInt(5)));
        }
        rst=con.createStatement().executeQuery("SELECT * FROM `categorie` ");
        while (rst.next())
        {
            id_cat.getItems().add(rst.getInt(1));
        }
        System.out.println(actList);
       
        } catch (SQLException ex) {
            Logger.getLogger(AffichageActualitesController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            colnom.setCellValueFactory(new PropertyValueFactory<Actualites,String>("nom"));
            coldesc.setCellValueFactory(new PropertyValueFactory<Actualites,String>("description"));
            colimg.setCellValueFactory(new PropertyValueFactory<Actualites,String>("image")); 
            colid.setCellValueFactory(new PropertyValueFactory<Actualites,Integer>("id"));
            colcateg.setCellValueFactory(new PropertyValueFactory<Actualites,String>("rate"));
            colcategid.setCellValueFactory(new PropertyValueFactory<Actualites,Integer>("id_cat"));
            Tableau.setItems(actList);
            search();
        
    }

    @FXML
    private void ajouteractualite(ActionEvent event) throws SQLException {
        
    String nom=nom_champ.getText();
     String desc=desc_champ.getText();
     String img=img_champ.getText();
      
        CrudActualites cs=new CrudActualites();
        Actualites act1 =new Actualites(nom_champ.getText().toString(),desc_champ.getText().toString(),img_champ.getText().toString());
        act1.setId_cat(id_cat.getValue());
        System.out.println(act1);
        if( (nom.isEmpty() || desc.isEmpty()) || img.isEmpty()){
                       
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier Votre champs s'il vous plaît!");
            alert.showAndWait();}
        else{
            cs.ajouteract(act1);
        
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Actualité ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT).onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event){
                       System.out.println("clicked on");
                   } 
                
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        }
        refresh();
    }

    @FXML
    private void supprimeractualite(ActionEvent event) {
      
                
      CrudActualites cs = new CrudActualites();
         
         System.out.println(Tableau.getSelectionModel().getSelectedItem().getId()+"");
        try {
            
           cs.delete(Tableau.getSelectionModel().getSelectedItem().getId());
      
           JOptionPane.showMessageDialog(null, "Suppression avec succée!");
           
            Actualites selectedItem = Tableau.getSelectionModel().getSelectedItem();
            
            Tableau.getItems().remove(selectedItem); 
            
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
       }
        
         
    }
   

    @FXML
    private void modifieractualite(ActionEvent event) throws SQLException {
        CrudActualites cs=new CrudActualites();
        Actualites act1 =new Actualites(nom_champ.getText().toString(),desc_champ.getText().toString(),img_champ.getText().toString());
        act1.setId_cat(id_cat.getValue());
        act1.setId(Tableau.getSelectionModel().getSelectedItem().getId());
        System.out.println(act1);
        cs.updatecat(act1,act1.getId());
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Actualités modifiés avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT).onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event){
                       System.out.println("clicked on");
                   } 
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        refresh();
        
    }

    @FXML
    private void tableview_clicked(javafx.scene.input.MouseEvent event) {
        System.out.println("Clicked on " + Tableau.getSelectionModel().getSelectedItem().getId()); 
                buttsupprimer.setDisable(false);
                buttmodif.setDisable(false);
                 int index = Tableau.getSelectionModel().getSelectedItem().getId();
                  nom_champ.setText(Tableau.getSelectionModel().getSelectedItem().getNom());
                  desc_champ.setText(Tableau.getSelectionModel().getSelectedItem().getDescription());
                  img_champ.setText(Tableau.getSelectionModel().getSelectedItem().getImage());
                  label_id.setText(Tableau.getSelectionModel().getSelectedItem().getId()+"");
             
            
             
//            String datestart = tableviewreservation.getSelectionModel().getSelectedItem().getTempsstart() ;
//                    
//             datepickerstart.setValue(LocalDate.parse(datestart));
             
            id_selected=Tableau.getSelectionModel().getSelectedItem().getId();
    }
   
private void search() {      
        
        FilteredList<Actualites>filteredData = new FilteredList<>(actList, b->true);
        filterField.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(Actualites->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
               
                 if(String.valueOf(Actualites).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Actualites>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Tableau.comparatorProperty());
        Tableau.setItems(sortedData);
    }
    public void refresh(){
        
         actList.clear();
       
          
          mc=MyDB.getInstance().getCon();

        actList = FXCollections.observableArrayList();
        
        String sql="select * from actualites";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Actualites e = new Actualites();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                
                
                actList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         Tableau.setItems(actList); 
        
    }
    /*
@FXML
   private void pdf(ActionEvent event)  throws FileNotFoundException, DocumentException {
        
        try {
            Connection con = MyDB.getInstance().getCon();
            Statement ste = con.createStatement();
            ResultSet query_set = ste.executeQuery("select nom,descrption,image,rate,id from Actualites");
            com.itextpdf.text.Document p1 = new com.itextpdf.text.Document();
            PdfWriter.getInstance((com.itextpdf.text.Document) p1 , new FileOutputStream("C:/Users/yassine/Desktop/actualites.pdf"));
            p1.open();
            PdfPTable my_report_table = new PdfPTable(4);
            PdfPCell table_cell;
            
             table_cell = new PdfPCell(new Phrase("nom"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("description"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("image"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("rate"));
            my_report_table.addCell(table_cell);
            
            while (query_set.next()) {
                String nom = query_set.getString("nom");
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                
                String description = query_set.getString("description");
                table_cell = new PdfPCell(new Phrase(description));
                my_report_table.addCell(table_cell);
                
                String image = query_set.getString("image");
                table_cell = new PdfPCell(new Phrase(image));
                my_report_table.addCell(table_cell);
                
                 String rate = query_set.getString("rate");
                table_cell = new PdfPCell(new Phrase(rate));
                my_report_table.addCell(table_cell);
              
            }
            p1.add(my_report_table);
            p1.close();
            query_set.close();
            ste.close();
            con.close();

            System.out.println("Pdf disponible");

        } catch (SQLException ex) {
            System.out.println("error");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AffichageActualitesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AffichageActualitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

*/
    @FXML
    private void pdf(ActionEvent event) {
    }

}