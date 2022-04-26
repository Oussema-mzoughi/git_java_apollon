package edu.esprit.gui.front.abo;

import edu.esprit.entities.Abo;
import edu.esprit.gui.front.MainWindowController;
import edu.esprit.services.AboService;
import edu.esprit.utils.AlertUtils;
import edu.esprit.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    public VBox mainVBox;
    @FXML
    public TextField searchTF;

    List<Abo> listAbo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listAbo = AboService.getInstance().getAll();
        displayData("");
    }

    void displayData(String searchText) {
        mainVBox.getChildren().clear();

        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Abo abo : listAbo) {
                if (abo.getSdp().getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                    mainVBox.getChildren().add(makeAboModel(abo));
                }
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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_ABO)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + abo.getCreatedAt());
            ((Text) innerContainer.lookup("#userIdText")).setText("Utilisateur : " + abo.getUser().getName());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("Salle de sport : " + abo.getSdp().getName());
            ((Text) innerContainer.lookup("#dureeText")).setText("Duree : " + abo.getDuree());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void search(KeyEvent event) {
        displayData(searchTF.getText());
    }

}
