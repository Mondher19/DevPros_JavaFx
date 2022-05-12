/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.CrudCategorie;
import entities.Categorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AjoutcategorieController implements Initializable {

    @FXML
    private Button buttoncategorie;
    @FXML
    private TextArea labelsujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutcat(ActionEvent event) throws SQLException {
    CrudCategorie Act1 = new CrudCategorie() ;
        Categorie act1 =new Categorie(labelsujet.getText());
        Act1.ajoutercategorie(act1);
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("actualité ajoutée");
            alert.show();
            labelsujet.setText("");
            
    }
}
