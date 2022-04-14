/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.forum.entities.Questions;
import edu.forum.entities.Reponses;
import edu.forum.tools.Connexion;
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
public class QuestionService {
     Connection connexion;   
  public QuestionService() {
        connexion = Connexion.getInstance().getCnx();
    }
 
 
    public void modifierQuestion(Questions e) throws SQLException, NoSuchAlgorithmException {
      
        
        String req = "UPDATE questions SET "
                + " titre='"+e.getTitre()+"'"
                + ", date_post='"+ (java.sql.Date) (Date) e.getDate_post()+"'"
                + ", message='"+e.getMessage()+"'"
              + ", solution='"+e.getSolution()+"'"
                + ", user_id='"+e.getUser_id()+"'"
              
                
                + ", nbr_vu='"+e.getNbr_vu()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
  
   
  
          public void ajouterQuestions(Questions e) throws SQLException {
        String req = "INSERT INTO `questions` (`titre`,`date_post`,`message`,`solution`,`user_id`,`nbr_vu`) "
                + "VALUES (?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getTitre());
        ps.setDate(2,(java.sql.Date) (Date) e.getDate_post());
       
        ps.setString(3, e.getMessage());
           ps.setString(4, e.getSolution());
            ps.setInt(5, e.getUser_id());
             ps.setInt(6, e.getNbr_vu());
          

        ps.executeUpdate();
    }
      
     
        
        
 

     

  
 
     public List<Questions> AfficherAllQuestion() throws SQLException {

        List<Questions> Questionss = new ArrayList<>();
        String req = "select * from questions order by date_post ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Questions e = new Questions(rst.getInt("id")
                    , rst.getString("titre")
                    , rst.getDate("date_post")
                    , rst.getString("message")
                    , rst.getString("solution")
                    , rst.getInt("user_id")
                    , rst.getInt("nbr_vu"));
            Questionss.add(e);
        }
        return Questionss;
    }
     
   
  
     public void SupprimerQuestion(Questions e) throws SQLException {

        String req = "DELETE FROM questions WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
    public List<Reponses> getAllReponsesForQuestions(int id) throws SQLException {

        List<Reponses> reponses = new ArrayList<>();
        //String req = "select * from Comment where articleId="+articleId;
        String req = "SELECT * FROM reponses  WHERE question_id="+id ;
 
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reponses c = new Reponses(rst.getInt("id"),
                   
                    rst.getString("message_r"),
                   rst.getDate("date_r"),
                   rst.getInt("question_id") ,
            rst.getInt("user_id"));
            reponses.add(c);
        }
        return reponses;
    }
 

}
