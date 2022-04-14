package com.apollon.gui.front.rdv;

import com.apollon.entities.Rdv;
import com.apollon.gui.front.MainWindowController;
import com.apollon.services.RdvService;
import com.apollon.utils.AlertUtils;
import com.apollon.utils.Constants;
import com.apollon.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> userIdCB;
    @FXML
    public TextField partenaireIdTF;
    @FXML
    public DatePicker debutDP;
    @FXML
    public DatePicker finDP;
    @FXML
    public TextField etatTF;
    @FXML
    public DatePicker createdAtDP;
    @FXML
    public DatePicker updatedAtDP;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;
    @FXML
    public TextField updatedAtHeureTF;
    @FXML
    public TextField updatedAtMinuteTF;
    @FXML
    public TextField createdAtMinuteTF;
    @FXML
    public TextField createdAtHeureTF;

    Rdv currentRdv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentRdv = ShowAllController.currentRdv;

        for (RelationObject user : RdvService.getInstance().getAllUsers()) {
            userIdCB.getItems().add(user);
        }

        if (currentRdv != null) {
            topText.setText("Modifier rdv");
            btnAjout.setText("Modifier");

            try {
                userIdCB.setValue(currentRdv.getUser());
                partenaireIdTF.setText(String.valueOf(currentRdv.getPartenaireId()));
                debutDP.setValue(currentRdv.getDebut());
                finDP.setValue(currentRdv.getFin());
                etatTF.setText(String.valueOf(currentRdv.getEtat()));
                createdAtDP.setValue(currentRdv.getCreatedAt().toLocalDate());
                updatedAtDP.setValue(currentRdv.getUpdatedAt().toLocalDate());
                createdAtMinuteTF.setText(String.valueOf(currentRdv.getCreatedAt().getMinute()));
                createdAtHeureTF.setText(String.valueOf(currentRdv.getCreatedAt().getHour()));
                updatedAtMinuteTF.setText(String.valueOf(currentRdv.getUpdatedAt().getMinute()));
                updatedAtHeureTF.setText(String.valueOf(currentRdv.getUpdatedAt().getHour()));

            } catch (NullPointerException ignored) {
                System.out.println("Champ null");
            }
        } else {
            topText.setText("Ajouter rdv");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            Rdv rdv = new Rdv(
                    userIdCB.getValue(),
                    Integer.parseInt(partenaireIdTF.getText()),
                    debutDP.getValue(),
                    finDP.getValue(),
                    Integer.parseInt(etatTF.getText()),
                    LocalDateTime.of(
                            createdAtDP.getValue(),
                            LocalTime.of(
                                    Integer.parseInt(createdAtHeureTF.getText()),
                                    Integer.parseInt(createdAtMinuteTF.getText())
                            )
                    ),
                    LocalDateTime.of(
                            updatedAtDP.getValue(),
                            LocalTime.of(
                                    Integer.parseInt(updatedAtHeureTF.getText()),
                                    Integer.parseInt(updatedAtMinuteTF.getText())
                            )
                    )
            );

            if (currentRdv == null) {
                if (RdvService.getInstance().add(rdv)) {
                    AlertUtils.makeInformation("Rdv ajouté avec succés");
                } else {
                    AlertUtils.makeError("Could not add rdv");
                }
            } else {
                rdv.setId(currentRdv.getId());
                if (RdvService.getInstance().edit(rdv)) {
                    AlertUtils.makeInformation("Rdv modifié avec succés");
                    ShowAllController.currentRdv = null;
                } else {
                    AlertUtils.makeError("Could not edit rdv");
                }
            }
            MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_RDV);
        }
    }

    private boolean controleDeSaisie() {

        if (userIdCB.getValue() == null) {
            AlertUtils.makeInformation("user ne doit pas etre vide");
            return false;
        }

        if (partenaireIdTF.getText().isEmpty()) {
            AlertUtils.makeInformation("partenaireId ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(partenaireIdTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("partenaireId doit etre un nombre");
            return false;
        }
        if (debutDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour debut");
            return false;
        }
        if (finDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour fin");
            return false;
        }

        if (etatTF.getText().isEmpty()) {
            AlertUtils.makeInformation("etat ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(etatTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("etat doit etre un nombre");
            return false;
        }
        if (createdAtDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour createdAt");
            return false;
        }
        if (updatedAtDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour updatedAt");
            return false;
        }

        if (createdAtHeureTF.getText().isEmpty()) {
            AlertUtils.makeInformation("createdAt heure ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(createdAtHeureTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("createdAt heure doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(createdAtHeureTF.getText()) > 12) {
            AlertUtils.makeInformation("createdAtHeure ne doit pas etre superieure a 12");
            return false;
        }

        if (createdAtMinuteTF.getText().isEmpty()) {
            AlertUtils.makeInformation("createdAtMinute ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(createdAtMinuteTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("createdAtMinute doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(createdAtMinuteTF.getText()) > 59) {
            AlertUtils.makeInformation("createdAtMinute ne doit pas etre superieure a 59");
            return false;
        }

        if (updatedAtHeureTF.getText().isEmpty()) {
            AlertUtils.makeInformation("updatedAtHeure ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(updatedAtHeureTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("updatedAtHeure doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(updatedAtHeureTF.getText()) > 12) {
            AlertUtils.makeInformation("updatedAtMinute ne doit pas etre superieur a 12");
            return false;
        }

        if (updatedAtMinuteTF.getText().isEmpty()) {
            AlertUtils.makeInformation("updatedAtMinute ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(updatedAtMinuteTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("updatedAtMinute doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(updatedAtMinuteTF.getText()) > 59) {
            AlertUtils.makeInformation("updatedAtMinute ne doit pas etre superieur a 59");
            return false;
        }

        return true;
    }
}
