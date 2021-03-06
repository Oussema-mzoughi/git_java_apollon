/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Commande;
import edu.esprit.services.CommandeService;
import edu.esprit.tools.Connexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutCommandeController implements Initializable {

     Connection connexion;   
    @FXML
    private Button AjouterCom;
    @FXML
    private Button pdf2;
  public AjoutCommandeController() {
        connexion = Connexion.getInstance().getCnx();
    }
    @FXML
    private TextField Tnumero;
    @FXML
    private TextField Tlivreur;
    @FXML
    private TextField Ttotale;
    @FXML
    private TableColumn<?, ?> numerocl;
    @FXML
    private TableColumn<?, ?> livreurcl;
    @FXML
    private TableColumn<?, ?> totalecl;
    @FXML
    private TableView<Commande> tableview;
  public static Commande connectedCommande;
    CommandeService cs = new CommandeService();
  public ObservableList<Commande> list;
    
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Button affichCommande;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
           
        
        
        CommandeService pss = new CommandeService();
        ArrayList<Commande> p = new ArrayList<>();
        try {
            p = (ArrayList<Commande>) pss.AfficherAllCommande();
        } catch (SQLException ex) {
        }
        
        ObservableList<Commande> obs2 = FXCollections.observableArrayList(p);
        tableview.setItems(obs2);
        
        
        numerocl.setCellValueFactory(new PropertyValueFactory<>("numero"));
        livreurcl.setCellValueFactory(new PropertyValueFactory<>("livreur"));
        totalecl.setCellValueFactory(new PropertyValueFactory<>("totale"));

            
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllCommande()
            );        
        
        
   FilteredList<Commande> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Commande>) Commandes -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Commandes.getLivreur().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Commande> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                }    
  public void resetTableData() throws SQLDataException, SQLException {

        List<Commande> listevents = new ArrayList<>();
        listevents = cs.AfficherAllCommande();
        ObservableList<Commande> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }    
    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            Commande p = new Commande();
            p.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            CommandeService cs = new CommandeService();
            
              Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous vraiment supprimer la commande N??"+ p.getId() +" ? ");
            a.setHeaderText(null);
            a.showAndWait();
            
            
            cs.supp2(p);
            resetTableData();  
        
        }
    
    }

    


    


  

    @FXML
    private void Ajouter(ActionEvent event) {
        CommandeService productService = new CommandeService();
  
        if (Tnumero.getText().equals("")
                || Tlivreur.getText().equals("") || Ttotale.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (Tnumero.getText().equals("")
                || Tlivreur.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s???est produite. Veuillez r??essayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
            

            Commande p;
       p = new Commande(Integer.parseInt(Tnumero.getText()),
                Tlivreur.getText(),
                Integer.parseInt(Ttotale.getText()));
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous vraiment Ajouter cette commande ");
            a.setHeaderText(null);
            a.showAndWait();
        try {
            productService.ajouterCommande(p);
             resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
        
    }
 
   
      @FXML
    private void Modif(ActionEvent event) throws IOException {
            CommandeService ps = new CommandeService();
          
   
        Commande p = new Commande(tableview.getSelectionModel().getSelectedItem().getId(),
               tableview.getSelectionModel().getSelectedItem().getNumero(),
                tableview.getSelectionModel().getSelectedItem().getLivreur(),
                tableview.getSelectionModel().getSelectedItem().getTotale()
                );
           
            labelid.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getId()));
            Tnumero.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getNumero()));
            Tlivreur.setText(tableview.getSelectionModel().getSelectedItem().getLivreur());
            Ttotale.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getTotale()));


         
           Confirmer.setVisible(true);

  
        
    }

    @FXML
    private void affichCommande(ActionEvent event) throws IOException {
            CommandeService ps = new CommandeService();
          
   
        Commande c = new Commande(tableview.getSelectionModel().getSelectedItem().getId(),
               tableview.getSelectionModel().getSelectedItem().getNumero(),
                tableview.getSelectionModel().getSelectedItem().getLivreur(),
                tableview.getSelectionModel().getSelectedItem().getTotale()
                );
        AjoutCommandeController.connectedCommande = c;
                           Parent page1 = FXMLLoader.load(getClass().getResource("AfficherCommandedetail.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
                CommandeService productService = new CommandeService();

        Commande c = new Commande(Integer.parseInt(labelid.getText()),
                   Integer.parseInt(Tnumero.getText()),
                               Tlivreur.getText(),
                Integer.parseInt(Ttotale.getText()));
        try {
            productService.modifierCommande(c);
            resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AjouterCom(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("AjoutCommande.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Commande");
        stage.setScene(scene);
        stage.show(); 
        
        
    }
    
      private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\fares\\Desktop\\ListCommande.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Commande"));
        
        PdfPTable pTable = new PdfPTable(1);
       

     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getNumero()));
  
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                Logger.getLogger(AjoutCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\fares\\Desktop\\ListEvents.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            
    
        } 
    
    
}
    
    
}
