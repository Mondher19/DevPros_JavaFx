/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Yassine
 */
public class mainact extends Application {

    /**
     * @param args the command line arguments
     */
   
    Stage stage;
    Parent parent;
    
      @Override
    public void start(Stage primarystage) throws IOException {

    
    this.stage= primarystage;
   
           
         parent = FXMLLoader.load(getClass().getResource("/gui/FXMLajoutform.fxml"));
          // parent = FXMLLoader.load(getClass().getResource("/gui/Ajoutcategorie.fxml"));
            Scene scene= new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Gestion categorie");
            stage.initStyle(StageStyle.UTILITY); //remove Max & Min option
            stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

      launch(args);
         
    
                    }
    
   }