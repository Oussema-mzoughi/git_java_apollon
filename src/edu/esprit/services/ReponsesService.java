/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Questions;
import edu.esprit.entities.Reponses;
import edu.esprit.entities.Salle;
import edu.esprit.tools.Connexion;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class ReponsesService {
    Connection connexion;   
  public ReponsesService() {
        connexion = Connexion.getInstance().getCnx();
    }
 
 
  
   

    public void modifierReponses(Reponses e) throws SQLException, NoSuchAlgorithmException {
      
        
        String req = "UPDATE reponses SET "
                + " message_r='"+e.getMessage_r()+"'"
                + ", date_r='"+ (java.sql.Date) (Date) e.getDate_r()+"'"
                + ", question_id='"+e.getQuestion_id()+"'"
            
              
                
                + ", user_id='"+e.getUser_id()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
          public void ajouterReponse(Reponses e) throws SQLException {
        String req = "INSERT INTO `reponses` (`message_r`,`date_r`,`question_id`,`user_id`) "
                + "VALUES (?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getMessage_r());
        ps.setDate(2,(java.sql.Date) (Date) e.getDate_r());
       
        ps.setInt(3, e.getQuestion_id());
           ps.setInt(4, e.getUser_id());
         
          

        ps.executeUpdate();
    } 
   
     public List<Reponses> AfficherAllReponses() throws SQLException {

        List<Reponses> Reponsess = new ArrayList<>();
        String req = "select * from reponses order by question_id ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Reponses e = new Reponses(rst.getInt("id")
                    , rst.getString("message_r")
                    , rst.getDate("date_r")
                    , rst.getInt("question_id")
                 
                    , rst.getInt("user_id")
                    );
            Reponsess.add(e);
        }
        return Reponsess;
    }
    
     
       public void SupprimerReponses(Reponses e) throws SQLException {

        String req = "DELETE FROM reponses WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    
       public void supp2(Reponses m) throws SQLException {

        String req = "DELETE FROM reponses WHERE id ="+m.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
    
    
    
}
