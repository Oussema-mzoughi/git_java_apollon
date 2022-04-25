/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherProduitDetailController implements Initializable {

   @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Label stock;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
 private File file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
            nom.setText(AjoutProduitController.connectedProduit.getNom());
            prix.setText(String.valueOf(AjoutProduitController.connectedProduit.getPrix()));
            
            description.setText(AjoutProduitController.connectedProduit.getDescription());
            
            stock.setText(String.valueOf(AjoutProduitController.connectedProduit.getStock()));
            
            imgpathttt.setText(AjoutProduitController.connectedProduit.getImage());
         
            
           if (imgpathttt.getText() == null) {
        throw new NullPointerException("name");
    }  
           
           try {
        Image image = new Image(getClass().getResourceAsStream("/images/test.jpg"));
    
        imgajoutincours.setImage(image);
          }

       
     catch (Exception e) {
       
    }
      
    }
    
}
