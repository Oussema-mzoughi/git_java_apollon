/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Evenement;
import edu.esprit.services.CommentaireServices;
import edu.esprit.services.EvenementServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AffichageEventsController implements Initializable {

    @FXML
    private Hyperlink Reponsess;
    @FXML
    private TextField txrecherche;
    @FXML
    private VBox pubvbox;
  public static String numTelephone;
 public static Evenement eventActuelle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    EvenementServices pss = new EvenementServices();
 try {
   List<Evenement> listEvenement = pss.ShowEvenement();
  for (int i = 0; i < listEvenement.size(); i++) {
            pubvbox.getChildren().add(creepub(listEvenement.get(i)));
        }
 } catch (SQLException ex) {        }
      
     
    }    

 public Parent creepub(Evenement evenement) {
        Parent modelePub;
CommentaireServices CommentaireServices = new CommentaireServices();
EvenementServices evenementServices =new EvenementServices();
  
        try {
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            modelePub = FXMLLoader.load(getClass().getResource("/edu/esprit/gui/ModelEvent.fxml"));
            ((Text) ((AnchorPane) modelePub).getChildren().get(0)).setText(evenement.getTitre());
            ((Text) ((AnchorPane) modelePub).getChildren().get(1)).setText(evenement.getDescription());
            ((Text) ((AnchorPane) modelePub).getChildren().get(7)).setText(dateFormat.format(evenement.getDate_deb()));
            ((Text) ((AnchorPane) modelePub).getChildren().get(8)).setText(evenement.getAdresse());
            ((Text) ((AnchorPane) modelePub).getChildren().get(9)).setText(dateFormat.format(evenement.getDate_fin()));
            ((Text) ((AnchorPane) modelePub).getChildren().get(13)).setText(Integer.toString(evenement.getNbr_max()));

            ((Button) ((AnchorPane) modelePub).getChildren().get(5)).setOnAction((event) -> {
                String textComm = ((TextField) ((AnchorPane) modelePub).getChildren().get(4)).getText();
                Date date = new Date(System.currentTimeMillis());
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
                java.sql.Date dateCommentaire = sqlDate2;
                Commentaire c = new Commentaire(evenement.getId(),dateCommentaire, textComm);

                 CommentaireServices.ajouterCommentaire(c);
                VBox commentaireContainer = ((VBox) ((AnchorPane) modelePub).getChildren().get(3));
                if (!commentaireContainer.getChildren().contains(commentaireContainer)) {
                    commentaireContainer.getChildren().add(creerCommentaire(c, commentaireContainer));
                }
                // SMS   
//                numTelephone = "+21628895666";
//                sms s = new sms();
//                s.send("Un commentaire a ètè ajouté", numTelephone);

            });

                ((Button) ((AnchorPane) modelePub).getChildren().get(2)).setOnAction((event) -> {
             try {
                 evenementServices.ParticiperEvent(evenement);
                 evenementServices.udpatePerticiper(evenement.getId(),evenement.getNbr_max()-1);
                ((Text) ((AnchorPane) modelePub).getChildren().get(13)).setText(Integer.toString(evenement.getNbr_max()));
                JOptionPane.showMessageDialog(null, "Vouz avez Participer à l'evenement ' "+evenement.getTitre()+" '");
            } catch (SQLException ex) {
                     Logger.getLogger(AffichageEventsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            });

            List<Commentaire> listCommentaire = CommentaireServices.getAllCommentairesByEvent(evenement.getId());
            if (!listCommentaire.isEmpty()) {

                for (int i = 0; i < listCommentaire.size(); i++) {
                    Commentaire commentaire = listCommentaire.get(i);

                    VBox commentaireContainer = ((VBox) ((AnchorPane) modelePub).getChildren().get(3));
                    commentaireContainer.getChildren().add(creerCommentaire(commentaire, commentaireContainer));
                }
            }

            return modelePub;

        } catch (IOException ex) {
            Logger.getLogger(AffichageEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

private Parent creerCommentaire(Commentaire commentaire, VBox commentaireContainer) {
CommentaireServices CommentaireServices = new CommentaireServices();
        try {
            Parent modeleCommentaire = FXMLLoader.load(
                    getClass().getResource("/edu/esprit/gui/ModeleCommentaire.fxml")
            );

            HBox hboxContenuCommentaire = (HBox) ((StackPane) ((AnchorPane) modeleCommentaire).getChildren().get(0)).getChildren().get(0);

            //Strin nomDestinataire = UserCrud.getInstance().g
            ((Text) hboxContenuCommentaire.getChildren().get(0)).setText(commentaire.getContent());

            Text storedDescription = ((Text) hboxContenuCommentaire.getChildren().get(0));
            ((Button) hboxContenuCommentaire.getChildren().get(1)).setOnAction(event -> {
                System.out.println("click mod");

                HBox hBox = new HBox();

                TextField textField = new TextField(storedDescription.getText());
                Button buttonConfirmer = new Button();
                buttonConfirmer.setText("Confirmer la modif");
                buttonConfirmer.setOnAction(value -> {
                    commentaire.setContent(textField.getText());
                    CommentaireServices.UpdateCommentaire(commentaire);

                    commentaireContainer.getChildren().removeAll();
                    storedDescription.setText(textField.getText());
                    commentaireContainer.getChildren().remove(hBox);
                });

                hBox.getChildren().addAll(textField, buttonConfirmer);

                commentaireContainer.getChildren().remove(storedDescription);
                commentaireContainer.getChildren().add(hBox);
            });
            ((Button) hboxContenuCommentaire.getChildren().get(2)).setOnAction(event -> {
                System.out.println("click supp");
                System.out.println(commentaire.getId());
                commentaireContainer.getChildren().removeAll(modeleCommentaire);
                CommentaireServices.supprimerCommentaire(commentaire.getId());
                JOptionPane.showMessageDialog(null, "Commentaire supprimer");

            });
            return modeleCommentaire;
        } catch (IOException ex) {
            Logger.getLogger(AffichageEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   @FXML
    private void ajouter(ActionEvent event) {
//        MainWindowController.chargerInterface(
//                getClass().getResource("/app/gui/front_end/candidat/publication/Ajout.fxml")
//        );
    }

 private void Modifier(ActionEvent event, Evenement evenement) {

//        eventActuelle = evenement;
//        MainWindowController.chargerInterface(
//                getClass().getResource("/app/gui/front_end/candidat/publication/ModifierPublication.fxml")
//        );
    }

private void Supprimer(ActionEvent event, Evenement evenement) throws SQLDataException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Suppression");
//        alert.setHeaderText(null);
//        alert.setContentText("vous voulez vraiment supprimer cette publication ?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            PublicationCrud.getInstance().SupprimerPublication(publication);
//            pubActuelle = null;
//            MainWindowController.chargerInterface(
//                    getClass().getResource("/app/gui/front_end/candidat/publication/Accueil.fxml")
//            );
//        }
    }

    @FXML
    private void recherche(KeyEvent event) {
EvenementServices pss = new EvenementServices();
        System.out.println("test 1");
        pubvbox.getChildren().clear();
        System.out.println("test 2");
        List<Evenement> listpublication = pss.findEventBytitre(txrecherche.getText());
        System.out.println("test 3");
        if (!listpublication.isEmpty()) {
            for (int i = 0; i < listpublication.size(); i++) {
                pubvbox.getChildren().add(creepub(listpublication.get(i)));
            }
            System.out.println("test 4");
        }
    }

 @FXML
    private void Reponsess(ActionEvent event) throws IOException {
                            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    } 
    
}
