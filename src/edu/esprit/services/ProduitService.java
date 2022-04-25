/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Produit;
import edu.esprit.tools.Connexion;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class ProduitService {
    
     Connection connexion;   
  public ProduitService() {
        connexion = Connexion.getInstance().getCnx();
    }
 

    public void modifierProduit(Produit p) throws SQLException, NoSuchAlgorithmException {
       
//          String req = "UPDATE evenement SET "
//                + " NomEvent='"+e.getNomEvent()+"'"
//                + ", DescriptionEvent='"+e.getDescriptionEvent()+"'"
//                + ", DateEvent  ='"+(java.sql.Date) (Date) e.getDateEvent()+"'"
//                + ", Prixevent='"+e.getPrixevent()+"' where IdEvent  = "+e.getIdEvent()+"";
        
        
        String req = "UPDATE produit SET "
                + ", nom='"+p.getNom()+"'"
                + ", prix='"+p.getPrix()+"'"
                + ", description='"+p.getDescription()+"'"
                + ", stock='"+p.getStock()+"'"
                + ", image='"+p.getImage()+"' where id  = "+p.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
    
    
    
    
          public void ajouterProduit(Produit p) throws SQLException {
        String req = "INSERT INTO `produit`(`nom_produit`, `prix_produit`, `description`, `image_path`, `stock`) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setFloat(2, p.getPrix());
        ps.setString(3, p.getDescription());
        ps.setInt(4, p.getStock());
        ps.setString(5, p.getImage());

        ps.executeUpdate();
    }
      
     
        
        
 

     

  
 
     public List<Produit> AfficherAllProduit() throws SQLException {

        List<Produit> Produits = new ArrayList<>();
        String req = "select * from produit ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit p = new Produit(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getFloat("prix")
                    , rst.getString("description")
                    , rst.getString("image")
                    , rst.getInt("stock"));
            Produits.add(p);
        }
        return Produits;
    }
     
   
  
     public void SupprimerProduit(Produit p) throws SQLException {

        String req = "DELETE FROM produit WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     
    
   public void supp2(Produit p) throws SQLException {

        String req = "DELETE FROM produit WHERE id ="+p.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
}
