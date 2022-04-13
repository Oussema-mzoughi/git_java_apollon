/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;
/**
 *
 * @author Administrateur
 */
public class Questions {
    private int id ;
    private String titre;
    private Date date_post;
    private String message;
    private String solution ;
    private int user_id;
    private int nbr_vu;

    public Questions() {
    }

    public Questions(int id, String titre, Date date_post, String message, String solution, int user_id, int nbr_vu) {
        this.id = id;
        this.titre = titre;
        this.date_post = date_post;
        this.message = message;
        this.solution = solution;
        this.user_id = user_id;
        this.nbr_vu = nbr_vu;
    }

    public Questions(String titre, Date date_post, String message, String solution, int user_id, int nbr_vu) {
        this.titre = titre;
        this.date_post = date_post;
        this.message = message;
        this.solution = solution;
        this.user_id = user_id;
        this.nbr_vu = nbr_vu;
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

    public Date getDate_post() {
        return date_post;
    }

    public void setDate_post(Date date_post) {
        this.date_post = date_post;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNbr_vu() {
        return nbr_vu;
    }

    public void setNbr_vu(int nbr_vu) {
        this.nbr_vu = nbr_vu;
    }

   
    
    
    
    
    
}
