/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author user
 */
public class Categorie {
    
    int id;
    String type;
    

    public Categorie() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
      public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
