/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Yassine
 */

public class Affichageback extends Application{

    /**
     * @param args the command line arguments
     */
    Stage stage;
    Parent parent;
    
      @Override
    public void start(Stage primarystage) throws IOException {

    
    this.stage= primarystage;
   
           
      //     parent = FXMLLoader.load(getClass().getResource("/gui/FXMLajoutform.fxml"));
           parent = FXMLLoader.load(getClass().getResource("/gui/AffichageActualites.fxml"));
            Scene scene= new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Gestion Actualités");
            stage.initStyle(StageStyle.UTILITY); //remove Max & Min option
            stage.show(); 
    }

    public static void main(String[] args) {
        // TODO code application logic here
         launch(args);
    }
    
}
