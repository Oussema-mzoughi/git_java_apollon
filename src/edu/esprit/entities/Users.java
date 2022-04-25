/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 * @author Administrateur
 */
public class Users {
    private int id;

      private String nom,prenom,email,adresse,tel;


    public Users(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Users() {
    }
    public Users(String nom, String prenom, String email, String adresse, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
    }
    public Users(String nom) {
        this.nom = nom;
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


}
