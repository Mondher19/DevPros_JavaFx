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

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;


public class CrudCategorie {
   Statement st;
    Connection mc;
    PreparedStatement ste;
    ResultSet rs;
    
    
      public CrudCategorie() {
        mc=MyDB.getInstance().getCon();
    }
    
    //yemchi
    public boolean ajoutercategorie(Categorie u) throws SQLException{
        PreparedStatement pre = mc.prepareStatement("INSERT INTO `categorie` (`sujet`)  VALUES (?)");
         pre.setString(1,u.getSujet());
int rs=pre.executeUpdate();
        //System.out.println(rs); 
         if (rs != 0) {
                System.out.println("categorie added");
               }
         else{
             System.out.println("error");
         }
       return false;
        
    }
    
   //yemchi
      public List<Categorie> getAll() throws SQLException {
        List<Categorie> cat = new ArrayList<>();
       PreparedStatement pre = mc.prepareStatement("SELECT * FROM categorie");
       
       ResultSet rs = pre.executeQuery();
        while (rs.next()) {
           int id= rs.getInt("id");
           String  sujet= rs.getString("sujet");
         
           Categorie Espace = new Categorie(sujet);
          cat.add(Espace);
           
    }
    return cat ;
      }
      //yekhdim
      public boolean updatecat(Categorie t,int id) throws SQLException {

        String sql = "UPDATE categorie SET sujet=? WHERE id=?";
        ste=mc.prepareStatement(sql); 
        ste.setString(1,t.getSujet());
        ste.setInt(2, id);
  
        
        if (ste.executeUpdate() != 0) {
                System.out.println(" updated");
                return true;
            }
       
         System.out.println("id not found!");
        return false;
    }
//yemchi
    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = mc.prepareStatement("DELETE FROM categorie WHERE id=?");
         pre.setInt(1, id);
         if (pre.executeUpdate() != 0) {
            System.out.println("Deleted");
            return true;}
         else {
        System.out.println("id Order not found!");
        return false;}
    }
  
    

    }
    