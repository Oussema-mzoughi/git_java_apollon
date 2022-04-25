/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Evenement;
import edu.esprit.tools.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class CommentaireServices {
        private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CommentaireServices() {
         cnx = Connexion.getInstance().getCnx();
    }
    
     public void ajouterCommentaire(Commentaire c) {
        String query = "insert into comments (evenement_id,content,date) values(?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, (int) c.getEvenementsId());
            ste.setString(2, c.getContent());
            ste.setDate(3, (Date) c.getDate());
            ste.executeUpdate();
            System.out.println("Commentaire Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
        public void supprimerCommentaire(int id) {
        try {
            String req = "DELETE FROM comments WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
             
        public void UpdateCommentaire(Commentaire c)
        { 
        try {
String req ="UPDATE comments SET content = ? WHERE id = ? ";
             PreparedStatement ps = cnx.prepareStatement(req);

             ps.setString(1, c.getContent());

            ps.setInt(2, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();
            ps.close();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }
        
        public List<Commentaire> afficherCommentaire() {
        List<Commentaire> Commentaires = new ArrayList<>();
        String sql = "select * from comments";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Commentaire p = new Commentaire();
                p.setId(rs.getInt("id"));
                //System.out.println(rs.getInt("id"));
               
                p.setEvenementsId(rs.getInt("evenement_id"));
                //System.out.println(rs.getInt("evenement_id"));

                
                
                p.setIdUser(rs.getInt("user_id"));
               
                Date date = rs.getDate("date");
                p.setDate(date);
                
                p.setContent(rs.getString("content"));
                
                p.setEtat(rs.getString("etat"));
                //System.out.println(p);
                Commentaires.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Commentaires;

    }


    public List<Commentaire> getAllCommentairesByEvent(int idEvent) {
        ObservableList<Commentaire> listCommentaire = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement
                    = cnx.prepareStatement("select * from comments WHERE evenement_id = ?");
            preparedStatement.setInt(1, idEvent);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listCommentaire.add(new Commentaire(
                        resultSet.getInt("id"),
                        resultSet.getInt("evenement_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("content")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage (tout) commentaire : " + e.getMessage());
        }
        return listCommentaire;
    }

    public List<String> afficherCommentaire2(int id) {
        List<String> Commentaires = new ArrayList<>();
        String sql = "select * from comments WHERE evenement_id="+id;
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {                
                
                Commentaires.add(rs.getString("content"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Commentaires;

    }

public List<String> afficherCommentaire3(int id) {
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        List<String> Commentaires = new ArrayList<>();
        String sql = "select * from comments WHERE evenement_id="+id;
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {                
                 String strDate = dateFormat.format(rs.getDate("date"));
                Commentaires.add(strDate);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Commentaires;

    }
        
}
