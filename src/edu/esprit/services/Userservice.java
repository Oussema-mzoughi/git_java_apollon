/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Salle;
import edu.esprit.entities.User;
import edu.esprit.tools.Connexion;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oussema
 */
public class Userservice {
    
         Connection cnx;
         PreparedStatement ste;
  public Userservice() {
        cnx = Connexion.getInstance().getCnx();
    } 
  
  
    public  boolean adduser(User user1) throws IOException
    {
       
        // Enter data using BufferReader
       String query ="INSERT INTO `users`(`nom`, `prenom`, `email`, `tel`, `adresse`, `ville`, `cp`, `login`, `mdp`, `img`, `role_id`, `etat`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      
          try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, user1.getNom());
            ste.setString(2, user1.getPrenom());
            ste.setString(3, user1.getEmail());
            ste.setString(4, user1.getTel());
            ste.setString(5, user1.getAdresse());
            ste.setString(6, user1.getVille());
            ste.setString(7, user1.getCp());
            ste.setString(8, user1.getLogin());
            ste.setString(9, user1.getPassword());
            ste.setString(10, user1.getImage());
            ste.setString(11, user1.getRole_id());
            ste.setString(12, user1.getEtat());
             
           
            ste.executeUpdate();
           
            ste.executeUpdate();
            return true;
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            return false;
           
        }
    }
      
  
    
       public List<User>  afficherUser(){
           
        List<User> Users = new ArrayList<>();
        String sql ="select u.id,nom,prenom,email,role_id,etat,tel,adresse,cp,login,ville,img,r.name from users u,role r where u.role_id=r.id order by u.id";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                User p = new User();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                 p.setEmail(rs.getString("email"));
                 p.setTel(rs.getString("tel"));
                 p.setAdresse(rs.getString("adresse"));
                 p.setVille(rs.getString("ville"));
                 p.setCp(rs.getString("cp"));
                 p.setLogin(rs.getString("login"));
                 p.setImg(rs.getString("img"));
                 if(rs.getString("etat").equals("1"))
                     p.setEtat("Activé");
                     else
                       p.setEtat("Désactivé");
                 
                
            
                p.setRole_id(rs.getString("name"));
                Users.add(p);
                
            }
           return Users;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return Users;
            
        }
       
         
 
        
    }
    
        public void DeleteUser(User u) throws SQLException {

        String req = "DELETE FROM users WHERE id ="+u.getId()+"";
      
     
         PreparedStatement ps = cnx.prepareStatement(req);
        ps.executeUpdate();
    }
    
    
        
        
         public boolean Updateuser(User e) throws SQLException, NoSuchAlgorithmException {
       
        String req = "UPDATE users SET "
              
                + " nom='"+e.getNom()+"'"
                + ", prenom='"+e.getPrenom()+"'"
              + ", email='"+e.getEmail()+"'"
                + ", adresse='"+e.getAdresse()+"'"
                + ", ville='"+e.getVille()+"'"
                + ", cp='"+e.getCp()+"'"
                + ", tel='"+e.getTel()+"'"
                 + ", login='"+e.getLogin()+"'"
                 + ", role_id='"+e.getRole_id()+"'"
                + ", img='"+e.getImage()+"' where id  = '"+e.getId()+"'";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        return true;
    } 
         
         
   public void  deco(){
                    Connexion.user_id=0;
                    Connexion.user_nom=null;
                    Connexion.user_prenom=null;
                    Connexion.user_role=0;
             
   }
  
   public int  login(String login,String mdp){
           
        List<User> Users = new ArrayList<>();
        String sql ="select id,nom,prenom,email,role_id,etat from users where login='"+login+"' and mdp='"+mdp+"'";
        Statement ste;
        int val =0;
     
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           if (rs != null) 
            {
                 while(rs.next()){
                    val = rs.getInt("etat");
                    Connexion.user_id=rs.getInt("id");
                    Connexion.user_nom=rs.getString("nom");
                    Connexion.user_prenom=rs.getString("prenom");
                    Connexion.user_role=rs.getInt("role_id");
                    
                   
       
                 }
            }else
               val = 0;
          
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            val = 0;
            
        }
       
         
        return val;
        
    }
        
        
}
