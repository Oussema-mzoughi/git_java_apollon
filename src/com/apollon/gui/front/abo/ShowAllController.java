package com.apollon.gui.front.abo;

import com.apollon.entities.Abo;
import com.apollon.gui.front.MainWindowController;
import com.apollon.services.AboService;
import com.apollon.utils.AlertUtils;
import com.apollon.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShowAllController implements Initializable {

    public static Abo currentAbo;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Abo> listAbo = AboService.getInstance().getAll();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Abo abo : listAbo) {
                mainVBox.getChildren().add(makeAboModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeAboModel(
            Abo abo
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_ABO)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + abo.getCreatedAt());
            ((Text) innerContainer.lookup("#userIdText")).setText("UserId : " + abo.getUser().getName());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("SdpId : " + abo.getSdp().getName());
            ((Text) innerContainer.lookup("#dureeText")).setText("Duree : " + abo.getDuree());
            ((Text) innerContainer.lookup("#etatText")).setText("Etat : " + abo.getEtat());

            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(abo));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAbo(abo));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterAbo(ActionEvent event) {
        currentAbo = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_ABO);
    }

    private void modifierAbo(Abo abo) {
        currentAbo = abo;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_ABO);
    }

    private void supprimerAbo(Abo abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer abo ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (AboService.getInstance().delete(abo.getId())) {
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_ABO);
            } else {
                AlertUtils.makeError("Could not delete abo");
            }
        }
    }
}
