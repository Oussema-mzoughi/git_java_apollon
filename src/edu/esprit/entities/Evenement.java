/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Evenement {

    private int id;
    private int nbr_max;
    private String titre, description, adresse, categorie, image;
    private Date date_deb, date_fin;
    private String time;

    public Evenement(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(int nbr_max, String titre, String description, String adresse, Date date_deb, Date date_fin, String categorie, String image, String time) {
        this.nbr_max = nbr_max;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.categorie = categorie;
        this.image = image;
        this.time = time;
    }

    public Evenement(int id, int nbr_max, String titre, String description, String adresse, Date date_deb, Date date_fin, String categorie, String image, String time) {
        this.id = id;
        this.nbr_max = nbr_max;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.categorie = categorie;
        this.image = image;
        this.time = time;
    }

    public Evenement() {
    }

    public Evenement(int id, String titre, String description, Date date_deb, Date date_fin, String time) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.time = time;
    }

    public Evenement(String titre, String description, Date date_deb, Date date_fin, String time) {
        this.titre = titre;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.time = time;
    }

    public int getNbr_max() {
        return nbr_max;
    }

    public void setNbr_max(int nbr_max) {
        this.nbr_max = nbr_max;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nbr_max=" + nbr_max + ", titre=" + titre + ", description=" + description + ", adresse=" + adresse + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", time=" + time + '}';
    }

}
