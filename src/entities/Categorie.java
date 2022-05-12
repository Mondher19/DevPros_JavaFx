/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Yassine
 */
public class Categorie {
    private String sujet ;
    private int id ;

    public int getId() {
        return id;
    }
    public Categorie() {
       
    }

    public Categorie(String sujet) {
        this.sujet = sujet;
    }

    public Categorie(String sujet, int id) {
        this.sujet = sujet;
        this.id = id;
    }

    

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
