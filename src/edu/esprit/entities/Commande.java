/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author amine
 */
public class Commande {
    
     private int id;
    private int numero;
    private Date date_commande;
    private Date date_livraison;
    private String livreur;
    private int totale;
    
    public Commande(int id, int numero, Date date_commande, Date date_livraison, String livreur, int totale) {
        this.id = id;
        this.numero = numero;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.livreur = livreur;
        this.totale = totale;
    }

    public Commande(int numero, Date date_commande, Date date_livraison, String livreur, int totale) {
        this.numero = numero;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
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
    
    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }
    
    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
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
