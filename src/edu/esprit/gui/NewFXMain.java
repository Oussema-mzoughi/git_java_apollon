/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.apollon.utils.Constants;
import edu.esprit.entities.User;
import edu.esprit.services.Userservice;
import edu.esprit.tools.Connexion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
            Parent root =FXMLLoader.load(getClass().getResource("AffichageEvents.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Apollon Java Pidev GoldMiners 3A40");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       /* Connexion cx=Connexion.getInstance();
          try {
        User usr=new User("02020202","nomm","prenomm","oz.oussema@gmail.com","adressee","villee","7500","login","mdp","default.png","1");
        Userservice us=new Userservice();
        us.adduser(usr);
      } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
    
}
