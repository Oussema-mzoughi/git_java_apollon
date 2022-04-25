/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Users;
import edu.esprit.services.CommentaireServices;
import edu.esprit.services.EvenementServices;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherDetailsEventController implements Initializable {

@FXML
    private TextField inputRech;
    @FXML
    private TableView<Users> tableview;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> Prenom;
    @FXML
    private TableColumn<?, ?> Email;
    @FXML
    private TableColumn<?, ?> tel;
    @FXML
    private TableColumn<?, ?> Adresse;
  public ObservableList<Users> list;  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        titre.setVisible(true);
//  labelid.setText(Integer.toString(AfficherEvenementController.connectedEvenement.getId()));
//                 titre.setText(AfficherEvenementController.connectedEvenement.getTitre());
//            description.setText(AfficherEvenementController.connectedEvenement.getDescription());
//            adresse.setText(AfficherEvenementController.connectedEvenement.getAdresse());
//            String dateDebut = dateFormat.format(AfficherEvenementController.connectedEvenement.getDate_deb());
//            datedebut.setText(dateDebut);
//             String dateFin = dateFormat.format(AfficherEvenementController.connectedEvenement.getDate_fin());
//            datefin.setText(dateFin);
//            nbrmax.setText(Integer.toString(AfficherEvenementController.connectedEvenement.getNbr_max()));
//        System.out.println(labelid.getText());
//           EvenementServices pss = new EvenementServices();
//               ArrayList<Commentaire> c = new ArrayList<>();
//        try {
//            c = (ArrayList<Commentaire>) pss.getAllCommentsForEvents(Integer.parseInt(labelid.getText()));
//        } catch (SQLException ex) {
//        }
        EvenementServices pss = new EvenementServices();
        ArrayList<Users> c = new ArrayList<>();
        try {
            c = (ArrayList<Users>) pss.getAllParticipantForEvents(AfficherEvenementController.connectedEvenement.getId());
        } catch (SQLException ex) {
        }
        
        ObservableList<Users> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);      

        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
         Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    
            try {
            list = FXCollections.observableArrayList(
                    pss.getAllParticipantForEvents(AfficherEvenementController.connectedEvenement.getId())
            );        
        
        
   FilteredList<Users> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Users>) user -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (user.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Users> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }    


 @FXML
    private void affichEvent(ActionEvent event) throws IOException {
//                            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
//        Scene scene = new Scene(page1);
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
        
        
    } 
    
}
