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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import edu.esprit.entities.Salle;
import edu.esprit.entities.Users;
import edu.esprit.services.SalleService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
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
import edu.esprit.tools.Connexion;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterSalleController implements Initializable {
   Connection connexion;   
    @FXML
    private Button AjouterMat;
    @FXML
    private Button pdf2;
  public AjouterSalleController() {
        connexion = Connexion.getInstance().getCnx();
    }
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tadresse;
    @FXML
    private TextField Tville;
    @FXML
    private TextField Temail;
    @FXML
    private TextField Tnum;
    @FXML
    private TextField Tgerant;
    @FXML
    private TextField Tdirecteur;
    @FXML
    private ComboBox<Integer> Tuser;
    @FXML
    private Button Timage;
    @FXML
    private TableColumn<?, ?> nomcl;
    @FXML
    private TableColumn<?, ?> adressecl;
    @FXML
    private TableColumn<?, ?> emailcl;
    @FXML
    private TableColumn<?, ?> telcl;
    @FXML
    private TableColumn<?, ?> gerantcl;
    @FXML
    private TableColumn<?, ?> directeurcl;
    @FXML
    private TableView<Salle> tableview;
  public static Salle connectedSalle;
SalleService cs = new SalleService();
  public ObservableList<Salle> list;
    @FXML
    private TableColumn<?, ?> villesalle;
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
    private Button affichsalle;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
         try {
            String req = "select * from users";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                Tuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        SalleService pss = new SalleService();
        ArrayList<Salle> c = new ArrayList<>();
        try {
            c = (ArrayList<Salle>) pss.AfficherAllSalle();
        } catch (SQLException ex) {
        }
        
        ObservableList<Salle> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
 nomcl.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
        adressecl.setCellValueFactory(new PropertyValueFactory<>("adresse_salle"));
        emailcl.setCellValueFactory(new PropertyValueFactory<>("email"));
        telcl.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
         gerantcl.setCellValueFactory(new PropertyValueFactory<>("nomgerant"));
          directeurcl.setCellValueFactory(new PropertyValueFactory<>("nomdirecteur"));
           villesalle.setCellValueFactory(new PropertyValueFactory<>("ville_salle"));
    
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllSalle()
            );        
        
        
   FilteredList<Salle> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Salle>) Salles -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Salles.getNom_salle().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Salle> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                }    
  public void resetTableData() throws SQLDataException, SQLException {

        List<Salle> listevents = new ArrayList<>();
        listevents = cs.AfficherAllSalle();
        ObservableList<Salle> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }    
    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            Salle e = new Salle();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            SalleService cs = new SalleService();
            cs.supp2(e);
            resetTableData();  
        
        }
    
    }

    


    


  

    @FXML
    private void Ajouter(ActionEvent event) {
        SalleService productService = new SalleService();
  
        if (Tnom.getText().equals("")
                || Tadresse.getText().equals("") || Tville.getText().equals("")
                || Temail.getText().equals("")|| Tgerant.getText().equals("")|| Tdirecteur.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (Tnom.getText().equals("")
                || Tadresse.getText().equals("") || Tnom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || Temail.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
            

            Salle c = new Salle(Tuser.getValue(),
                    Tnom.getText(),
                  Tadresse.getText(), Tville.getText(), Temail.getText(),
                  Tnum.getText(), Tgerant.getText()   , Tdirecteur.getText(), Timage.getText()                  );
        try {
            productService.ajouterSalle(c);
             resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
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
            SalleService ps = new SalleService();
          
   
        Salle c = new Salle(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getUsers_id(),
               tableview.getSelectionModel().getSelectedItem().getNom_salle(),
                 tableview.getSelectionModel().getSelectedItem().getAdresse_salle(),
                tableview.getSelectionModel().getSelectedItem().getVille_salle(),
                tableview.getSelectionModel().getSelectedItem().getEmail(),
                         tableview.getSelectionModel().getSelectedItem().getNum_tel(),
                                  tableview.getSelectionModel().getSelectedItem().getNomgerant(),
                                                  tableview.getSelectionModel().getSelectedItem().getNomdirecteur(),
                                  tableview.getSelectionModel().getSelectedItem().getImage()
                );
           Tuser.setValue(tableview.getSelectionModel().getSelectedItem().getUsers_id());
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getUsers_id()));
         
            Tnom.setText(tableview.getSelectionModel().getSelectedItem().getNom_salle());
            
            Tadresse.setText(tableview.getSelectionModel().getSelectedItem().getAdresse_salle());
            Tville.setText(tableview.getSelectionModel().getSelectedItem().getVille_salle());

                        Temail.setText(tableview.getSelectionModel().getSelectedItem().getEmail());
            Tnum.setText(tableview.getSelectionModel().getSelectedItem().getNum_tel());
            Tgerant.setText(tableview.getSelectionModel().getSelectedItem().getNomgerant());
            Tdirecteur.setText(tableview.getSelectionModel().getSelectedItem().getNomgerant());
            Timage.setText(tableview.getSelectionModel().getSelectedItem().getImage());

         
           Confirmer.setVisible(true);

  
        
    }

    @FXML
    private void affichsalle(ActionEvent event) throws IOException {
            SalleService ps = new SalleService();
          
   
        Salle c = new Salle(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getUsers_id(),
               tableview.getSelectionModel().getSelectedItem().getNom_salle(),
                 tableview.getSelectionModel().getSelectedItem().getAdresse_salle(),
                tableview.getSelectionModel().getSelectedItem().getVille_salle(),
                tableview.getSelectionModel().getSelectedItem().getEmail(),
                         tableview.getSelectionModel().getSelectedItem().getNum_tel(),
                                  tableview.getSelectionModel().getSelectedItem().getNomgerant(),
                                                  tableview.getSelectionModel().getSelectedItem().getNomdirecteur(),
                                  tableview.getSelectionModel().getSelectedItem().getImage()
                );
        AjouterSalleController.connectedSalle = c;
                           Parent page1 = FXMLLoader.load(getClass().getResource("AfficherSalledetail.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
                SalleService productService = new SalleService();

        Salle c = new Salle(Integer.parseInt(labelid.getText()),Tuser.getValue(),
                    Tnom.getText(),
                  Tadresse.getText(), Tville.getText(), Temail.getText(),
                  Tnum.getText(), Tgerant.getText()   , Tdirecteur.getText(), Timage.getText()                  );
        try {
            productService.modifierSalle(c);
            resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AjouterMat(ActionEvent event) throws IOException {
        
            Parent page1 = FXMLLoader.load(getClass().getResource("AjoutMateriel.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Gestion des Salles de Sports");
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
            pTable.addCell(String.valueOf(t.getNom_salle()));
  
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
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