package edu.esprit.entities;

import edu.esprit.gui.back.rdv.ShowAllController;
import edu.esprit.utils.RelationObject;

import java.time.LocalDateTime;

public class Rdv implements Comparable<Rdv> {

    private int id;
    private RelationObject userId;
    private RelationObject partenaire;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private int etat;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Rdv(int id, RelationObject userId, RelationObject partenaire, LocalDateTime debut, LocalDateTime fin, int etat, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.partenaire = partenaire;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Rdv(RelationObject userId, RelationObject partenaire, LocalDateTime debut, LocalDateTime fin, int etat, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.partenaire = partenaire;
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

    public RelationObject getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(RelationObject partenaire) {
        this.partenaire = partenaire;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
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
                ", partenaire=" + partenaire +
                ", debut=" + debut +
                ", fin=" + fin +
                ", etat=" + etat +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int compareTo(Rdv rdv) {
        switch (ShowAllController.compareVar) {
            case "partenaire":
                return this.getPartenaire().getName().compareTo(rdv.getPartenaire().getName());
            case "Date de debut":
                return this.getDebut().compareTo(rdv.getDebut());
            case "Date de fin":
                return this.getFin().compareTo(rdv.getFin());
            case "createdAt":
                return this.getCreatedAt().compareTo(rdv.getCreatedAt());
            case "updatedAt":
                return this.getUpdatedAt().compareTo(rdv.getUpdatedAt());
            default:
                return 0;
        }
    }
}