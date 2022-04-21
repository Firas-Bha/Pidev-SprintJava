/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author firas
 */
public class Evenement {
    public static String pathfile; 
    public static String filename="";
    public static  Image imageE ;
    
     private int id;
     private String Nom;
     private int Capacite;
     private String Date;
     private String Description;
     private  String image;
     private String Adresse;
     
      public Evenement( String Nom, int Capacite,String  Date, String Description, String image, String Adresse) {
        
        this.Nom = Nom;
        this.Capacite = Capacite;
        this.Date = Date;
        this.Description = Description;
        this.image = image;
        this.Adresse = Adresse;
    }

    public Evenement(int id, String Nom, int Capacite, String  Date, String Description, String image, String Adresse) {
        this.id = id;
        this.Nom = Nom;
        this.Capacite = Capacite;
        this.Date = Date;
        this.Description = Description;
        this.image = image;
        this.Adresse = Adresse;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getCapacite() {
        return Capacite;
    }

    public void setCapacite(int Capacite) {
        this.Capacite = Capacite;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Nom=" + Nom + ", Capacite=" + Capacite + ", Date=" + Date + ", Description=" + Description + ", image=" + image + ", Adresse=" + Adresse + '}';
    }

    public Image getImageE() {
        return imageE;
    }

    public void setImageE(Image imageE) {
        this.imageE = imageE;
    }
    
    
  
}
