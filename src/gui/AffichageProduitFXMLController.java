/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.ServiceEquipement;
import entities.Equipement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AffichageProduitFXMLController implements Initializable {

    @FXML
    private TableView<Equipement> listeq;
    @FXML
    private TableColumn<Equipement, Integer> id;
    @FXML
    private TableColumn<Equipement, String> nom;
    @FXML
    private TableColumn<Equipement, String> marque;
    @FXML
    private TableColumn<Equipement, String> categorie;
    @FXML
    private TableColumn<Equipement, String> description;
    @FXML
    private TableColumn<Equipement, Float> prix;
    @FXML
    private TableColumn<Equipement, String> image;
    @FXML
    private Button delete_equipement;
    @FXML
    private Button update_equipement;
    @FXML
    private Button ajouter_equipement;
    @FXML
    private Label lnom;
    @FXML
    private Label lcategorie;
    @FXML
    private Label ldesc;
    @FXML
    private Label lprix;
    @FXML
    private Label limage;
    private File file;
    private Image image1;
    private String pic;
    @FXML
    private Label lmarque;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfcategorie;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfmarque;

    /**
     * Initializes the controller class.
     */
      /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
              ServiceEquipement cs = new ServiceEquipement();
        List Equipement = cs.listerEquipement();
        ObservableList list = FXCollections.observableArrayList(Equipement);
        listeq.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name_e"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque_e"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("typequipement_e"));
        
        
    }   

    @FXML
    private void table_view_equipement_clicked(MouseEvent event) {
        delete_equipement.setDisable(false);
        update_equipement.setDisable(false);
            tfnom.setText(listeq.getSelectionModel().getSelectedItem().getName_e());
            tfmarque.setText(listeq.getSelectionModel().getSelectedItem().getMarque_e());
            tfcategorie.setText(listeq.getSelectionModel().getSelectedItem().getTypequipement_e()+"");
            tfimage.setText(listeq.getSelectionModel().getSelectedItem().getImage_e());
            tfprix.setText(listeq.getSelectionModel().getSelectedItem().getPrix_e()+"");
            tfdescription.setText(listeq.getSelectionModel().getSelectedItem().getDescription_e()+"");
       
      
    }

    @FXML
    private void del_on_click(ActionEvent event) throws IOException {
        try{
            ServiceEquipement cs = new ServiceEquipement();
        cs.supprimerEquipement(listeq.getSelectionModel().getSelectedItem().getId_e());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            ServiceEquipement cs = new ServiceEquipement();
            List Equipement = cs.listerEquipement();
        ObservableList list = FXCollections.observableArrayList(Equipement);
        listeq.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name_e"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque_e"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("typequipement_e"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            
    }
    
    

    @FXML
    private void ajouter_on_click(ActionEvent event) throws IOException{
    ServiceEquipement ps = new ServiceEquipement();

        Equipement p = new Equipement(Integer.parseInt(tfcategorie.getText()),tfnom.getText(),tfmarque.getText(),tfdescription.getText(),tfimage.getText(),Float.parseFloat(tfprix.getText()));
        try {
            ps.ajoutEquipement(p);
            try{
            ServiceEquipement cs = new ServiceEquipement();
            List Equipement = cs.listerEquipement();
        ObservableList list = FXCollections.observableArrayList(Equipement);
        listeq.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name_e"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque_e"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("typequipement_e"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("personne ajoutee");
            alert.show();
            
            tfnom.setText("");
            tfmarque.setText("");
            tfcategorie.setText("");
            tfimage.setText("");
            tfprix.setText("");
            tfdescription.setText("");
               
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
    }

    @FXML
    private void update_on_click(ActionEvent event) {
        
          ServiceEquipement ps = new ServiceEquipement();

        Equipement p = new Equipement(Integer.parseInt(tfcategorie.getText().toString()),tfnom.getText().toString(),tfmarque.getText().toString(),tfdescription.getText().toString(),tfimage.getText().toString(),Float.parseFloat(tfprix.getText().toString()));
        try {
            ps.updateEquipement(p, listeq.getSelectionModel().getSelectedItem().getId_e());
            try{
            ServiceEquipement cs = new ServiceEquipement();
            List Equipement = cs.listerEquipement();
        ObservableList list = FXCollections.observableArrayList(Equipement);
        listeq.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name_e"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque_e"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("typequipement_e"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("personne ajoutee");
            alert.show();
            
            tfnom.setText("");
            tfmarque.setText("");
            tfcategorie.setText("");
            tfimage.setText("");
            tfprix.setText("");
            tfdescription.setText("");
               
            
            
        } catch (Exception e) {
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
}
