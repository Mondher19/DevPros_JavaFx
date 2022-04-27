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
public class Tournoi {
    private int id_tour , tour , nbr_joueur ; 
    String nom_tour ,desc_tour;

    public Tournoi() {
    }

    public Tournoi(int id_tour, int tour, int nbr_joueur, String nom_tour, String desc_tour) {
        this.id_tour = id_tour;
        this.tour = tour;
        this.nbr_joueur = nbr_joueur;
        this.nom_tour = nom_tour;
        this.desc_tour = desc_tour;
    }

    public Tournoi(int id_tour, int nbr_joueur, String nom_tour, String desc_tour) {
        this.id_tour = id_tour;
        this.nbr_joueur = nbr_joueur;
        this.nom_tour = nom_tour;
        this.desc_tour = desc_tour;
    }

    public Tournoi(int nbr_joueur, String nom_tour, String desc_tour) {
        this.nbr_joueur = nbr_joueur;
        this.nom_tour = nom_tour;
        this.desc_tour = desc_tour;
    }

    public int getId_tour() {
        return id_tour;
    }

    public void setId_tour(int id_tour) {
        this.id_tour = id_tour;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int getNbr_joueur() {
        return nbr_joueur;
    }

    public void setNbr_joueur(int nbr_joueur) {
        this.nbr_joueur = nbr_joueur;
    }

    public String getNom_tour() {
        return nom_tour;
    }

    public void setNom_tour(String nom_tour) {
        this.nom_tour = nom_tour;
    }

    public String getDesc_tour() {
        return desc_tour;
    }

    public void setDesc_tour(String desc_tour) {
        this.desc_tour = desc_tour;
    }

    @Override
    public String toString() {
        return "Type_Tournoi{" + "id_tour=" + id_tour + ", tour=" + tour + ", nbr_joueur=" + nbr_joueur + ", nom_tour=" + nom_tour + ", desc_tour=" + desc_tour + '}';
    }
    
    
    
    
}
