/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherTousEvenementsController implements Initializable {

    @FXML
    private AnchorPane affichage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/edu/esprit/gui/AffichageEvents.fxml"));
            // hethom ikabrou lpage 3la 9ad l'affichage ---
            AnchorPane.setTopAnchor(parent, 0.0);
            AnchorPane.setBottomAnchor(parent, 0.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
            AnchorPane.setRightAnchor(parent, 0.0);
            // --------------------------------------------
            affichage.getChildren().clear();
            affichage.getChildren().add(parent);
        } catch (IOException ex) {
            Logger.getLogger(AfficherTousEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
