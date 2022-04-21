/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.forum.gui;

import edu.forum.entities.Questions;
import edu.forum.entities.Reponses;
import edu.esprit.services.QuestionService;
import edu.esprit.services.ReponsesService;
import edu.forum.tools.Connexion;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fakher
 */
public class ReponsesGestionController implements Initializable {
 Connection connexion;   
    @FXML
    private Button Confirmermodif;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    public ReponsesGestionController() {
        connexion = Connexion.getInstance().getCnx();
    }
    @FXML
    private TextField inputmessage;
    @FXML
    private TableView<Reponses> tableview;
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
    private TableColumn<?, ?> nbrVues;
    @FXML
    private TextField inputRech;
 
    
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button affichsalle;
    @FXML
    private Label labelid;
    private Button Confirmer;
    @FXML
    private Label nbvue;
    @FXML
    private Label datenow;
    @FXML
    private Label datee;
    @FXML
    private TableColumn<?, ?> message_r;
    @FXML
    private TableColumn<?, ?> date_r;
    @FXML
    private TableColumn<?, ?> question_id;
    @FXML
    private TableColumn<?, ?> user_id;
    @FXML
    private Button Ajouter1;
    @FXML
    private Label imgpathttt1;
    @FXML
    private Label labelid1;
    @FXML
    private Button Confirmer1;
    @FXML
    private Label nbvue1;
    @FXML
    private Label datenow1;
    @FXML
    private Label datee1;
    @FXML
    private ComboBox<Integer> inputuser;
    @FXML
    private ComboBox<Integer> inputquestion;
 public ObservableList<Reponses> list;
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
        
        
         try {
            String req = "select * from questions";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputquestion.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        ReponsesService pss = new ReponsesService();
        ArrayList<Reponses> c = new ArrayList<>();
        try {
            c = (ArrayList<Reponses>) pss.AfficherAllReponses();
        } catch (SQLException ex) {
        }
        
        ObservableList<Reponses> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
          
        
 message_r.setCellValueFactory(new PropertyValueFactory<>("message_r"));
        date_r.setCellValueFactory(new PropertyValueFactory<>("date_r"));
        question_id.setCellValueFactory(new PropertyValueFactory<>("question_id"));
      //  Date.setCellValueFactory(new PropertyValueFactory<>("date_post"));
         user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
     
    
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllReponses()
            );        
        
        
   FilteredList<Reponses> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Reponses>) Reponsess -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Reponsess.getMessage_r().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Reponses> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      ReponsesService cs = new ReponsesService();
      List<Reponses> listevents = new ArrayList<>();
        listevents = cs.AfficherAllReponses();
        ObservableList<Reponses> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }
  
  
  
    @FXML
    private void supp(ActionEvent event) throws SQLException {
           if (event.getSource() == supp) {
            Reponses e = new Reponses();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          ReponsesService cs = new ReponsesService();
              Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous Supprimer cette réponse?");
            a.setHeaderText(null);
            a.showAndWait();
            cs.SupprimerReponses(e);
            resetTableData();  
        
        }   
        
    }

  

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
           ReponsesService productService = new ReponsesService();
  
        if (inputmessage.getText().equals("")
                ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputmessage.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
            
     
            
         Reponses c = new Reponses(inputmessage.getText(),sqlDate2,inputquestion.getValue(),inputuser.getValue());
         
            
        
                  Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous ajouter cette réponse ");
            a.setHeaderText(null);
            a.showAndWait();
            productService.ajouterReponse(c);
             resetTableData();
      
     
        
        
    }

  @FXML
    private void Modif(ActionEvent event) {
          ReponsesService ps = new ReponsesService();
          
   
        Reponses c = new Reponses(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getMessage_r(),
               tableview.getSelectionModel().getSelectedItem().getDate_r(),
                 tableview.getSelectionModel().getSelectedItem().getQuestion_id(),
                tableview.getSelectionModel().getSelectedItem().getUser_id());
              
                                 
                
           inputuser.setValue(tableview.getSelectionModel().getSelectedItem().getUser_id());
           inputquestion.setValue(tableview.getSelectionModel().getSelectedItem().getQuestion_id());
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            
            inputmessage.setText(tableview.getSelectionModel().getSelectedItem().getMessage_r());

           
                  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           String strDate = dateFormat.format(tableview.getSelectionModel().getSelectedItem().getDate_r());       
            datee.setText(strDate);

         
           Confirmermodif.setVisible(true);  
        
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
       
        
        
    }

    @FXML
    private void affichsalle(ActionEvent event) {
    }

    @FXML
    private void Confirmermodif(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
          ReponsesService productService = new ReponsesService();
  
        if (inputmessage.getText().equals("")
                ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (
                inputmessage.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
            
       
            
        
            Reponses c = new Reponses(Integer.parseInt(labelid.getText()),inputmessage.getText(),
                    sqlDate2,
                  inputquestion.getValue(),
                        inputuser.getValue()  );
        
                   Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Confirmer ");
            a.setHeaderText(null);
            a.showAndWait();
            productService.modifierReponses(c);
             resetTableData();
        
        
      
        
        
    }

  
    }

 
    

