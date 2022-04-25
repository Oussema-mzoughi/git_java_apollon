/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;

import edu.esprit.services.EvenementServices;

import edu.esprit.tools.Connexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 *
 * @author LENOVO
 */
public class AfficherEvenementController implements Initializable {
 Connection connexion;  

    public AfficherEvenementController() {
        connexion = Connexion.getInstance().getCnx();
    }
public static Evenement connectedEvenement;
    @FXML
    private Hyperlink Reponsess;
    @FXML
    private TextField inputtitre;
     @FXML
    private TextField inputdescription;
     @FXML
    private TextField inputadresse;
    @FXML
    private TextField inputnbrmax;
    @FXML
    private Button supp;
    @FXML
    private TableView<Evenement> tableview;
    @FXML
    private TableColumn<?, ?> Titre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> Adresse;
    @FXML
    private TableColumn<?, ?> Date_debut;
    @FXML
    private TableColumn<?, ?> Date_fin;
    @FXML
    private TableColumn<?, ?> categorie;
    @FXML
    private TableColumn<?, ?> nbrMax;
    @FXML
    private TableColumn<?, ?> tabimage;
    @FXML
    private TextField inputRech;
    @FXML
    private ImageView importeimage;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
     @FXML
    private ComboBox<Integer> inputcategorie;
    public ObservableList<Evenement> list;
    List<String> type;
     String img="";

  @Override
    public void initialize(URL url, ResourceBundle rb) {

         try {
            String req = "select * from categorie";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputcategorie.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

     EvenementServices pss = new EvenementServices();
        ArrayList<Evenement> c = new ArrayList<>();
        try {
            c = (ArrayList<Evenement>) pss.ShowEvenement();
        } catch (SQLException ex) {
        }
        
        ObservableList<Evenement> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");        

        Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_deb"));
         Date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        nbrMax.setCellValueFactory(new PropertyValueFactory<>("Nbr_max"));
        tabimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    
            try {
            list = FXCollections.observableArrayList(
                    pss.ShowEvenement()
            );        
        
        
   FilteredList<Evenement> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Evenement>) Questionss -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Questionss.getTitre().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Evenement> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
}

    @FXML
    private void Modif(ActionEvent event)
    {}
 @FXML
    private void affichEvent(ActionEvent event) throws IOException
    {
//affficher les participant
   EvenementServices ps = new EvenementServices();
         
        Evenement c = new Evenement(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getNbr_max(),
                tableview.getSelectionModel().getSelectedItem().getTitre(),
               tableview.getSelectionModel().getSelectedItem().getDescription(),
                 tableview.getSelectionModel().getSelectedItem().getAdresse(),
                tableview.getSelectionModel().getSelectedItem().getDate_deb(),
                tableview.getSelectionModel().getSelectedItem().getDate_fin(),
                         tableview.getSelectionModel().getSelectedItem().getCategorie(),
                   tableview.getSelectionModel().getSelectedItem().getImage()
                );
        AfficherEvenementController.connectedEvenement = c;
        Parent page1 = FXMLLoader.load(getClass().getResource("AfficherDetailsEvent.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          
        
        }
    @FXML
    private void supp(ActionEvent event) throws SQLException 
    {
           if (event.getSource() == supp) {
           int e=tableview.getSelectionModel().getSelectedItem().getId();  
          EvenementServices cs = new EvenementServices();
            cs.DeleteEvenement(e);
            resetTableData();  
        JOptionPane.showMessageDialog(null, "Evenement supprimer");
        }
    }
     @FXML
    private void Ajouter(ActionEvent event) {
 EvenementServices EvenementServices = new EvenementServices();
                   LocalDate now = LocalDate.now();
        
                if (inputdescription.getText().equals("")
                || inputtitre.getText().equals("") 
                || inputnbrmax.getText().equals("") 
                || inputadresse.getText().equals("")
                || datedebut.getValue().equals("")||
                datefin.getValue().equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
         else if (datedebut.getValue().isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date début doit etre supérieure à celle d'aujourd'hui ! ");
            alert.showAndWait();
        }
        else if(datedebut.getValue().compareTo(datefin.getValue())> 0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("la date debut doit etre inferieur a la date fin !");
            alert.showAndWait();
      }
         
          else {
        Date date = new Date(System.currentTimeMillis());
         
               
                LocalDate dd =datedebut.getValue();
                LocalDate df =datefin.getValue();
                java.sql.Date dateDebut = java.sql.Date.valueOf(dd);
                java.sql.Date dateFin = java.sql.Date.valueOf(df);
                int nbrmax=Integer.parseInt(inputnbrmax.getText());
        
            Evenement c = new Evenement(nbrmax,inputtitre.getText(),inputdescription.getText(),inputadresse.getText(),
                    dateDebut,dateFin,Integer.toString(inputcategorie.getValue()),img);
            
        System.out.println(" Evenement Cree !");
        Notifications.create().title("Merci").text("Votre Evenement a été Cree avec succes ").showWarning();
        
                    
                try {
            EvenementServices.AddEvenementPst(c);
             resetTableData();
            inputnbrmax.clear();
            inputtitre.clear();
             inputdescription.clear();
            inputadresse.clear();
            datedebut.setValue(null);
            datefin.setValue(null);
            importeimage.setImage(null);
            inputcategorie.setValue(null);
JOptionPane.showMessageDialog(null, "ajout avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
        };
        }
    }
    @FXML
    private void importimage(ActionEvent event) {
       
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        
        if(fc != null)
        {   
            System.out.println(fc.getName());
            img=fc.getName();
           FileSystem fileSys = FileSystems.getDefault();
           Path srcPath= fc.toPath();
           Path destPath= fileSys.getPath("C:\\xampp\\img\\"+fc.getName());
            try {
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AfficherEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(srcPath.toString());
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            importeimage.setImage(i);
            
        }
       
        
    }

  public void resetTableData() throws SQLDataException, SQLException {
      EvenementServices cs = new EvenementServices();
      List<Evenement> listevents = new ArrayList<>();
        listevents = cs.ShowEvenement();
        ObservableList<Evenement> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    } 
    
   @FXML
    private void Reponsess(ActionEvent event) throws IOException {
                            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherTousEvenements.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }
}
