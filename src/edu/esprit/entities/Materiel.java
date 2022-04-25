/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 * @author hp
 */
public class Materiel {
    private int id;
    private int salle_id_id;
    private String type;
    private String nom;
    private String code;

    public Materiel() {
    }

    public Materiel(int id, int salle_id_id, String type, String nom, String code) {
        this.id = id;
        this.salle_id_id = salle_id_id;
        this.type = type;
        this.nom = nom;
        this.code = code;
    }

    public Materiel(int salle_id_id, String type, String nom, String code) {
        this.salle_id_id = salle_id_id;
        this.type = type;
        this.nom = nom;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalle_id_id() {
        return salle_id_id;
    }

    public void setSalle_id_id(int salle_id_id) {
        this.salle_id_id = salle_id_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
