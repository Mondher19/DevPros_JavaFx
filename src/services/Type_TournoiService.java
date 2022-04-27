/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Type_Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author eyaam
 */
public class Type_TournoiService implements IType_Tournoi<Type_Tournoi> {

    Connection connexion;
    Statement stm;

    public Type_TournoiService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    

    @Override
    public List<Type_Tournoi> afficherType_Tournoi() throws SQLException {
        List<Type_Tournoi> tour = new ArrayList<>();
        String req = "select * from type_tournoi";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Type_Tournoi p = new Type_Tournoi(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom_type"),
                    rst.getString("desc_type")
                   );
            tour.add(p);
        }
        return tour;
    }

    @Override
    public void ajouterType_Tournoi(Type_Tournoi tour) throws SQLException {
 String req = "INSERT INTO `tournoi` (`nom_type`, `desc_type`) VALUES ( '"
                + tour.getNom_type() + "', '" + tour.getDesc_type() + "') ";
        try {
            stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     //***********************update**********

    @Override
    public void modifierType_Tournoi(Type_Tournoi t, int id) throws SQLException {
       
        String req = "UPDATE type_tournoi SET   nom_type = ?, desc_tour = ?    where id=" + id;
        PreparedStatement pre = connexion.prepareStatement(req);
      
        pre.setString(1, t.getNom_type());
        pre.setString(2, t.getDesc_type());
        
        
        pre.executeUpdate();
    }
    
    
    //***************sup**************
    @Override
    public void deleteType_Tournoi(int id) throws SQLException {
         String req = "DELETE FROM type_tournoi WHERE id = "+ id;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void deleteType_TournoiParNom(String nom) throws SQLException {
         String req = "DELETE FROM type_tournoi WHERE nom_type = "+ nom;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
     public void ajouterType_TournoiAutrement(Type_Tournoi tour) throws SQLException {
        String req = "INSERT INTO `tournoi` (`nom_type`, `desc_type` ) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, tour.getNom_type());
        ps.setString(2, tour.getDesc_type());
       
        ps.executeUpdate();
    }

}
