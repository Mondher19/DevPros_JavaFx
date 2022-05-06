/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Tournoi;
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
public class TournoiServices implements ITournoi<Tournoi> {

    Connection connexion;
    Statement stm;

    public TournoiServices() {
        connexion = MyDB.getInstance().getConnexion();
    }

    

    @Override
    public List<Tournoi> afficherTournoi() throws SQLException {
        List<Tournoi> tour = new ArrayList<>();
        String req = "select * from tournoi";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Tournoi p = new Tournoi(rst.getInt("id_tour"),
                    rst.getInt("tour"),
                     rst.getInt("nbr_joueur"),//or rst.getInt(1)
                    rst.getString("nom_tour"),
                    rst.getString("desc_tour")
                   );
            tour.add(p);
        }
        return tour;
    }

    @Override
    public void ajouterTournoi(Tournoi tour) throws SQLException {
 String req = "INSERT INTO `tournoi` ( `nbr_joueur`,`nom_tour`, `desc_tour`) VALUES (  '" + tour.getNbr_joueur()+ "' ,'"
                + tour.getNom_tour() + "', '" + tour.getDesc_tour() + "') ";
        try {
            stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     //***********************update**********

    @Override
    public void modifierTournoi(Tournoi t, int id) throws SQLException {
       
        String req = "UPDATE tournoi SET   nom_tour = ?, desc_tour = ? , nbr_joueur = ?   where id_tour=" + id;
        PreparedStatement pre = connexion.prepareStatement(req);
      
        pre.setString(1, t.getNom_tour());
        pre.setString(2, t.getDesc_tour());
         pre.setInt(3, t.getNbr_joueur());
        
        pre.executeUpdate();
    }
    
    
    
    
   
    public void Update(Tournoi p, int id) {
        try {
        String req = " update tournoi set nom_tour='"+p.getNom_tour()+"', desc_tour='"+p.getDesc_tour()+
                            "', nbr_joueur='"+p.getNbr_joueur()+"' where id_tour ='"+id+"' ";
        
            PreparedStatement pre = connexion.prepareStatement(req);
       
        pre.executeUpdate();
        System.out.println(" publication modifiè !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        
    }

    
    
    
    //***************sup**************
    @Override
    public void deleteTournoi(int id) throws SQLException {
         String req = "DELETE FROM tournoi WHERE id_tour = "+ id;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void deleteTournoiParNom(String nom) throws SQLException {
         String req = "DELETE FROM tournoi WHERE nom_tour = "+ nom;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
     public void ajouterTournoiAutrement(Tournoi tour) throws SQLException {
        String req = "INSERT INTO `tournoi` (`nom_tour`, `desc_tour` , `nbr_joueur`) "
                + "VALUES ( ?, ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
       ps.setString(1, tour.getNom_tour());
        ps.setString(2, tour.getDesc_tour());
         ps.setInt(3, tour.getNbr_joueur());
        ps.executeUpdate();
    }

     
      public ArrayList<Tournoi> TrierParId() {

        ArrayList<Tournoi> List = new ArrayList<>();
        try {

//            String requete = "select * from membre where role != 'Admin' ORDER BY id DESC ";
            String req = "select * from tournoi ORDER BY nom_tour";
          PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Tournoi m = new Tournoi();

               
                m.setNom_tour(rs.getString("nom_tour"));
                m.setNbr_joueur(rs.getInt("nbr_joueur"));
                m.setDesc_tour(rs.getString("desc_tour"));
              
               
          

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
      
      public List<Tournoi> RechercherProduit(String x) {
        ArrayList<Tournoi> List = new ArrayList<>();
        try {
            String req = "Select * from tournoi where nom_tour like '%" + x + "%' or nbr_joueur like '%" + x + "%' or desc_tour like '%" + x + "%'  ";
            System.out.println("aa: "+x);
       PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
                while (rs.next()) {
                    Tournoi m = new Tournoi();

              
                m.setNom_tour(rs.getString("nom_tour"));
                m.setNbr_joueur(rs.getInt("nbr_joueur"));
                m.setDesc_tour(rs.getString("desc_tour"));
                    System.out.println("x:" +x);
                    List.add(m);
                }

            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (List.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return List;
    }
    
    
     
}
