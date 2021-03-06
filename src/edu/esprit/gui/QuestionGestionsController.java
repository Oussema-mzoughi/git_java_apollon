/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Questions;
import edu.esprit.services.QuestionService;
import edu.esprit.tools.Connexion;
import edu.esprit.tools.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * FXML Controller class
 *
 * @author Fakher
 */
public class QuestionGestionsController implements Initializable {
    public static Questions connectedQuestion;
    public ObservableList<Questions> list;
    Connection connexion;
    @FXML
    private TableColumn<?, ?> nbrVues;
    @FXML
    private Label datenow;
    @FXML
    private Label datee;
    @FXML
    private Hyperlink Reponsess;
    @FXML
    private TextField inputtitre;
    @FXML
    private TextField inputmessage;
    @FXML
    private TextField inputSolution;
    @FXML
    private TableView<Questions> tableview;
    @FXML
    private TableColumn<?, ?> Titre;
    @FXML
    private TableColumn<?, ?> Message;
    @FXML
    private TableColumn<?, ?> Solution;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> User;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button affichsalle;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
    @FXML
    private ComboBox<Integer> inputuser;
    @FXML
    private Label nbvue;
    private int id;
    private String titre;
    private Date date_post;
    private String message;
    private String solution;
    private int user_id;
    private int nbr_vu;

    public QuestionGestionsController() {
        connexion = Connexion.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select * from users";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                //   Users a = new Users(rst.getInt("id"));

                Integer xx = rst.getInt("id");
                inputuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        QuestionService pss = new QuestionService();
        ArrayList<Questions> c = new ArrayList<>();
        try {
            c = (ArrayList<Questions>) pss.AfficherAllQuestion();
        } catch (SQLException ex) {
        }

        ObservableList<Questions> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);


        Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Message.setCellValueFactory(new PropertyValueFactory<>("message"));
        Solution.setCellValueFactory(new PropertyValueFactory<>("solution"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_post"));
        User.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        nbrVues.setCellValueFactory(new PropertyValueFactory<>("nbr_vu"));

        try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllQuestion()
            );


            FilteredList<Questions> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Questions>) Questionss -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        return Questionss.getTitre().toLowerCase().contains(lower);
                    });
                });

                SortedList<Questions> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void resetTableData() throws SQLException {
        QuestionService cs = new QuestionService();
        List<Questions> listevents = new ArrayList<>();
        listevents = cs.AfficherAllQuestion();
        ObservableList<Questions> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
        if (event.getSource() == supp) {
            Questions e = new Questions();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());
            QuestionService cs = new QuestionService();
            cs.SupprimerQuestion(e);
            resetTableData();

        }


    }

    @FXML
    private void Modif(ActionEvent event) {

        QuestionService ps = new QuestionService();


        Questions c = new Questions(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getTitre(),
                tableview.getSelectionModel().getSelectedItem().getDate_post(),
                tableview.getSelectionModel().getSelectedItem().getMessage(),
                tableview.getSelectionModel().getSelectedItem().getSolution(),
                tableview.getSelectionModel().getSelectedItem().getUser_id(),
                tableview.getSelectionModel().getSelectedItem().getNbr_vu()

        );
        inputuser.setValue(tableview.getSelectionModel().getSelectedItem().getUser_id());

        labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));

        inputSolution.setText(tableview.getSelectionModel().getSelectedItem().getSolution());

        inputmessage.setText(tableview.getSelectionModel().getSelectedItem().getMessage());
        inputtitre.setText(tableview.getSelectionModel().getSelectedItem().getTitre());

        nbvue.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getNbr_vu()));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(tableview.getSelectionModel().getSelectedItem().getDate_post());
        datee.setText(strDate);


        Confirmer.setVisible(true);

    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        QuestionService productService = new QuestionService();

        if (

                inputmessage.getText().equals("")
                        || inputtitre.getText().equals("")
                        || inputSolution.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputmessage.getText().equals("")
                || inputtitre.getText().equals("") || inputSolution.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s???est produite. Veuillez r??essayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtitre.getText().length() < 10) {

            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Le titre doit etre superieur a 10");
            a.setHeaderText(null);
            a.showAndWait();
        } else {


            Date date = new Date(System.currentTimeMillis());

            java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());


            Questions c = new Questions(inputtitre.getText(),
                    sqlDate2,
                    inputmessage.getText(), inputSolution.getText(), inputuser.getValue(),
                    0);

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous ajouter cette question? ");
            a.setHeaderText(null);
            a.showAndWait();
            productService.ajouterQuestions(c);
            resetTableData();


        }

    }

    @FXML
    private void affichsalle(ActionEvent event) throws IOException {
        QuestionService ps = new QuestionService();


        Questions c = new Questions(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getTitre(),
                tableview.getSelectionModel().getSelectedItem().getDate_post(),
                tableview.getSelectionModel().getSelectedItem().getMessage(),
                tableview.getSelectionModel().getSelectedItem().getSolution(),
                tableview.getSelectionModel().getSelectedItem().getUser_id(),
                tableview.getSelectionModel().getSelectedItem().getNbr_vu()

        );
        QuestionGestionsController.connectedQuestion = c;
        MainWindowController.getInstance().loadInterface(Constants.FXML_AFFICHER_QUESTIONS);
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
        QuestionService productService = new QuestionService();

        if (inputmessage.getText().equals("")
                || inputtitre.getText().equals("")
                || inputSolution.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputmessage.getText().equals("")
                || inputtitre.getText().equals("") || inputSolution.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s???est produite. Veuillez r??essayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        Date date = new Date(System.currentTimeMillis());

        java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());


        Questions c = new Questions(Integer.parseInt(labelid.getText()), inputtitre.getText(),
                sqlDate2,
                inputmessage.getText(), inputSolution.getText(), inputuser.getValue(),
                Integer.parseInt(nbvue.getText()));

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Voulez-vous Modifier cette question? ");
        a.setHeaderText(null);
        a.showAndWait();
        productService.modifierQuestion(c);
        resetTableData();
    }

    @FXML
    private void Reponsess(ActionEvent event) {
        MainWindowController.getInstance().loadInterface(Constants.FXML_AFFICHER_REPONSES);
    }
}
