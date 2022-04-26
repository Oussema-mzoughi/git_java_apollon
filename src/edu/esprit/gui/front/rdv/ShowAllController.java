package edu.esprit.gui.front.rdv;

import edu.esprit.entities.Rdv;
import edu.esprit.gui.front.MainWindowController;
import edu.esprit.services.RdvService;
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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_RDV)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));

            ((Text) innerContainer.lookup("#userIdText")).setText("User : " + rdv.getUser().getName());
            ((Text) innerContainer.lookup("#partenaireText")).setText("Partenaire : " + rdv.getPartenaire().getName());
            ((Text) innerContainer.lookup("#debutText")).setText("Debut : " + rdv.getDebut());
            ((Text) innerContainer.lookup("#finText")).setText("Fin : " + rdv.getFin());
            ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + rdv.getCreatedAt());
            ((Text) innerContainer.lookup("#updatedAtText")).setText("UpdatedAt : " + rdv.getUpdatedAt());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    public void sort(ActionEvent actionEvent) {
        compareVar = sortCB.getValue();
        Collections.sort(listRdv);
        displayData();
    }
}
