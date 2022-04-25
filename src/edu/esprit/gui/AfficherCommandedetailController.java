/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AfficherCommandedetailController implements Initializable {

    @FXML
    private Label numero;
    @FXML
    private Label date_commande;
    @FXML
    private Label date_livraison;
    @FXML
    private Label livreur;
    @FXML
    private Label totale;
 private File file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
            numero.setText(String.valueOf(AjouterCommandeController.connectedCommande.getNumero()));
            date_commande.setText(String.valueOf(AjouterCommandeController.connectedCommande.getDate_commande()));
            
            date_livraison.setText(String.valueOf(AjouterCommandeController.connectedCommande.getDate_livraison()));
            
            livreur.setText(String.valueOf(AjouterCommandeController.connectedCommande.getLivreur()));
            
            totale.setText(String.valueOf(AjouterCommandeController.connectedCommande.getTotale()));
         
            
      
    }

    

    
    

    
}
