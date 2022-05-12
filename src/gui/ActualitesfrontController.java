/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ActualitesfrontController implements Initializable {

    @FXML
    private TableView<Actualites> table;
    @FXML
    private TableColumn<Actualites, String> collnom;
    @FXML
    private TableColumn<Actualites, String> colldesc;
    @FXML
    private TableColumn<Actualites, String> collimag;
    @FXML
    private TextField searchid;
    ObservableList<Actualites>List;
 private TextField filterField;
     Connection mc;
    PreparedStatement ste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List =FXCollections.observableArrayList();
        // TODO
         try{
             
                 Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `actualites` ");
        while(rst.next()){
        List.add(new Actualites(rst.getString(4),rst.getString(2),rst.getString(3)));
        }
      //  rst=con.createStatement().executeQuery("SELECT * FROM `categorie` ");
      //  while (rst.next())
      //  {
         //   id_cat.getItems().add(rst.getInt(1));
      //  }
      //  System.out.println(List);
       
        } catch (SQLException ex) {
            Logger.getLogger(AffichageActualitesController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            collnom.setCellValueFactory(new PropertyValueFactory<Actualites,String>("nom"));
            colldesc.setCellValueFactory(new PropertyValueFactory<Actualites,String>("description"));
            collimag.setCellValueFactory(new PropertyValueFactory<Actualites,String>("image")); 
           table.setItems(List);
         //  search();
        
    }
  /*  private void search() {      
        
        FilteredList<Actualites>filteredData = new FilteredList<>(List, b->true);
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
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    public void refresh(){
        
         List.clear();
       
          
          mc=MyDB.getInstance().getCon();

        List = FXCollections.observableArrayList();
        
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
                
                
                List.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         table.setItems(List); 
        
    }*/
}
