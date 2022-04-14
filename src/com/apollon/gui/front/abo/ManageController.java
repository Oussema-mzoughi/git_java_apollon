package com.apollon.gui.front.abo;

import com.apollon.entities.Abo;
import com.apollon.gui.front.MainWindowController;
import com.apollon.services.AboService;
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
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public DatePicker createdAtDP;
    @FXML
    public ComboBox<RelationObject> userIdCB;
    @FXML
    public ComboBox<RelationObject> sdpIdCB;
    @FXML
    public TextField dureeTF;
    @FXML
    public TextField etatTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Abo currentAbo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo = ShowAllController.currentAbo;

        for (RelationObject user : AboService.getInstance().getAllUsers()) {
            userIdCB.getItems().add(user);
        }

        for (RelationObject sdp : AboService.getInstance().getAllSdp()) {
            sdpIdCB.getItems().add(sdp);
        }

        if (currentAbo != null) {
            topText.setText("Modifier abo");
            btnAjout.setText("Modifier");

            try {
                createdAtDP.setValue(currentAbo.getCreatedAt());
                userIdCB.setValue(currentAbo.getUser());
                sdpIdCB.setValue(currentAbo.getSdp());
                dureeTF.setText(currentAbo.getDuree());
                etatTF.setText(currentAbo.getEtat());

            } catch (NullPointerException ignored) {
                System.out.println("Champ null");
            }
        } else {
            topText.setText("Ajouter abo");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            Abo abo = new Abo(
                    createdAtDP.getValue(),
                    userIdCB.getValue(),
                    sdpIdCB.getValue(),
                    dureeTF.getText(),
                    etatTF.getText()
            );

            if (currentAbo == null) {
                if (AboService.getInstance().add(abo)) {
                    AlertUtils.makeInformation("Abo ajouté avec succés");
                } else {
                    AlertUtils.makeError("Could not add abo");
                }
            } else {
                abo.setId(currentAbo.getId());
                if (AboService.getInstance().edit(abo)) {
                    AlertUtils.makeInformation("Abo modifié avec succés");
                    ShowAllController.currentAbo = null;
                } else {
                    AlertUtils.makeError("Could not edit abo");
                }
            }
            MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_ABO);
        }
    }

    private boolean controleDeSaisie() {

        if (createdAtDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour createdAt");
            return false;
        }

        if (userIdCB.getValue() == null) {
            AlertUtils.makeInformation("userId ne doit pas etre vide");
            return false;
        }

        if (sdpIdCB.getValue() == null) {
            AlertUtils.makeInformation("sdpId ne doit pas etre vide");
            return false;
        }

        if (dureeTF.getText().isEmpty()) {
            AlertUtils.makeInformation("duree ne doit pas etre vide");
            return false;
        }

        if (etatTF.getText().isEmpty()) {
            AlertUtils.makeInformation("etat ne doit pas etre vide");
            return false;
        }

        return true;
    }
}
