/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Actualites;
import Services.CrudActualites;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PostFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
      public ObservableList<Actualites> Postdata = FXCollections.observableArrayList();
                CrudActualites pts = new CrudActualites();
                  private List<Actualites> Post= new ArrayList<>();
    @FXML
    private Label totalpost;
    @FXML
    private Label totalcom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Postdata.addAll(pts.affiche());
        } catch (SQLException ex) {
            Logger.getLogger(PostFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Match.addAll(getData());
        // Jeux.addAll(jcr.readAll());
   
        System.out.println(Postdata);
        
        int column=0;
        int row=3;
         try {  
        for(int i=0 ; i<Postdata.size();i++){
            
              FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ItemPost.fxml"));
         
                AnchorPane anchorPane = fxmlLoader.load();
          
            ItemPostController itemController = fxmlLoader.getController();
            itemController.setData(Postdata.get(i).getNom(),Postdata.get(i).getDescription(),Postdata.get(i).getImage(),Postdata.get(i).getRate(),Postdata.get(i).getId());
            if(column == 2){
                column=0;
                row++;
            }
            grid.add(anchorPane, column++, row); //Child , column , row
            
            //Set Item Grid Width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            
            
               //Set Item Grid Height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            
            
            
            GridPane.setMargin(anchorPane,new Insets(10));
          
            
            }
              }
            catch (IOException ex) {
                ex.printStackTrace();
              }
              
                
    }
    
     
}