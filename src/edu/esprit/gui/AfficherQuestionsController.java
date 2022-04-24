/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Reponses;
import edu.esprit.services.QuestionService;
import edu.esprit.services.ReponsesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AfficherQuestionsController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private Label labelid;
    @FXML
    private Label nbvue;
    @FXML
    private Label date;
    @FXML
    private Label tt;
    @FXML
    private ListView<String> listview;
    @FXML
    private AnchorPane add;
    @FXML
    private TextArea comm;
    @FXML
    private Button Envoyer;
    @FXML
    private ListView<String> listdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        question.setVisible(true);
        question.setText(QuestionGestionsController.connectedQuestion.getTitre());
        labelid.setText(Integer.toString(QuestionGestionsController.connectedQuestion.getId()));

        nbvue.setText(Integer.toString(QuestionGestionsController.connectedQuestion.getNbr_vu()));
        tt.setText(QuestionGestionsController.connectedQuestion.getMessage());

        QuestionService pss = new QuestionService();
        ArrayList<Reponses> c = new ArrayList<>();
        try {
            c = (ArrayList<Reponses>) pss.getAllReponsesForQuestions(Integer.parseInt(labelid.getText()));
        } catch (SQLException ex) {
        }

        for (Reponses r : c) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(r.getDate_r());
            listview.getItems().add(r.getMessage_r());
            listdate.getItems().add(strDate);
        }


    }


    @FXML
    private void GotoStage(ActionEvent event) {
        ReponsesService productService = new ReponsesService();

        if (comm.getText().equals("")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (comm.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
        ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        Date date = new Date(System.currentTimeMillis());

        java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());


        Reponses c;
        c = new Reponses(comm.getText(),
                sqlDate2, Integer.parseInt(labelid.getText()),

                0);


    }


    public void resetTableData() throws SQLException {
        QuestionService pss = new QuestionService();
        ArrayList<Reponses> c = new ArrayList<>();
        try {
            c = (ArrayList<Reponses>) pss.getAllReponsesForQuestions(Integer.parseInt(labelid.getText()));
        } catch (SQLException ex) {
        }


        listview.getItems().add(comm.getText());


        ArrayList<Reponses> c2 = new ArrayList<>();
        try {
            c = (ArrayList<Reponses>) pss.getAllReponsesForQuestions(Integer.parseInt(labelid.getText()));
        } catch (SQLException ex) {
        }

        for (Reponses r : c2) {


            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(r.getDate_r());
            listdate.getItems().add(date.getText());

            listdate.getItems().add(strDate);
        }

    }


    public void resetTableData2() throws SQLException {
        QuestionService pss = new QuestionService();
        ArrayList<Reponses> c = new ArrayList<>();
        try {
            c = (ArrayList<Reponses>) pss.getAllReponsesForQuestions(Integer.parseInt(labelid.getText()));
        } catch (SQLException ex) {
        }


        // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(sqlDate2);
        listdate.getItems().add(strDate);


    }


}
