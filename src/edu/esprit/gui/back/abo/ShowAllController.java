package edu.esprit.gui.back.abo;

import edu.esprit.entities.Abo;
import edu.esprit.gui.back.MainWindowController;
import edu.esprit.services.AboService;
import edu.esprit.utils.AlertUtils;
import edu.esprit.utils.Constants;
import edu.esprit.utils.RelationObject;
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
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class ShowAllController implements Initializable {

    public static Abo currentAbo;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_ABO)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#createdAtText")).setText("CreatedAt : " + abo.getCreatedAt());
            ((Text) innerContainer.lookup("#userIdText")).setText("Utilisateur : " + abo.getUser().getName());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("Salle de sport : " + abo.getSdp().getName());
            ((Text) innerContainer.lookup("#dureeText")).setText("Duree : " + abo.getDuree());

            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(abo));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAbo(abo));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void search(KeyEvent event) {
        displayData(searchTF.getText());
    }

    @FXML
    private void ajouterAbo(ActionEvent event) {
        currentAbo = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_ABO);
    }

    private void modifierAbo(Abo abo) {
        currentAbo = abo;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_ABO);
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
                MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_ABO);
            } else {
                AlertUtils.makeError("Could not delete abo");
            }
        }
    }

    @FXML
    public void makeExel(ActionEvent event) {
        System.out.println("making exel");
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            FileChooser chooser = new FileChooser();
            // Set extension filter
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(.xls)", ".xls");
            chooser.getExtensionFilters().add(filter);

            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            workSheet.setColumnWidth(1, 25);

            HSSFFont fontBold = workbook.createFont();
            fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle styleBold = workbook.createCellStyle();
            styleBold.setFont(fontBold);

            Row row1 = workSheet.createRow((short) 0);
            workSheet.autoSizeColumn(7);
            row1.createCell(0).setCellValue("CreatedAt");
            row1.createCell(1).setCellValue("User");
            row1.createCell(2).setCellValue("Salle de sport");
            row1.createCell(3).setCellValue("Duree");

            int i = 0;
            for (Abo abo : listAbo) {
                i++;
                Row row2 = workSheet.createRow((short) i);
                row2.createCell(0).setCellValue(String.valueOf(abo.getCreatedAt()));
                row2.createCell(1).setCellValue(abo.getUser().getName());
                row2.createCell(2).setCellValue(abo.getSdp().getName());
                row2.createCell(3).setCellValue(abo.getDuree());
            }

            workbook.write(new FileOutputStream("abo.xls"));
            Desktop.getDesktop().open(new File("abo.xls"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
