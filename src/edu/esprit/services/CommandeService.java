/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commande;
import edu.esprit.tools.Connexion;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
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
public class CommandeService {
 
    
     Connection connexion;   
  public CommandeService() {
        connexion = Connexion.getInstance().getCnx();
    }
 

    public void modifierCommande(Commande c) throws SQLException, NoSuchAlgorithmException {
       
//          String req = "UPDATE evenement SET "
//                + " NomEvent='"+e.getNomEvent()+"'"
//                + ", DescriptionEvent='"+e.getDescriptionEvent()+"'"
//                + ", DateEvent  ='"+(java.sql.Date) (Date) e.getDateEvent()+"'"
//                + ", Prixevent='"+e.getPrixevent()+"' where IdEvent  = "+e.getIdEvent()+"";
        
        
        String req = "UPDATE commande SET "
                + ", numero='"+c.getNumero()+"'"
                + ", date_commande='"+c.getDate_commande()+"'"
                + ", date_livraison='"+c.getDate_livraison()+"'"
                + ", livreur='"+c.getLivreur()+"'"
                + ", totale='"+c.getTotale()+"' where id  = "+c.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
    
    
    
    
          public void ajouterCommande(Commande c) throws SQLException {
        String req = "INSERT INTO `commande`(`numero`, `date_commande`, `date_livraison`, `livreur`, `totale`) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, c.getNumero());
        ps.setDate(2, (Date) c.getDate_commande());
        ps.setDate(3, (Date) c.getDate_livraison());
        ps.setString(4, c.getLivreur());
        ps.setInt(5, c.getTotale());

        ps.executeUpdate();
    }
      
     
        
        
 

     

  
 
     public List<Commande> AfficherAllCommande() throws SQLException {

        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande c = new Commande(rst.getInt("id")
                    , rst.getInt("numero")
                    , rst.getDate("date_commande")
                    , rst.getDate("date_livraison")
                    , rst.getString("livreur")
                    , rst.getInt("totale"));
            Commandes.add(c);
        }
        return Commandes;
    }
     
   
  
     public void SupprimerCommande(Commande c) throws SQLException {

        String req = "DELETE FROM commande WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     
    
   public void supp2(Commande c) throws SQLException {

        String req = "DELETE FROM commande WHERE id ="+c.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
    
    
    
}
