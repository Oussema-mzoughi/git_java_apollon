/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author amine
 */
public class Produit {
    
    private int id;
    private String nom;
    private float prix;
    private String description;
    private String image;
    private int stock;

    public Produit(int id, String nom, float prix, String description, String image, int stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.stock = stock;
    }

    public Produit(String nom, float prix, String description, String image, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.stock = stock;
    }

    public Produit() {
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

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
}
