package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Users;
import edu.esprit.tools.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EvenementServices {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public EvenementServices() {
        cnx = Connexion.getInstance().getCnx();
    }

    public void AddEvenementPst(Evenement c) throws SQLException {

        String req = "insert into evenement (titre, description, nbr_max,adresse,category_id,date_deb,date_fin,time,image) values (?,?,?,?,?,?,?,?,?)";
        try {

            pst = cnx.prepareStatement(req);

            pst.setString(1, c.getTitre());
            pst.setString(2, c.getDescription());
            pst.setInt(3, c.getNbr_max());
            pst.setString(4, c.getAdresse());
            pst.setInt(5, Integer.parseInt(c.getCategorie()));
            pst.setDate(6, (Date) c.getDate_deb());
            pst.setDate(7, (Date) c.getDate_fin());
            pst.setString(8, LocalTime.now().toString());
            pst.setString(9, "");
            //pst.setDate(5, (Date) c.getTime());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeleteEvenement(int c) {
        try {
            String req = "DELETE from evenement  WHERE id =" + c + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void udpatePerticiper(int id, int nbMax) {
        String req = "UPDATE evenement set Nbr_max=? WHERE id =" + id + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, nbMax);
            pst.executeUpdate();
            System.out.println("nbMax modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }
    }

    public void UpdateEvenement(Evenement c, int cu) {
        String req = "UPDATE evenement set titre=? , Description=? , Nbr_max=?, Adresse=? WHERE id =" + cu + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, c.getTitre());
            pst.setString(2, c.getDescription());
            //pst.setDate(3, (Date) c.getDate_deb());
            //pst.setDate(4, (Date) c.getDate_fin());
            //pst.setDate(5, (Date) c.getTime());
            pst.setInt(3, c.getNbr_max());
            pst.setString(4, c.getAdresse());

            pst.executeUpdate();
            System.out.println("Evenement modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }
    }

    public List<Commentaire> getAllCommentsForEvents(int id) throws SQLException {

        List<Commentaire> commentaire = new ArrayList<>();
        //String req = "select * from Comment where articleId="+articleId;
        String req = "SELECT * FROM comments  WHERE evenement_id=" + id;

        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire(
                    rst.getDate("date"),
                    rst.getString("content"));
            commentaire.add(c);
        }
        return commentaire;
    }

    public List<Users> getAllParticipantForEvents(int id) throws SQLException {

        List<Users> user = new ArrayList<>();
        //String req = "select * from Comment where articleId="+articleId;
        String req = "SELECT * FROM participant  WHERE event_id=" + id;

        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            int user_id = rst.getInt("user_id");
            String req1 = "SELECT * FROM users  WHERE " + user_id;

            Statement stm1 = cnx.createStatement();
            ResultSet rst1 = stm1.executeQuery(req1);
            while (rst1.next()) {
                Users u = new Users(
                        rst1.getString("nom"),
                        rst1.getString("prenom"),
                        rst1.getString("email"),
                        rst1.getString("adresse"),
                        rst1.getString("tel")
                );
                System.out.println("ckjaja");
                user.add(u);
            }

        }
        return user;
    }

    public ObservableList<Evenement> findEventBytitre(String titre) {
        ObservableList<Evenement> listevenement = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(
                    "select * from evenement WHERE titre LIKE ? ");
            preparedStatement.setString(1, titre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listevenement.add(new Evenement(
                        resultSet.getInt("id"),
                        resultSet.getInt("Nbr_max"),
                        resultSet.getString("Titre"),
                        resultSet.getString("description"),
                        resultSet.getString("Adresse"),
                        resultSet.getDate("Date_deb"),
                        resultSet.getDate("Date_fin"),
                        Integer.toString(resultSet.getInt("category_id")),
                        resultSet.getString("image"),
                        resultSet.getString("time")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur recherche publication : " + e.getMessage());
        }
        return listevenement;
    }

    public List<Evenement> ShowEvenement() throws SQLException {
        List<Evenement> evenement = new ArrayList<>();
        String sql = "SELECT * FROM `evenement`";
        Statement ste;

        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Evenement c = new Evenement();
                c.setId(rs.getInt("id"));
                c.setTitre(rs.getString("Titre"));
                c.setDescription(rs.getString("description"));
                c.setNbr_max(rs.getInt("Nbr_max"));
                c.setAdresse(rs.getString("Adresse"));
                c.setDate_deb(rs.getDate("Date_deb"));
                c.setDate_fin(rs.getDate("Date_fin"));
                c.setTime(rs.getString("time"));
                c.setCategorie(Integer.toString(rs.getInt("category_id")));
                c.setImage(rs.getString("image"));
//                 int cate =rs.getInt("categorie");
//System.out.println(rs.getInt("category_id")+"ghv");
//                 String sql1="select * from categorie WHERE id="+rs.getInt("category_id");
//Statement ste1;
//                ste1 = cnx.createStatement();
//                 ResultSet rs1 = ste.executeQuery(sql1);
//  while(rs1.next())
//               { c.setCategorie(rs1.getString("type"));
//
//                }
                evenement.add(c);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return evenement;
    }

    public void ParticiperEvent(Evenement c) throws SQLException {

        String req = "insert into participant (user_id, event_id) values (?,?)";
        try {

            pst = cnx.prepareStatement(req);

            pst.setInt(1, 25);
            pst.setInt(2, c.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
