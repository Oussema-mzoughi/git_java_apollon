/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Role;
import edu.esprit.entities.Salle;
import edu.esprit.entities.User;
import edu.esprit.services.SalleService;
import edu.esprit.services.Userservice;
import edu.esprit.tools.Connexion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Oussema
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
        Connection cnx;   
         @FXML
  private Pane pnl_add_user,pnl_list_user,pnl_add_article,pnl_list_article,pnl_update_user;
  
  @FXML
  private Button btn_menu_add_user,btn_menu_list_user,btn_menu_add_article,btn_menu_list_article,btn_update_user;
  
  
  @FXML
    private ImageView adduserimg;
  
  @FXML
    private Accordion menu_accordion;
  
   @FXML
    private Label userimagepath,add_email_error,add_login_error;
    @FXML
    private Label update_userimagepath,user_update_id;
   
   @FXML
    private ComboBox<Role> add_role;
   
   @FXML
    private ComboBox<Role> update_role;
   
   @FXML
    private TextField add_nom,add_prenom,add_email,add_adresse,add_ville,add_cp,add_tel,add_login,add_mdp;
   

   
    @FXML
    private TextField update_nom,update_prenom,update_email,update_adresse,update_ville,update_cp,update_tel,update_login,update_mdp;
  
  @FXML
    private TableView tv_user;
  
  
  @FXML
    private TableColumn<?, ?> tv_nom,tv_prenom,tv_email,tv_role,tv_etat,tv_id;
  
  
  @FXML
    private Button Delete,Update,btn_profil;
  
   @FXML
    private TextField inputRech;
   
  public ObservableList<User> list;
  
  public HomeController() {
        cnx = Connexion.getInstance().getCnx();
    
           
    } 
  
   @FXML
    private void Update_user(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
        
        
        Userservice us = new Userservice();
        
        User user1 = (User)tv_user.getSelectionModel().getSelectedItem();
       // int x= Integer.parseInt(tv_user.getSelectionModel().getSelectedItem().toString().substring(8,10));
       //  int y= Integer.parseInt(tv_user.getSelectionModel().getSelectedItem().);
         //us.Updateuser(user1);
        //user1.setId(x);
            user_update_id.setText(String.valueOf(user1.getId()));
            update_nom.setText(user1.getNom());
            update_prenom.setText(user1.getPrenom());
            update_email.setText(user1.getEmail());
            update_adresse.setText(user1.getAdresse());
            update_ville.setText(user1.getVille());
            update_cp.setText(user1.getCp());
            update_tel.setText(user1.getTel());
            update_login.setText(user1.getLogin());
           
            Role r =new Role();
            r.setName(user1.getRole_id());
         for (Role t : update_role.getItems()) {
        if (user1.getRole_id().equals(t.getName())) {
            update_role.setValue(t);
        }
    }
            //update_role.setSelectedItem(r);
           // update_role.setValue(r);
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
          pnl_update_user.setVisible(true);
        
        
      
       
        
    }

     @FXML
    private void btn_update_user(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
    Role r=update_role.getSelectionModel().getSelectedItem();
        
        if (update_nom.getText().equals("")||update_prenom.getText().equals("")||update_email.getText().equals("")||update_email.getText().equals("")||
                update_tel.getText().equals("")||update_adresse.getText().equals("")||update_ville.getText().equals("")||update_cp.getText().equals("")||
                update_login.getText().equals("")||r.getName().equals(""))
        {
              JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs svp!");
        }else
        {
                   
        // Enter data using BufferReader
        User user1=new User();
        //System.out.println(user_update_id.getText());
        user1.setId(Integer.parseInt(user_update_id.getText()));
        user1.setNom(update_nom.getText());
        user1.setPrenom(update_prenom.getText());
        user1.setEmail(update_email.getText());
        user1.setTel(update_tel.getText());
        user1.setAdresse(update_adresse.getText());
        user1.setVille(update_ville.getText());
        user1.setCp(update_cp.getText());
        user1.setLogin(update_login.getText());
       
        user1.setImg(update_userimagepath.getText());
        user1.setRole_id(String.valueOf(r.getId()));
        //user1.setRole_id(add_role.getValue().toString().substring(0,add_role.getValue().toString().indexOf("--")-1));
        //System.out.println(add_role.getValue().toString().substring(0,add_role.getValue().toString().indexOf("--")-1));
      
         
        Userservice us = new Userservice();
        if(us.Updateuser(user1))
        {
             JOptionPane.showMessageDialog(null, "Utilisateur Modifier avec success! Vous allez etre redirectionner vers la liste des utilisateurs");
         resetTableData();
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(true);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
          pnl_update_user.setVisible(false);
        }else
          JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs svp!!!");

           
        
        }
    }
    
    
    
    
 
  
  @FXML
  private void handleButtonAction(ActionEvent event) throws SQLException
  {
      if(event.getSource() == btn_menu_add_user)
      {
          pnl_add_user.setVisible(true);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
           pnl_update_user.setVisible(false);
          
          
      }else if(event.getSource() == btn_menu_list_user)
      {
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(true);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
          pnl_update_user.setVisible(false);
         
      }
      else if(event.getSource() == btn_menu_add_article)
      {
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(true);
          pnl_list_article.setVisible(false);
           pnl_update_user.setVisible(false);
           
      }
      else if(event.getSource() == btn_menu_list_article)
      {
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(true);
           pnl_update_user.setVisible(false);
         
      }
      
  }
  
    public void Delete_user(ActionEvent event) throws SQLException {
         
            User u = new User();
            int x= Integer.parseInt(tv_user.getSelectionModel().getSelectedItem().toString().substring(8,10));
            u.setId(x);  
            Userservice cs = new Userservice();
            cs.DeleteUser(u);
            resetTableData();  
        
        
    
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
        String req = "select * from role";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                Role r=new Role();
               
                
                r.setId(rst.getInt("id"));
                r.setName(rst.getString("name"));
                add_role.getItems().add(r);
                update_role.getItems().add(r);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
          Userservice us = new Userservice();
        ArrayList<User> u = new ArrayList<>();
       
            u = (ArrayList<User>) us.afficherUser();
       
        
        ObservableList<User> obs2 = FXCollections.observableArrayList(u);
        tv_user.setItems(obs2);
       
         tv_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tv_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));       
        tv_role.setCellValueFactory(new PropertyValueFactory<>("role_id"));
         tv_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         
         
         
         
            
         
         
            try {
            list = FXCollections.observableArrayList(
                    us.afficherUser()
            );   
            
            
           
            
            
            
          FilteredList<User> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super User>) Users -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Users.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<User> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tv_user.comparatorProperty());
                tv_user.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
     update_tel.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            update_tel.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
     
      add_tel.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            add_tel.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
          add_email.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        String regex = "^(.+)@(.+)$";
 
Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(add_email.getText());
         if(!newPropertyValue)
         {
              if (matcher.matches())
        {
            
            add_email_error.setVisible(false);
        }
        else
        {
            add_email_error.setVisible(true);
        }
         }
         

    }
});
          
          
           add_login.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
      
        
        String sql ="select u.id,nom,prenom,email,role_id,etat,tel,adresse,cp,login,ville,img,r.name from users u,role r where u.role_id=r.id and u.login like '"+add_login.getText()+"' order by u.id";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            int x=0;
            while(rs.next()){
               x=x+1;
            }
            if(x>0)
            {
                  add_login_error.setVisible(true);
            }else
            {
                 add_login_error.setVisible(false);
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          
        }
       
         
 
        
     

    }
});
          
          if(Connexion.user_inscription!=null)
          {
               if(Connexion.user_inscription.equals("1"))
          {
          pnl_add_user.setVisible(true);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
           pnl_update_user.setVisible(false);
           Connexion.user_inscription="0";
          }
          }
         
        
          
          
          
          
    }    
    
    
    
    
      public void resetTableData() throws SQLDataException, SQLException {
          Userservice us = new Userservice();
        List<User> listevents = new ArrayList<>();
        listevents = us.afficherUser();
        ObservableList<User> data = FXCollections.observableArrayList(listevents);
        tv_user.setItems(data);
    } 
    
      public void  afficherUser(ActionEvent event){
         try
         {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("add_user.fxml"));
              Parent root=loader.load();
              Add_userController auc=loader.getController();
         }catch(IOException ex)
         {
             System.out.println(ex.getMessage());
         }
      }
      
      
      
       @FXML
    private void adduserimg(ActionEvent event) {
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
            adduserimg.setImage(image);
            adduserimg.setFitWidth(200);
            adduserimg.setFitHeight(200);
            adduserimg.scaleXProperty();
            adduserimg.scaleYProperty();
            adduserimg.setSmooth(true);
            adduserimg.setCache(true);
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
        userimagepath.setText(file.getAbsolutePath());
    }
      
       public  void adduser(ActionEvent event) throws IOException, SQLException
    {
        Role r=add_role.getSelectionModel().getSelectedItem();
        
        if (add_nom.getText().equals("")||add_prenom.getText().equals("")||add_email.getText().equals("")||add_email.getText().equals("")||
                add_tel.getText().equals("")||add_adresse.getText().equals("")||add_ville.getText().equals("")||add_cp.getText().equals("")||
                add_login.getText().equals("")||add_mdp.getText().equals("")||r.getName().equals(""))
        {
              JOptionPane.showMessageDialog(null, "Merci de remplir tous les champs svp!");
        }else
        {
                   
        // Enter data using BufferReader
        User user1=new User();
        user1.setNom(add_nom.getText());
        user1.setPrenom(add_prenom.getText());
        user1.setEmail(add_email.getText());
        user1.setTel(add_tel.getText());
        user1.setAdresse(add_adresse.getText());
        user1.setVille(add_ville.getText());
        user1.setCp(add_cp.getText());
        user1.setLogin(add_login.getText());
        user1.setPassword(add_mdp.getText());
        user1.setImg(userimagepath.getText());
        user1.setRole_id(String.valueOf(r.getId()));
        //user1.setRole_id(add_role.getValue().toString().substring(0,add_role.getValue().toString().indexOf("--")-1));
        //System.out.println(add_role.getValue().toString().substring(0,add_role.getValue().toString().indexOf("--")-1));
        user1.setEtat("1");
         
        Userservice us = new Userservice();
        if(us.adduser(user1))
        {
             JOptionPane.showMessageDialog(null, "Utilisateur Ajouté avec success! Vous allez etre redirectionner vers la liste des utilisateurs");
         resetTableData();
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(true);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
        }else
          JOptionPane.showMessageDialog(null, "Veuillez  remplir tous les champs svp!!!");

           
        
        }
        
 
        
        
    }
       @FXML     
    public void btn_monprofil(ActionEvent event) throws SQLException, IOException {
       
       
          String sql ="select u.id,nom,prenom,email,role_id,etat,tel,adresse,cp,login,ville,img from users u where id='"+Connexion.user_id+"'";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                User p = new User();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                 p.setEmail(rs.getString("email"));
                 p.setTel(rs.getString("tel"));
                 p.setAdresse(rs.getString("adresse"));
                 p.setVille(rs.getString("ville"));
                 p.setCp(rs.getString("cp"));
                 p.setLogin(rs.getString("login"));
                 p.setImg(rs.getString("img"));
                 
                  user_update_id.setText(String.valueOf(p.getId()));
            update_nom.setText(p.getNom());
            update_prenom.setText(p.getPrenom());
            update_email.setText(p.getEmail());
            update_adresse.setText(p.getAdresse());
            update_ville.setText(p.getVille());
            update_cp.setText(p.getCp());
            update_tel.setText(p.getTel());
            update_login.setText(p.getLogin());
           
        
            //update_role.setSelectedItem(r);
           // update_role.setValue(r);
          pnl_add_user.setVisible(false);
          pnl_list_user.setVisible(false);
          pnl_add_article.setVisible(false);
          pnl_list_article.setVisible(false);
          pnl_update_user.setVisible(true);
        
              /*    Role r =new Role();
                 r.setId(rs.getInt("role_id"));
                 for (Role t : update_role.getItems()) {
                          if (p.getRole_id().equals(t.getId())) {
                      update_role.setValue(t);
                    }
            
                 }*/
            }
            
                 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            
        }
    }
       
     @FXML     
    public void btn_deco(ActionEvent event) throws SQLException, IOException {
         Userservice us=new Userservice();
         us.deco();
         Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
            
            Scene scene = new Scene(root);
            
           
            primaryStage.setScene(scene);
            primaryStage.show();
         
    }
    
   

    @FXML
    private void afficheuser(ActionEvent event) throws IOException {
            Userservice us = new Userservice();
          
   
    }

  
    
}
