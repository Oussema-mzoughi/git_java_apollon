package com.apollon.gui.back;

import com.apollon.utils.Animations;
import com.apollon.utils.Constants;
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

public class TopBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_BLUE = new Color(0.7, 0.7, 0.20, 1);
    private final Color COLOR_DARK = new Color(0.20, 0.23, 0.25, 1);
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

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_BLUE, 0, false);
        }
        btnAbos.setTextFill(Color.WHITE);
        btnRdvs.setTextFill(Color.WHITE);

    }

    @FXML
    private void afficherAbos(ActionEvent event) {
        goToLink(Constants.FXML_DISPLAY_ALL_ABO);

        btnAbos.setTextFill(COLOR_BLUE);
        Animations.animateButton(btnAbos, COLOR_GRAY, Color.WHITE, COLOR_BLUE, 0, false);
    }

    @FXML
    private void afficherRdvs(ActionEvent event) {
        goToLink(Constants.FXML_DISPLAY_ALL_RDV);

        btnRdvs.setTextFill(COLOR_BLUE);
        Animations.animateButton(btnRdvs, COLOR_GRAY, Color.WHITE, COLOR_BLUE, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_BLUE, 0, false);
        }
        MainWindowController.getInstance().loadInterface(link);
    }
}
