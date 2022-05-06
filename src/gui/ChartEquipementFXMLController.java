/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.ServiceTypeEquipement;
import entities.TypeEquipement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import tools.Myconn;

/**
 * FXML Controller class
 *
 * @author Mondh
 */
public class ChartEquipementFXMLController implements Initializable {

    @FXML
    private PieChart pieChart;
    
    ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
    Map<String, Integer> hm = new HashMap<String, Integer>();
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      
        try{
            Connection con = Myconn.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT name,categorie2eq_id,COUNT(id) FROM Equipement  group by categorie2eq_id");
            
            
            while(rs.next()){
                list.add(new PieChart.Data(rs.getString("name"),rs.getInt(2)));
        }
        
        pieChart.setData(list);
        
    }
        catch(SQLException ex){
    
  
    
}
    }

    @FXML
    private void back(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageProduitFXML.fxml"));
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     
}
