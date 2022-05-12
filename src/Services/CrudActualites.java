/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



/**
 *
 * @author Yassine
 */
import entities.Actualites;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;


public class CrudActualites {
   Statement st;
    Connection mc;
    PreparedStatement ste;
    ResultSet rs;
    
    
      public CrudActualites() {
        mc=MyDB.getInstance().getCon();
    }
    
    //temchi
    public boolean ajouteract(Actualites u) throws SQLException{
       PreparedStatement pre = mc.prepareStatement("INSERT INTO actualites(nom,description,image,categorie_id,rate) Values(?,?,?,?,?)");
         pre.setString(1, u.getNom());
          pre.setString(2, u.getDescription());
         pre.setString(3, u.getImage());
         pre.setInt(4, u.getId_cat());
          pre.setInt(5, u.getRate());
         int rs=pre.executeUpdate();
        //System.out.println(rs); 
         if (rs != 0) {
                System.out.println("Actualites added");
               }
         else{
             System.out.println("error");
         }
       return false;
    }

       
    
    
     
    public List<Actualites> getAll() throws SQLException {
        List<Actualites> act= new ArrayList<>();
       PreparedStatement pre = mc.prepareStatement("SELECT * FROM actualites");
       
       ResultSet rs = pre.executeQuery();
        while (rs.next()) {
           
           String nom=rs.getString("nom");
           String description=rs.getString("description");
           String image=rs.getString("image");
           
          
           Actualites Espace = new Actualites(nom,description,image);
          act.add(Espace);
         
        }
       return act;
     
    }
    public List<Actualites> affiche() throws SQLException {
        List<Actualites> act= new ArrayList<>();
       PreparedStatement pre = mc.prepareStatement("SELECT nom,description,image,id,rate FROM actualites");
       
       ResultSet rs = pre.executeQuery();
        while (rs.next()) {
           int id= rs.getInt("id");
           String nom=rs.getString("nom");
           String description=rs.getString("description");
           String image=rs.getString("image");
           int rate=rs.getInt("rate");
          
           Actualites Espace = new Actualites(nom,description,image,id,rate);
          act.add(Espace);
         
        }
       return act;
     
    }
     public boolean updatecat(Actualites t,int id) throws SQLException {

        String sql = "UPDATE actualites SET nom=?,image=?,description=?,categorie_id=?,rate=? WHERE id=?";
        ste=mc.prepareStatement(sql); 
        ste.setString(1,t.getNom());
        ste.setString(2,t.getImage());
         ste.setString(3,t.getDescription());
         ste.setInt(4, t.getId_cat());
        ste.setInt(5,t.getRate());
        ste.setInt(6, id);
  
        
        if (ste.executeUpdate() != 0) {
                System.out.println(" updated");
                return true;
            }
       
         System.out.println("id not found!");
        return false;
    }
  //yemchi
     public boolean delete(int id) throws SQLException {
        PreparedStatement pre = mc.prepareStatement("DELETE FROM `actualites` WHERE `id`=?");
         pre.setInt(1, id);
         if (pre.executeUpdate() != 0) {
            System.out.println("Deleted");
            return true;}
         else {
        System.out.println("id Order not found!");
        return false;}
    }
    public boolean updaterate(int rate, int id) {
     try {
            PreparedStatement pre = mc.prepareStatement("UPDATE actualites SET rate=? WHERE id=?");
            pre.setInt(1, rate);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Rating updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;
    }
   
    }
    


   
