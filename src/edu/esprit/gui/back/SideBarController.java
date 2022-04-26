package edu.esprit.gui.back;

import edu.esprit.MainApp;
import edu.esprit.utils.Animations;
import edu.esprit.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = new Color(0.7, 0.7, 0.20, 1);
    private final Color COLOR_DARK = new Color(1, 1, 1, 0.65);
    private Button[] liens;

    @FXML
    private Button btnAbos;
    @FXML
    private Button btnRdvs;
    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
                btnAbos,
                btnRdvs,
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }
        btnAbos.setTextFill(Color.WHITE);
        btnRdvs.setTextFill(Color.WHITE);

    }

    @FXML
    private void afficherAbos(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_ABO);

        btnAbos.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnAbos, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherRdvs(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_RDV);

        btnRdvs.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnRdvs, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        MainWindowController.getInstance().loadInterface(link);
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        MainApp.getInstance().logout();
    }
}
