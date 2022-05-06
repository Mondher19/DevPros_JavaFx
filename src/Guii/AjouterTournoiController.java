/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guii;

import animatefx.animation.Bounce;
import animatefx.animation.GlowText;
import entities.Tournoi;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.TournoiServices;
import utils.email;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class AjouterTournoiController implements Initializable {

    @FXML
    private TextArea tdescription;
    
     
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Tournoi tournoi = null;
    private boolean update;
    int studentId;
    @FXML
    private Label lusername;
    @FXML
    private TextField ttour;
    @FXML
    private TextField tnbr;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    public void setType_emplacement(String tour) {
        ttour.setText(tour);
    }
 
      /* this.imageuploadm.setAccessibleText(he.getImage());*/

    public static Tournoi he;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    private void update(ActionEvent event) {
            new GlowText(titre, Color.BLACK, Color.WHITE).play();
            new Bounce(ajoutbtn).play();
        TournoiServices sp = new TournoiServices();

            String nom=  ttour.getText();
       Integer nbr = Integer.parseInt(tnbr.getText());
        String description =  tdescription.getText();
       
        if (nom.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "le nom  ne doit pas etre vide");
        }  else if  (nbr.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "le prix ne doit pas etre vide");
    }
          else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
    } 
 
        else {
              Tournoi p=  new Tournoi(nbr,nom,description);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.Update(p,12);

            JOptionPane.showMessageDialog(null, "Tournoi modifiée !");
    }
    }
    

    @FXML
    private void BtnAjouterP(ActionEvent event) {
        Tournoi p = new Tournoi(Integer.parseInt(tnbr.getText()),ttour.getText(), tdescription.getText());
         TournoiServices ps = new  TournoiServices();
    
        String vue = ttour.getText();
        String nbr = tnbr.getText();
        String desc = tdescription.getText();
        try {
                if ( vue.isEmpty() || desc.isEmpty() ||  (vue.length() < 3 || desc.length() < 3 )) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill the data");
                alert.show();
                ttour.setText("");
                tdescription.setText("");
            }
                else   if (  vue.length() < 3 || desc.length() < 3 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Vue et Descrptiob doit contient au moins 3 caractères");
                alert.show();
            }
                else if ( vue != desc ){
                        
                        ps.ajouterTournoi(p);
                            ttour.setText("");
                            tnbr.setText("");
                            tdescription.setText("");}
                else {
                     getQuery();
                }
               
           
             email n = new email();
            n.s(); 
        }

            
            
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
                 Notifications notificationBuilder = Notifications.create()
                    .title("Tournoi")
                    .text("Votre Tournoi est Ajouter")//affichage notif
                    .graphic(null)
                    .hideAfter(Duration.seconds(50))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked on notif");
                        }
                    });
            notificationBuilder.showConfirm();
    }

    
    
      void setTextField( String v, String d) {

        ttour.setText(v);
        tdescription.setText(d);
        tnbr.setText(d);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

 private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `tournoi`( `nom_tour`, `des_tour`, `nbr_joueur`) VALUES (?,?,?)";

        }else{
            query = "UPDATE `tournoi` SET "
                    + "`nom_tour`=?,"
                    + "`des_tour`=?"
                   + "`nbr_joueur`=? WHERE id_tour = '"+tournoi.getId_tour()+"'";
        }

    }



    @FXML
    private void Clean(MouseEvent event) {
         ttour.setText(null);
          tnbr.setText(null);
        tdescription.setText(null);
    }
    
    
    

}
