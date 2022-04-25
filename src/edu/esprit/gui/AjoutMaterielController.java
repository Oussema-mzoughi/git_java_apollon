/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Materiel;
import edu.esprit.gui.AjouterSalleController;
import edu.esprit.services.MaterielService;
import edu.esprit.tools.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AjoutMaterielController implements Initializable {

    public ObservableList<Materiel> list;
    Connection connexion;
    MaterielService cs = new MaterielService();
    @FXML
    private TextField type;
    @FXML
    private TextField nom;
    @FXML
    private TextField Tdirecteur;
    @FXML
    private TextField inputRech;
    @FXML
    private TableColumn<?, ?> typetable;
    @FXML
    private TableColumn<?, ?> Nomtable;
    @FXML
    private TableColumn<?, ?> Codetable;
    @FXML
    private TableColumn<?, ?> Salletable;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
    @FXML
    private ComboBox<Integer> Salle;
    @FXML
    private Label code;
    @FXML
    private TableView<Materiel> tableview;
    public AjoutMaterielController() {
        connexion = Connexion.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select * from salle_de_sport";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                //   Users a = new Users(rst.getInt("id"));

                Integer xx = rst.getInt("id");
                Salle.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        MaterielService pss = new MaterielService();
        ArrayList<Materiel> c = new ArrayList<>();
        try {
            c = (ArrayList<Materiel>) pss.AfficherAllMateriel();
        } catch (SQLException ex) {
        }

        ObservableList<Materiel> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);


        typetable.setCellValueFactory(new PropertyValueFactory<>("type"));
        Nomtable.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Codetable.setCellValueFactory(new PropertyValueFactory<>("code"));
        Salletable.setCellValueFactory(new PropertyValueFactory<>("salle_id_id"));


        try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllMateriel()
            );


            FilteredList<Materiel> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Materiel>) Materiels -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        return Materiels.getNom().toLowerCase().contains(lower);
                    });
                });


                SortedList<Materiel> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetTableData() throws SQLException {

        List<Materiel> listevents = new ArrayList<>();
        listevents = cs.AfficherAllMateriel();
        ObservableList<Materiel> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }


    @FXML
    private void supp(ActionEvent event) throws SQLException {
        if (event.getSource() == supp) {
            Materiel e = new Materiel();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());
            MaterielService materielservice = new MaterielService();
            materielservice.SupprimerMateriel(e);
            resetTableData();

        }


    }


    @FXML
    private void Ajouter(ActionEvent event) {
        MaterielService productService = new MaterielService();

        if (type.getText().equals("")
                || nom.getText().equals("") || Tdirecteur.getText().equals("")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (type.getText().equals("")
                || nom.getText().equals("") || Tdirecteur.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        } else {


            Materiel c = new Materiel(Salle.getValue(),
                    type.getText(),
                    nom.getText(), Tdirecteur.getText());
            try {
                productService.ajouterMateriel(c);
                resetTableData();
            } catch (SQLException ex) {
                Logger.getLogger(edu.esprit.gui.AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void Modif(ActionEvent event) {
        MaterielService ps = new MaterielService();


        Materiel c = new Materiel(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getSalle_id_id(),
                tableview.getSelectionModel().getSelectedItem().getType(),
                tableview.getSelectionModel().getSelectedItem().getNom(),
                tableview.getSelectionModel().getSelectedItem().getCode());


        Salle.setValue(tableview.getSelectionModel().getSelectedItem().getSalle_id_id());

        labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));

        nom.setText(tableview.getSelectionModel().getSelectedItem().getNom());

        type.setText(tableview.getSelectionModel().getSelectedItem().getType());
        Tdirecteur.setText(tableview.getSelectionModel().getSelectedItem().getCode());

        Confirmer.setVisible(true);


    }


    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
        MaterielService productService = new MaterielService();

        Materiel c = new Materiel(Integer.parseInt(labelid.getText()), Salle.getValue(),
                type.getText(),
                nom.getText(), Tdirecteur.getText());
        try {
            productService.modifierMateriel(c);
            resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}
