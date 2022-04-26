package edu.esprit.gui.back.abo;

import edu.esprit.entities.Abo;
import edu.esprit.gui.back.MainWindowController;
import edu.esprit.services.AboService;
import edu.esprit.utils.AlertUtils;
import edu.esprit.utils.Constants;
import edu.esprit.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> userIdCB;
    @FXML
    public ComboBox<RelationObject> sdpIdCB;
    @FXML
    public TextField dureeTF;
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
                userIdCB.setValue(currentAbo.getUser());
                sdpIdCB.setValue(currentAbo.getSdp());
                dureeTF.setText(currentAbo.getDuree());
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
                    LocalDate.now(),
                    userIdCB.getValue(),
                    sdpIdCB.getValue(),
                    dureeTF.getText(),
                    "1"
            );

            if (currentAbo == null) {
                if (AboService.getInstance().add(abo)) {
                    AlertUtils.makeSuccessNotification("Abo ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_ABO);
                } else {
                    AlertUtils.makeError("Abonnement existe deja");
                }
            } else {
                abo.setCreatedAt(currentAbo.getCreatedAt());
                abo.setId(currentAbo.getId());
                if (AboService.getInstance().edit(abo)) {
                    AlertUtils.makeSuccessNotification("Abo modifié avec succés");
                    ShowAllController.currentAbo = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_ABO);
                } else {
                    AlertUtils.makeError("Abonnement existe deja");
                }
            }
        }
    }

    private boolean controleDeSaisie() {

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

        return true;
    }
}
