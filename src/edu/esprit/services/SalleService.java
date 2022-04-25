/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Salle;
import edu.esprit.tools.Connexion;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
public class SalleService {

    Connection connexion;

    public SalleService() {
        connexion = Connexion.getInstance().getCnx();
    }


    public void modifierSalle(Salle e) throws SQLException, NoSuchAlgorithmException {

//          String req = "UPDATE evenement SET "
//                + " NomEvent='"+e.getNomEvent()+"'"
//                + ", DescriptionEvent='"+e.getDescriptionEvent()+"'"
//                + ", DateEvent  ='"+(java.sql.Date) (Date) e.getDateEvent()+"'"
//                + ", Prixevent='"+e.getPrixevent()+"' where IdEvent  = "+e.getIdEvent()+"";


        String req = "UPDATE salle_de_sport SET "
                + " users_id='" + e.getUsers_id() + "'"
                + ", nom_salle='" + e.getNom_salle() + "'"
                + ", adresse_salle='" + e.getAdresse_salle() + "'"
                + ", ville_salle='" + e.getVille_salle() + "'"
                + ", email='" + e.getEmail() + "'"
                + ", num_tel='" + e.getNum_tel() + "'"
                + ", nomgerant='" + e.getNomgerant() + "'"
                + ", nomdirecteur='" + e.getNomdirecteur() + "'"
                + ", image='" + e.getImage() + "' where id  = " + e.getId() + "";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterSalle(Salle e) throws SQLException {
        String req = "INSERT INTO `salle_de_sport` (`users_id`,`nom_salle`,`adresse_salle`,`ville_salle`,`email`,`num_tel`,`nomgerant`,`nomdirecteur`,`image`) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, e.getUsers_id());
        ps.setString(2, e.getNom_salle());

        ps.setString(3, e.getAdresse_salle());
        ps.setString(4, e.getVille_salle());
        ps.setString(5, e.getEmail());
        ps.setString(6, e.getNum_tel());

        ps.setString(7, e.getNomgerant());
        ps.setString(8, e.getNomdirecteur());
        ps.setString(9, e.getImage());

        ps.executeUpdate();
    }


    public List<Salle> AfficherAllSalle() throws SQLException {

        List<Salle> Salles = new ArrayList<>();
        String req = "select * from salle_de_sport ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Salle e = new Salle(rst.getInt("id")
                    , rst.getInt("users_id")
                    , rst.getString("nom_salle")
                    , rst.getString("adresse_salle")
                    , rst.getString("ville_salle")
                    , rst.getString("email")
                    , rst.getString("num_tel")
                    , rst.getString("nomgerant")
                    , rst.getString("nomdirecteur")
                    , rst.getString("image"));
            Salles.add(e);
        }
        return Salles;
    }


    public void SupprimerSalle(Salle e) throws SQLException {

        String req = "DELETE FROM salle_de_sport WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }


    public void supp2(Salle m) throws SQLException {

        String req = "DELETE FROM salle_de_sport WHERE id =" + m.getId() + "";

        PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();

    }


//    Connection cnx;
//
//    public SalleService() {
//        cnx =Connexion.getInstance().getCnx();
//    }
//    
//    
//   //ajouter une salle
//    
//     public  void AjouterSalle(Salle sdp) 
//    {
//      Salle sdp=new Salle();
//        // Enter data using BufferReader
//        BufferedReader reader = new BufferedReader(
//        new InputStreamReader(System.in));
//        
//        System.out.println("Nom Salle :");
//        String nom_salle = reader.readLine();
//        sdp.setNom_salle(nom_salle);
//        
//        System.out.println("Adresse ssalle:");
//        String adresse_salle = reader.readLine();
//        sdp.setAdresse_salle(adresse_salle);
//        
//        System.out.println("Ville salle:");
//        String ville_salle = reader.readLine();
//        sdp.setVille_salle(ville_salle);
//        
//        System.out.println("Email:");
//        String email = reader.readLine();
//        sdp.setEmail(email);
//        
//        System.out.println("Numero :");
//        String num_tel = reader.readLine();
//        sdp.setNum_tel(num_tel);
//        
//        System.out.println("Nom Gerant:");
//        String gerant = reader.readLine();
//        sdp.setNomgerant(gerant);
//        
//        System.out.println("Nom Directeur:");
//        String directeur = reader.readLine();
//        sdp.setNomdirecteur(directeur);
//        
//        System.out.println("Utilisateur");
//        String users = reader.readLine();
//        sdp.setUsers_id(users);
//        
//        sdp.setImage("default.png");
//        
//
//        
//       
//        try {
//             String query ="INSERT INTO salle_de_sport('nom_salle, 'adresse_salle', 'ville_salle', 'email', 'num_tel' , `nomgerant` , 'nomdirecteur' , 'image' , 'users_id') VALUES (?,?,?,?,?,?,?,?,?)";
//            PreparedStatement ste = cnx.prepareStatement(query);
//             ste.setString(1, sdp.getNom_salle());
//             ste.setString(2, sdp.getAdresse_salle());
//             ste.setString(3, sdp.getVille_salle());
//             ste.setString(4, sdp.getEmail());
//             ste.setString(5, sdp.getNum_tel());
//             ste.setString(6, sdp.getNomgerant());
//             ste.setString(7, sdp.getNomdirecteur());
//             ste.setString(8, sdp.getUsers_id());   
//             ste.setString(9, sdp.getImage());
//            
//           
//           
//            ste.executeUpdate();
//            
//           
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//    }
//
//    
//    
//    //affichage
//     public List<Salle> afficherSalle(){
//        List<Salle> salles = new ArrayList<>();
//        String sql ="select * from salle_de_sport";
//        Statement ste;
//        try {
//            ste = cnx.createStatement();
//            ResultSet rs = ste.executeQuery(sql);
//            while(rs.next()){
//                Salle sdp = new Salle();
//                sdp.setId(rs.getInt("id"));
//                sdp.setNom_salle(rs.getString("nom_salle"));
//                sdp.setAdresse_salle(rs.getString("adresse_salle"));
//                sdp.setVille_salle(rs.getString("ville_salle"));
//                sdp.setEmail(rs.getString("email"));
//                sdp.setNum_tel(rs.getString("num_tel"));
//                sdp.setNomgerant(rs.getString("nomgerant"));
//                sdp.setNomdirecteur(rs.getString("nomdirecteur"));
//                sdp.setUsers_id("users_id");
//                
//                salles.add(sdp);
//                
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//       
//        return salles;
//        
//    }
    
    
     
    
    
   /*  public void ModifierSalle(Salle sdp) {

        {
            PreparedStatement preparedStatement;

            try {
                preparedStatement = cnx.prepareStatement(
                        "UPDATE salle_de_sport "
                        + "SET nom_salle =  ?, adresse_salle = ?, ville_salle = ? ,email = ?, num_tel = ? , nomgerant = ? ,"
                                + "nomdirecteur = ? ,users_id=? "
                        + "WHERE id = ?");
             preparedStatement.setString(1, sdp.getNom_salle());
            preparedStatement.setString(2, sdp.getAdresse_salle());
            preparedStatement.setString(3, sdp.getVille_salle());
            preparedStatement.setString(4, sdp.getNum_tel());
            preparedStatement.setString(4, sdp.getEmail());
            preparedStatement.setString(4, sdp.getNomdirecteur());
            preparedStatement.setString(4, sdp.getNomgerant());
            preparedStatement.setString(4, sdp.getUsers_id());
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Erreur de modification Salle : " + e.getMessage());
            }

        }
    }

*/


}
