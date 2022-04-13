/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Materiel;
import edu.esprit.entities.Salle;
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
 * @author hp
 */
public class MaterielService {
     Connection connexion;   
  public MaterielService() {
        connexion = Connexion.getInstance().getCnx();
    }
 

    public void modifierMateriel(Materiel e) throws SQLException, NoSuchAlgorithmException {
       
        String req = "UPDATE materiel SET "
                + " salle_id_id ='"+e.getSalle_id_id()+"'"
                + ", type='"+e.getType()+"'"
                + ", nom='"+e.getNom()+"'"
             
                + ", code='"+e.getCode()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
          public void ajouterMateriel(Materiel e) throws SQLException {
        String req = "INSERT INTO `materiel` (`salle_id_id`,`type`,`nom`,`code`) "
                + "VALUES (?,?,?,?)";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, e.getSalle_id_id());
        ps.setString(2, e.getType());
       
        ps.setString(3, e.getNom());
           ps.setString(4, e.getCode());
         

        ps.executeUpdate();
    }

 
     public List<Materiel> AfficherAllMateriel() throws SQLException {

        List<Materiel> Materiels = new ArrayList<>();
        String req = "select * from materiel order by salle_id_id  ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Materiel e = new Materiel(rst.getInt("id")
                    , rst.getInt("salle_id_id")
                    , rst.getString("type")
                    , rst.getString("nom")
                     , rst.getString("code")
                   );
            Materiels.add(e);
        }
        return Materiels;
    }
     
   
  
     public void SupprimerMateriel(Materiel e) throws SQLException {

        String req = "DELETE FROM materiel WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     
    
   public void supp2(Materiel m) throws SQLException {

        String req = "DELETE FROM materiel WHERE id ="+m.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
}
