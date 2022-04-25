/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Categorie;
import edu.esprit.tools.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CategorieService() {
         cnx = Connexion.getInstance().getCnx();
    }
    
    public void ajouterCategorie(Categorie c) {
        String query = "insert into categorie(id,type) values(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, (int) c.getId());
            ste.setString(2, c.getType());

            ste.executeUpdate();
            System.out.println("Categorie Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Categorie> afficherCategorie() {
        List<Categorie> Categories = new ArrayList<>();
        String sql = "select * from categorie";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                
                c.setType(rs.getString(2));
                c.setType(rs.getString("type"));
                Categories.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Categories;

    }

    public void modifierCategorie(Categorie c) {
        try {
            String req = "UPDATE categorie SET type= ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(2, c.getType());

            ps.setInt(1, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }
 
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

}
