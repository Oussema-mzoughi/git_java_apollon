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
public class Commande {
    
    private int id;
    private int numero;
    private String livreur;
    private int totale;
    
    public Commande(int id, int numero, String livreur, int totale) {
        this.id = id;
        this.numero = numero;
        this.livreur = livreur;
        this.totale = totale;
    }

    public Commande(int numero, String livreur, int totale) {
        this.numero = numero;
       
        this.livreur = livreur;
        this.totale = totale;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }
    
    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }
}
