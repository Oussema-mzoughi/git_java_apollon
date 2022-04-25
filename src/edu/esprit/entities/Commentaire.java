
package edu.esprit.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author user
 */
public class Commentaire {
     int id;
    int evenements_id;
    int id_user;
    Date date;
    String content;
    String etat;

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", evenements_id=" + evenements_id + ", id_user=" + id_user + ", date=" + date + ", content=" + content + ", etat=" + etat + '}';
    }
    
    public Commentaire() {
    }

    public Commentaire(Date date, String content) {
        this.date = date;
        this.content = content;
    }


    public Commentaire(int evenements_id,Date date, String content) {
this.evenements_id=evenements_id;
        this.date = date;
        this.content = content;
    }
  public Commentaire(int id,int evenements_id,Date date, String content) {
        this.id=id;
        this.evenements_id=evenements_id;
        this.date = date;
        this.content = content;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvenementsId(int evenements_id) {
        this.evenements_id = evenements_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return id_user;
    }

    public int getEvenementsId() {
        return evenements_id;
    }

    public Date getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public String getContent() {

        return content;
    }

}
