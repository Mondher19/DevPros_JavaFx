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
public interface ITournoi<T> {
         public void ajouterTournoi(T t) throws SQLException;
    public List<T> afficherTournoi() throws SQLException;
     public void ajouterTournoiAutrement(T tout) throws SQLException;
    public void modifierTournoi(T t,int id) throws SQLException;
    public void deleteTournoi(int id_tour) throws SQLException;
    
}
