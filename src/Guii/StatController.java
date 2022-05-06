/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guii;

import static com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Adem_
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           String query = "select nom_tour,nbr_joueur from `tournoi`  ";
        System.out.println(query);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            Connection con = MyDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
               barchart.setTitle("Stats");
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("nom_tour").toString(), rs.getInt("nbr_joueur")));
            }
            barchart.getData().add(series);
        } catch (Exception e) {
        }
        // TODO
    }   
    
    
      void handleClose(MouseEvent event) {
        if(event.getSource() == close){
            Stage stg = (Stage) close.getScene().getWindow();
            stg.close();
        }
    }
    
}
