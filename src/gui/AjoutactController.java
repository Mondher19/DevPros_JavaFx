/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CrudActualites;
import Utils.MyDB;
import entities.Actualites;
import entities.Categorie;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AjoutactController implements Initializable {

    @FXML
    private TextArea labeldesc;
    @FXML
    private TextArea labelimage;
    @FXML
    private Button submitbutt;
    @FXML
    private TextArea labelnom;

    Connection mc;
    PreparedStatement ste;
    ObservableList <String> list = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> categ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mc=MyDB.getInstance().getCon();
         ResultSet rst = null;
        try {
            rst = mc.createStatement().executeQuery("SELECT * FROM `categorie`");
        } catch (SQLException ex) {
            Logger.getLogger(AjoutactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rst.next()){
                list.add(rst.getString(1));
                categ.getItems().add(rst.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjoutactController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouteractualite(ActionEvent event) throws SQLException {
        CrudActualites Act1 = new CrudActualites() ;
        Actualites act1 =new Actualites(labelnom.getText(),labeldesc.getText(),labelimage.getText());
        act1.setId_cat(categ.getValue());
        System.out.println(act1);
        Act1.ajouteract(act1);
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Actualiteé ajoutée");
            alert.show();
            labelnom.setText("");
            labeldesc.setText("");
            labelimage.setText("");
    }
    
}
