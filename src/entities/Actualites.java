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
public class Actualites {
    private String nom ;
    private String description  ;
    private String image ;
    private int id ;
    private String categorie ;
    private int rate ;
      static int test;

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getId_cat() {
        return id_cat;
    }
    private int id_cat;

    public Actualites(String nom, String description, String image, int id, String categorie, int id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id = id;
        this.categorie = categorie;
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "Actualites{" + "nom=" + nom + ", description=" + description + ", image=" + image + ", id=" + id + ", rate=" + rate + '}';
    }

    

    public Actualites(String nom, String description, String image, int id) {
        this.nom = nom;
        this.description  = description ;
        this.image = image;
        this.id = id;
    }

    public Actualites(String nom, String description , String image) {
        this.nom = nom;
        this.description  = description;
        this.image = image;
    }

    public Actualites(String nom, String description, String image, int id, String categorie) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id = id;
        this.categorie = categorie;
    }


    
    public Actualites() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Actualites(String nom, String description, String image, int id, int rate, int id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id = id;
        this.rate = rate;
        this.id_cat = id_cat;
    }
  

    public static int getTest() {
        return test;
    }

    public static void setTest(int test) {
        Actualites.test = test;
    }

    public Actualites(String nom, String description, String image, int id, int rate) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id = id;
        this.rate = rate;
    }
    
}
