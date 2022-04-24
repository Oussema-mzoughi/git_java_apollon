/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.esprit.entities.User;
import edu.esprit.services.Userservice;
import edu.esprit.tools.Connexion;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussema
 */
public class LoginController implements Initializable {

    
        Connection cnx;
 @FXML
    private Button btn_login,btn_inscription;
 
  @FXML
    private TextField txt_login,txt_mdp;
          @FXML
  private Pane pnl_add_user,pnl_list_user,pnl_add_article,pnl_list_article,pnl_update_user;
  
  @FXML
    private Label login_error,login_disabled;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public LoginController() {
         cnx=Connexion.getInstance().getCnx();
    }
    @FXML
public void btn_inscription_action(ActionEvent event) throws SQLException, IOException {
    
     Connexion.user_inscription="1";
                        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        Parent root =FXMLLoader.load(getClass().getResource("home.fxml"));
            
            Scene scene = new Scene(root);
            
           
            primaryStage.setScene(scene);
            primaryStage.show();
           
       
}
    
       public void btn_login_action(ActionEvent event) throws SQLException, IOException {
            
            Userservice us=new Userservice();
            login_error.setVisible(false);
            login_disabled.setVisible(false);
            if(us.login(txt_login.getText(),txt_mdp.getText())==0)
                    {
                        login_error.setVisible(true);
                    }
            else if(us.login(txt_login.getText(),txt_mdp.getText())==2)
                    {
                        login_disabled.setVisible(true);
                    }
            if(us.login(txt_login.getText(),txt_mdp.getText())==1)
                    {
                        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        Parent root =FXMLLoader.load(getClass().getResource("home.fxml"));
            
            Scene scene = new Scene(root);
            
           
            primaryStage.setScene(scene);
            primaryStage.show();
                    }
            
        
        
    
    }
    
}
