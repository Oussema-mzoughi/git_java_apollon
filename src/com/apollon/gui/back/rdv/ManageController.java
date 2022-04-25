package com.apollon.gui.back.rdv;

import com.apollon.entities.Rdv;
import com.apollon.gui.back.MainWindowController;
import com.apollon.services.RdvService;
import com.apollon.utils.AlertUtils;
import com.apollon.utils.Constants;
import com.apollon.utils.Mailer;
import com.apollon.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> userIdCB;
    @FXML
    public ComboBox<RelationObject> partenaireCB;
    @FXML
    public DatePicker debutDP;
    @FXML
    public DatePicker finDP;
    @FXML
    public TextField debutHeureTF;
    @FXML
    public TextField debutMinuteTF;
    @FXML
    public TextField finMinuteTF;
    @FXML
    public TextField finHeureTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Rdv currentRdv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentRdv = ShowAllController.currentRdv;

        for (RelationObject user : RdvService.getInstance().getAllUsers()) {
            userIdCB.getItems().add(user);
        }

        for (RelationObject partenaire : RdvService.getInstance().getAllUsers()) {
            partenaireCB.getItems().add(partenaire);
        }

        if (currentRdv != null) {
            topText.setText("Modifier rdv");
            btnAjout.setText("Modifier");

            try {
                userIdCB.setValue(currentRdv.getUser());
                partenaireCB.setValue(currentRdv.getPartenaire());
                debutDP.setValue(currentRdv.getDebut().toLocalDate());
                debutMinuteTF.setText(String.valueOf(currentRdv.getDebut().getMinute()));
                debutHeureTF.setText(String.valueOf(currentRdv.getDebut().getHour()));
                finDP.setValue(currentRdv.getFin().toLocalDate());
                finMinuteTF.setText(String.valueOf(currentRdv.getFin().getMinute()));
                finHeureTF.setText(String.valueOf(currentRdv.getFin().getHour()));

            } catch (NullPointerException ignored) {
                System.out.println("Champ null");
            }
        } else {
            topText.setText("Ajouter rdv");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) throws Exception {

        if (controleDeSaisie()) {
            Rdv rdv = new Rdv(
                    userIdCB.getValue(),
                    partenaireCB.getValue(),
                    LocalDateTime.of(
                            finDP.getValue(),
                            LocalTime.of(
                                    Integer.parseInt(finHeureTF.getText()),
                                    Integer.parseInt(finMinuteTF.getText())
                            )
                    ),
                    LocalDateTime.of(
                            debutDP.getValue(),
                            LocalTime.of(
                                    Integer.parseInt(debutHeureTF.getText()),
                                    Integer.parseInt(debutMinuteTF.getText())
                            )
                    ),
                    1,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            if (currentRdv == null) {
                if (RdvService.getInstance().add(rdv)) {
                    AlertUtils.makeInformation("Rdv ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_RDV);
                    Mailer.sendMail(
                            rdv.getPartenaire().getName(),
                            "",
                            "<h1>Notification</h1> <br/> <h2><b>Un nouveau rendez vous a été ajouté</b></h2>"
                    );
                } else {
                    AlertUtils.makeError("Rendez vous existe deja");
                }
            } else {
                rdv.setCreatedAt(currentRdv.getCreatedAt());
                rdv.setId(currentRdv.getId());
                if (RdvService.getInstance().edit(rdv)) {
                    AlertUtils.makeInformation("Rdv modifié avec succés");
                    ShowAllController.currentRdv = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_RDV);
                } else {
                    AlertUtils.makeError("Rendez vous existe deja");
                }
            }
        }
    }

    @FXML
    public void afficherCalendrier(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_CALENDAR)));

            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.setTitle("Calendar");
            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {
            System.out.println("Could not load FXML : " + e.getMessage() + " check your controller");
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {

        if (userIdCB.getValue() == null) {
            AlertUtils.makeInformation("user ne doit pas etre vide");
            return false;
        }

        if (partenaireCB.getValue() == null) {
            AlertUtils.makeInformation("partenaire ne doit pas etre vide");
            return false;
        }

        if (partenaireCB.getValue().getId() == userIdCB.getValue().getId()) {
            AlertUtils.makeInformation("partenaire doit etre different a user");
            return false;
        }

        if (finDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour fin");
            return false;
        }
        if (debutDP.getValue() == null) {
            AlertUtils.makeInformation("Choisir une date pour debut");
            return false;
        }

        if (finHeureTF.getText().isEmpty()) {
            AlertUtils.makeInformation("fin heure ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(finHeureTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("fin heure doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(finHeureTF.getText()) > 12) {
            AlertUtils.makeInformation("finHeure ne doit pas etre superieure a 12");
            return false;
        }

        if (finMinuteTF.getText().isEmpty()) {
            AlertUtils.makeInformation("finMinute ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(finMinuteTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("finMinute doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(finMinuteTF.getText()) > 59) {
            AlertUtils.makeInformation("finMinute ne doit pas etre superieure a 59");
            return false;
        }

        if (debutHeureTF.getText().isEmpty()) {
            AlertUtils.makeInformation("debutHeure ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(debutHeureTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("debutHeure doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(debutHeureTF.getText()) > 12) {
            AlertUtils.makeInformation("debutMinute ne doit pas etre superieur a 12");
            return false;
        }

        if (debutMinuteTF.getText().isEmpty()) {
            AlertUtils.makeInformation("debutMinute ne doit pas etre vide");
            return false;
        }
        try {
            Integer.parseInt(debutMinuteTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("debutMinute doit etre un nombre");
            return false;
        }
        if (Integer.parseInt(debutMinuteTF.getText()) > 59) {
            AlertUtils.makeInformation("debutMinute ne doit pas etre superieur a 59");
            return false;
        }

        return true;
    }
}
