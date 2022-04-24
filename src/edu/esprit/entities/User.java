package edu.esprit.entities;

import java.util.Date;

public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String cp;
    private String tel;
    private String img;
    private String role_id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    private String etat;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    private String login;
    private String password;
    private String image;

    public User(int id) {
        this.id = id;
    }

    public User(int id,String tel, String nom, String prenom, String email, String adresse, String ville, String cp, String login, String password, String image, String role_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.tel=tel;
        this.login = login;
        this.password = password;
        this.image = image;
    }

    public User(String tel,String nom, String prenom, String email, String adresse, String ville, String cp, String login, String password, String image,String role_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.tel=tel;
        this.login = login;
        this.password = password;
        this.image = image;
        this.role_id = role_id;
    }

    public User() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        
        String role="";
        String etat1="";
        if (this.role_id=="1")
        {
            role="Admin";
        }
        else if (this.role_id=="2")
        {
            role="Gérant Salle de sport";
        }
         else if (this.role_id=="3")
        {
            role="Coach privé";
        }
         else if (this.role_id=="4")
        {
            role="Nutritionniste";
        }
         else if (this.role_id=="5")
        {
            role="Apollonien";
        }
        if(this.etat=="1")
        {
            etat1="Activer";
        }else
         etat1="Désactiver";
        
        
        return "User{id=" +id+ ",nom=" + nom + ", prenom=" + prenom+ ", etat=" + etat1 + '}';
    }
    
    
        public String showuser() {
        
        String role="";
        String etat1="";
    
        if(etat=="1")
        {
            etat1="Activer";
        }else
         etat1="Désactiver";
        
        
        return "User{id=" +id+ ",nom=" + nom + ", prenom=" + prenom+ ", etat=" + etat1 + '}';
    }
    
    
    

    public void setImage(String image) {
        this.image = image;
    }
    
}