/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.User;
import edu.esprit.tools.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Oussema
 */
public class Add_userController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
      Connection cnx;   
  public Add_userController() {
        cnx = Connexion.getInstance().getCnx();
    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void  afficherUser(ActionEvent event){
         try
         {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("add_user.fxml"));
              Parent root=loader.load();
              Add_userController auc=loader.getController();
         }catch(IOException ex)
         {
             System.out.println(ex.getMessage());
         }
        
      /*  List<User> Users = new ArrayList<>();
        String sql ="select id,nom,prenom,etat from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                User p = new User();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                
            
                p.setEtat(rs.getString("etat"));
                Users.add(p);
                
            }
            for (int i = 0; i < Users.size(); i++) {
           
            System.out.println(Users.get(i));
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       */
        
        
    }
   
    
}
