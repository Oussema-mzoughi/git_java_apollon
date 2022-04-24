package edu.esprit.entities;

import java.util.Date;

public class Article {
    
    private int id;
    private String titre;
    private String description;
    private String image;
    private String type;
    private Date date;
    
    public Article(int id, String titre, String description, String image, String type, Date date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.type = type;
        this.date = date;
    }

    public Article(String titre, String description, String image, String type, Date date) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.type = type;
        this.date = date;
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}