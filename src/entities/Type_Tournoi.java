/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Adem_
 */
public class Type_Tournoi {
    private int id ; 
    String nom_type ,desc_type;

    public Type_Tournoi(int id, String nom_type, String desc_type) {
        this.id = id;
        this.nom_type = nom_type;
        this.desc_type = desc_type;
    }

    public Type_Tournoi() {
    }

    public Type_Tournoi(String nom_type, String desc_type) {
        this.nom_type = nom_type;
        this.desc_type = desc_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_type() {
        return nom_type;
    }

    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }

    public String getDesc_type() {
        return desc_type;
    }

    public void setDesc_type(String desc_type) {
        this.desc_type = desc_type;
    }

    @Override
    public String toString() {
        return "Type_Tournoi{" + "id=" + id + ", nom_type=" + nom_type + ", desc_type=" + desc_type + '}';
    }
    
    
    
    
    
}
