/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AfficherSalledetailController implements Initializable {

    @FXML
    private Label NomSalle;
    @FXML
    private Label ville;
    @FXML
    private Label adress;
    @FXML
    private Label email;
    @FXML
    private Label tel;
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


        ville.setText(AjouterSalleController.connectedSalle.getVille_salle());
        NomSalle.setText(AjouterSalleController.connectedSalle.getNom_salle());

        adress.setText(AjouterSalleController.connectedSalle.getAdresse_salle());

        email.setText(AjouterSalleController.connectedSalle.getEmail());

        tel.setText(AjouterSalleController.connectedSalle.getNum_tel());
        imgpathttt.setText(AjouterSalleController.connectedSalle.getImage());


        if (imgpathttt.getText() == null) {
            throw new NullPointerException("name");
        }

        try {
            Image image = new Image(getClass().getResourceAsStream("/images/test.jpg"));

            imgajoutincours.setImage(image);
        } catch (Exception e) {

        }

    }


}
