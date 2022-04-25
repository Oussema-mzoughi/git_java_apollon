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
import edu.esprit.entities.Produit;
import edu.esprit.services.ProduitService;
import edu.esprit.tools.Connexion;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutProduitController implements Initializable {


      Connection connexion;   
    @FXML
    private Button AjouterCom;
    @FXML
    private Button pdf2;
  public AjoutProduitController() {
        connexion = Connexion.getInstance().getCnx();
    }
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tprix;
    @FXML
    private TextField Tdescription;
    @FXML
    private TextField Tstock;
    @FXML
    private Button Timage;
    @FXML
    private TableColumn<?, ?> nomcl;
    @FXML
    private TableColumn<?, ?> prixcl;
    @FXML
    private TableColumn<?, ?> descriptioncl;
    @FXML
    private TableColumn<?, ?> stockcl;
    @FXML
    private TableView<Produit> tableview;
  public static Produit connectedProduit;
ProduitService cs = new ProduitService();
  public ObservableList<Produit> list;
    
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button affichProduit;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
             
        
        
        
        ProduitService pss = new ProduitService();
        ArrayList<Produit> p = new ArrayList<>();
        try {
            p = (ArrayList<Produit>) pss.AfficherAllProduit();
        } catch (SQLException ex) {
        }
        
        ObservableList<Produit> obs2 = FXCollections.observableArrayList(p);
        tableview.setItems(obs2);
        
        
        nomcl.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prixcl.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descriptioncl.setCellValueFactory(new PropertyValueFactory<>("description"));
        stockcl.setCellValueFactory(new PropertyValueFactory<>("stock"));
            
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllProduit()
            );        
        
        
             FilteredList<Produit> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Produit>) Produits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Produits.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Produit> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                }    
  public void resetTableData() throws SQLDataException, SQLException {

        List<Produit> listevents = new ArrayList<>();
        listevents = cs.AfficherAllProduit();
        ObservableList<Produit> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }    
    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            Produit p = new Produit();
            p.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            ProduitService cs = new ProduitService();

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous vraiment supprimer le produit N°"+ p.getId() +" ? ");
            a.setHeaderText(null);
            a.showAndWait();
            
            cs.supp2(p);
            resetTableData();  
        
        }
    
    }

    


    


  

    @FXML
    private void Ajouter(ActionEvent event) {
        ProduitService productService = new ProduitService();
  
        if (Tnom.getText().equals("")
                || Tprix.getText().equals("") || Tdescription.getText().equals("") || Tstock.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
           // a.setHeaderText(null);
            a.showAndWait();
        } else if (Tnom.getText().equals("")
                || Tprix.getText().equals("") || Tnom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || Tstock.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
           // a.setHeaderText(null);
            a.showAndWait();
        }
        
     
       
            

            Produit p;
                p = new Produit(  Tnom.getText(),
                Float.valueOf(Tprix.getText()),
                Tdescription.getText(),
                Timage.getText(),
                Integer.parseInt(Tstock.getText()));
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Voulez-vous vraiment Ajouter ce produit ");
            a.setHeaderText(null);
            a.showAndWait();
                    try {
            productService.ajouterProduit(p);
             resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
        
    }
 @FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
   
      @FXML
    private void Modif(ActionEvent event) throws IOException {
            ProduitService ps = new ProduitService();
          
   
        Produit p = new Produit(tableview.getSelectionModel().getSelectedItem().getId(),
               tableview.getSelectionModel().getSelectedItem().getNom(),
                tableview.getSelectionModel().getSelectedItem().getPrix(),
                tableview.getSelectionModel().getSelectedItem().getDescription(),
                tableview.getSelectionModel().getSelectedItem().getImage(),
                tableview.getSelectionModel().getSelectedItem().getStock()
                );
           
         
             labelid.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getId()));
            Tnom.setText(tableview.getSelectionModel().getSelectedItem().getNom());
            Tprix.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getPrix()));
            Tdescription.setText(tableview.getSelectionModel().getSelectedItem().getDescription());
            Timage.setText(tableview.getSelectionModel().getSelectedItem().getImage());
            Tstock.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getStock()));


         
           Confirmer.setVisible(true);

  
        
    }

    @FXML
    private void affichProduit(ActionEvent event) throws IOException {
            ProduitService ps = new ProduitService();
          
   
        Produit c = new Produit(tableview.getSelectionModel().getSelectedItem().getId(),
               tableview.getSelectionModel().getSelectedItem().getNom(),
                 tableview.getSelectionModel().getSelectedItem().getPrix(),
                tableview.getSelectionModel().getSelectedItem().getDescription(),
                tableview.getSelectionModel().getSelectedItem().getImage(),
                tableview.getSelectionModel().getSelectedItem().getStock()
                );
        AjoutProduitController.connectedProduit = c;
                           Parent page1 = FXMLLoader.load(getClass().getResource("AfficherProduitdetail.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
                ProduitService productService = new ProduitService();
                Produit c = new Produit(Integer.parseInt(labelid.getText()),
                Tnom.getText(),
                Float.valueOf(Tprix.getText()),
                Tdescription.getText(),
                Timage.getText(),
                Integer.parseInt(Tstock.getText()));
        try {
            productService.modifierProduit(c);
            resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AjouterCom(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("AjoutCommande.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Produit");
        stage.setScene(scene);
        stage.show(); 
        
        
    }
    
      private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\fares\\Desktop\\ListSalle.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Evenements"));
        
        PdfPTable pTable = new PdfPTable(1);
       

     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getNom()));
  
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
