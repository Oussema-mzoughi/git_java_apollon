package edu.esprit.entities;

import edu.esprit.utils.RelationObject;

import java.time.LocalDate;

public class Abo {

    private int id;
    private LocalDate createdAt;
    private RelationObject userId;
    private RelationObject sdpId;
    private String duree;
    private String etat;

    public Abo(int id, LocalDate createdAt, RelationObject userId, RelationObject sdpId, String duree, String etat) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.sdpId = sdpId;
        this.duree = duree;
        this.etat = etat;
    }

    public Abo(LocalDate createdAt, RelationObject userId, RelationObject sdpId, String duree, String etat) {
        this.createdAt = createdAt;
        this.userId = userId;
        this.sdpId = sdpId;
        this.duree = duree;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public RelationObject getUser() {
        return userId;
    }

    public void setUserId(RelationObject userId) {
        this.userId = userId;
    }

    public RelationObject getSdp() {
        return sdpId;
    }

    public void setSdpId(RelationObject sdpId) {
        this.sdpId = sdpId;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}