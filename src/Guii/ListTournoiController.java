/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guii;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.Tournoi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
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



  
    
}
