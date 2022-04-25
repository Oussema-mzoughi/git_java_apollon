package com.apollon.gui.back.rdv;

import com.apollon.entities.Rdv;
import com.apollon.gui.back.MainWindowController;
import com.apollon.services.RdvService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShowAllController implements Initializable {

    public static String compareVar;
    public static Rdv currentRdv;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;
    @FXML
    public ComboBox<String> sortCB;

    List<Rdv> listRdv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listRdv = RdvService.getInstance().getAll();

        sortCB.getItems().addAll("partenaire", "Date de debut", "Date de fin", "createdAt", "updatedAt");
        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listRdv);

        if (!listRdv.isEmpty()) {
            for (Rdv rdv : listRdv) {
                mainVBox.getChildren().add(makeRdvModel(rdv));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeRdvModel(
            Rdv rdv
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_RDV)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            System.out.println("ANOTHER");
            System.out.println(rdv.getUser().getId());
            System.out.println(rdv.getUser().getName());
            System.out.println("NEXT");
            System.out.println(rdv.getPartenaire().getId());
            System.out.println(rdv.getUser().getName());

            ((Text) innerContainer.lookup("#userIdText")).setText("User : " + rdv.getUser().getName());
            ((Text) innerContainer.lookup("#partenaireText")).setText("Partenaire : " + rdv.getPartenaire().getName());
            ((Text) innerContainer.lookup("#debutText")).setText("Debut : " + rdv.getDebut());
            ((Text) innerContainer.lookup("#finText")).setText("Fin : " + rdv.getFin());
            ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + rdv.getCreatedAt());
            ((Text) innerContainer.lookup("#updatedAtText")).setText("UpdatedAt : " + rdv.getUpdatedAt());

            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierRdv(rdv));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerRdv(rdv));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterRdv(ActionEvent event) {
        currentRdv = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_RDV);
    }

    private void modifierRdv(Rdv rdv) {
        currentRdv = rdv;
        MainWindowController.getInstance().loadInterface(Constants.FXML_MANAGE_RDV);
    }

    private void supprimerRdv(Rdv rdv) {
        currentRdv = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer rdv ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent()) {
            if (action.get() == ButtonType.OK) {
                if (RdvService.getInstance().delete(rdv.getId())) {
                    MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_RDV);
                } else {
                    AlertUtils.makeError("Could not delete rdv");
                }
            }
        }
    }

    @FXML
    public void sort(ActionEvent actionEvent) {
        compareVar = sortCB.getValue();
        Collections.sort(listRdv);
        displayData();
    }
}
