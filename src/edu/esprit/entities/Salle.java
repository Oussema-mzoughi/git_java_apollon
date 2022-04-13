/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author hp
 */
public class Salle {

    public static Object getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private int id,users_id	;
private String 	nom_salle ,adresse_salle ,ville_salle ,email ,num_tel,nomgerant,nomdirecteur,image;

    public Salle() {
    }

    public Salle(int id, int users_id, String nom_salle, String adresse_salle, String ville_salle, String email, String num_tel, String nomgerant, String nomdirecteur, String image) {
        this.id = id;
        this.users_id = users_id;
        this.nom_salle = nom_salle;
        this.adresse_salle = adresse_salle;
        this.ville_salle = ville_salle;
        this.email = email;
        this.num_tel = num_tel;
        this.nomgerant = nomgerant;
        this.nomdirecteur = nomdirecteur;
        this.image = image;
    }

    public Salle(int users_id, String nom_salle, String adresse_salle, String ville_salle, String email, String num_tel, String nomgerant, String nomdirecteur, String image) {
        this.users_id = users_id;
        this.nom_salle = nom_salle;
        this.adresse_salle = adresse_salle;
        this.ville_salle = ville_salle;
        this.email = email;
        this.num_tel = num_tel;
        this.nomgerant = nomgerant;
        this.nomdirecteur = nomdirecteur;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getAdresse_salle() {
        return adresse_salle;
    }

    public void setAdresse_salle(String adresse_salle) {
        this.adresse_salle = adresse_salle;
    }

    public String getVille_salle() {
        return ville_salle;
    }

    public void setVille_salle(String ville_salle) {
        this.ville_salle = ville_salle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getNomgerant() {
        return nomgerant;
    }

    public void setNomgerant(String nomgerant) {
        this.nomgerant = nomgerant;
    }

    public String getNomdirecteur() {
        return nomdirecteur;
    }

    public void setNomdirecteur(String nomdirecteur) {
        this.nomdirecteur = nomdirecteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", users_id=" + users_id + ", nom_salle=" + nom_salle + ", adresse_salle=" + adresse_salle + ", ville_salle=" + ville_salle + ", email=" + email + ", num_tel=" + num_tel + ", nomgerant=" + nomgerant + ", nomdirecteur=" + nomdirecteur + ", image=" + image + '}';
    }

   

}
