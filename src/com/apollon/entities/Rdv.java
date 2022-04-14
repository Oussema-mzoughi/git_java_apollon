package com.apollon.entities;

import com.apollon.utils.RelationObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rdv {

    private int id;
    private RelationObject userId;
    private int partenaireId;
    private LocalDate debut;
    private LocalDate fin;
    private int etat;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Rdv(int id, RelationObject userId, int partenaireId, LocalDate debut, LocalDate fin, int etat, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.partenaireId = partenaireId;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Rdv(RelationObject userId, int partenaireId, LocalDate debut, LocalDate fin, int etat, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.partenaireId = partenaireId;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RelationObject getUser() {
        return userId;
    }

    public void setUserId(RelationObject userId) {
        this.userId = userId;
    }

    public int getPartenaireId() {
        return partenaireId;
    }

    public void setPartenaireId(int partenaireId) {
        this.partenaireId = partenaireId;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Rdv{" +
                "id=" + id +
                ", userId=" + userId +
                ", partenaireId=" + partenaireId +
                ", debut=" + debut +
                ", fin=" + fin +
                ", etat=" + etat +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}