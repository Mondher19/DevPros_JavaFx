/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.ServiceEquipement;
import Services.ServiceTypeEquipement;
import entities.TypeEquipement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import tools.Upload;

/**
 * FXML Controller class
 *
 * @author Mondh
 */
public class AffichageCatFXMLController implements Initializable {

    @FXML
    private TableView<TypeEquipement> listcat;
    @FXML
    private TableColumn<TypeEquipement, Integer> id;
    @FXML
    private TableColumn<TypeEquipement, String> lib;
    @FXML
    private Label lalib;
    @FXML
    private TextField tflib;
    @FXML
    private Label limage;
    private File file;
    private Image image1;
    private String pic;
    @FXML
    private TextField tfimage;
    @FXML
    private TableColumn<TypeEquipement,String> image;
    @FXML
    private Button update_cat;
    @FXML
    private Button spp_cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         ServiceTypeEquipement cs = new ServiceTypeEquipement();
        List TypeEquipement = cs.listerCat();
        ObservableList list = FXCollections.observableArrayList(TypeEquipement);
        listcat.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_et"));
        lib.setCellValueFactory(new PropertyValueFactory<>("name_et"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_et"));
      
        
    }    
    
     public boolean checkFields() {
     
        
        Boolean isvalid = true;
        if (tflib.getText().isEmpty()) {
            lalib.setVisible(true);
            isvalid = false;
        }
        if (tfimage.getText().isEmpty()) {
            limage.setVisible(true);
            isvalid = false;
        }
    
        return isvalid;
    }

    @FXML
    private void table_view_cat_clicked(MouseEvent event) {
        
         spp_cat.setDisable(false);
         update_cat.setDisable(false);
        
            tflib.setText(listcat.getSelectionModel().getSelectedItem().getName_et());  
            tfimage.setText(listcat.getSelectionModel().getSelectedItem().getImage_et());
           
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
       if (tfimage.getText().isEmpty() || tflib.getText().isEmpty())
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
       
        ServiceTypeEquipement ps = new ServiceTypeEquipement();

        TypeEquipement p = new TypeEquipement(tflib.getText(),tfimage.getText());
        try {
            ps.ajoutTypeEquipement(p);
            try{
            ServiceTypeEquipement cs = new ServiceTypeEquipement();
            List TypeEquipement = cs.listerCat();
        ObservableList list = FXCollections.observableArrayList(TypeEquipement);
        listcat.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_et"));
        lib.setCellValueFactory(new PropertyValueFactory<>("name_et"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_et"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Categorie ajoutee");
            alert.show();
            
            tflib.setText("");           
            tfimage.setText("");
           
               
            
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
    }}

    @FXML
    private void update(ActionEvent event) throws IOException {
        
          if (tfimage.getText().isEmpty() || tflib.getText().isEmpty())
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
        
            ServiceTypeEquipement ps = new ServiceTypeEquipement();

            TypeEquipement p = new TypeEquipement(tflib.getText(),tfimage.getText());
        try {
            ps.updateTypeEquipement(p, listcat.getSelectionModel().getSelectedItem().getId_et());
            try{
            ServiceTypeEquipement cs = new ServiceTypeEquipement();
            List TypeEquipement = cs.listerCat();
        ObservableList list = FXCollections.observableArrayList(TypeEquipement);
        listcat.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_et"));
        lib.setCellValueFactory(new PropertyValueFactory<>("name_et"));     
        image.setCellValueFactory(new PropertyValueFactory<>("image_et"));
       
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("personne ajoutee");
            alert.show();
            
            tfimage.setText("");
            tflib.setText("");
           
               
            
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
    
    }}

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
          try{
            ServiceTypeEquipement cs = new ServiceTypeEquipement();
        cs.supprimerTypeEquipement(listcat.getSelectionModel().getSelectedItem().getId_et());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            ServiceTypeEquipement cs = new ServiceTypeEquipement();
            List TypeEquipement = cs.listerCat();
        ObservableList list = FXCollections.observableArrayList(TypeEquipement);
        listcat.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_et"));
        lib.setCellValueFactory(new PropertyValueFactory<>("name_et"));       
        image.setCellValueFactory(new PropertyValueFactory<>("image_et"));
      
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
         
    }

    @FXML
    private void addImg(ActionEvent event) throws IOException {
        
              FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            
            pic=(file.toURI().toString());
         //  pic=new Upload().upload(file,"uimg");
           pic=new Upload().upload(file,"");
            System.out.println(pic);
   //   image= new Image("http://localhost/uimg/"+pic);
            tfimage.setText(pic);
           image1= new Image("http://localhost/projectpidev/public/uploads"
                   + ""+pic);
  }

    @FXML
    private void ajoutereq(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageProduitFXML.fxml"));
            Parent root = loader.load();
            limage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
