/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class Connexion {
    public final String url  ="jdbc:mysql://localhost:3306/apollon";
    public final String user ="root";
    public final String pwd = "";
    private Connection cnx;
    public static Connexion ct;
    
    
    private Connexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public static Connexion getInstance(){
        if(ct==null)
            ct=new Connexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
