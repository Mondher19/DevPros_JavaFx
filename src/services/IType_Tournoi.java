/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Adem_
 */
public interface IType_Tournoi<T> {
       public void ajouterType_Tournoi(T t) throws SQLException;
    public List<T> afficherType_Tournoi() throws SQLException;
     public void ajouterType_TournoiAutrement(T tout) throws SQLException;
    public void modifierType_Tournoi(T t,int id) throws SQLException;
    public void deleteType_Tournoi(int id_tour) throws SQLException;
    
}
