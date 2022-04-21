/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import app.controllers.GestionEvController;
import entities.Evenement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import util.MyDB;

/**
 *
 * @author firas
 */
public class EvenementService  implements IServiceEvenement <Evenement> {
  Connection cnx;
  String Filename;

    public EvenementService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouterEvenement(Evenement t) {
       
         try {
            String req = "insert into Evenement (nom,capacite,date,description,image,adresse)"
                    +"values('"+t.getNom()+"','"+t.getCapacite()+"','"+t.getDate()+"','"+t.getDescription()+"','"+t.getImage()+"','"+t.getAdresse()+"')";
            Statement st = cnx.createStatement(); //yhez les données ou hothom fel BD/ pour les requetes statiques
            st.executeUpdate(req);
            System.out.println("Evenement ajoutée avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierEvenement(int id, String nom,int capacite,String date ,String description,String image,String adresse) {
        try {
              Statement st = null;
      String req = "UPDATE Evenement SET nom='"+nom+"',capacite="+capacite+",date='"+date+"',description='"+description+"',image='"+image+"',adresse='"+adresse+"' WHERE id='"+id+"'";
       
           st = cnx.createStatement();// pour les requetes dynamiques
             st.executeUpdate(req);//modfication dans la base de données
            System.out.println("Evenement modifiée avec succées");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Evenement> recupererEvenement() {
          List <Evenement> evenement = new ArrayList<>();//list interface array list classe
        try {
             String req = "Select * from evenement";
             Statement st=cnx.createStatement();
             ResultSet rs = st.executeQuery(req);  // Pour bien structurer les données
             
             while(rs.next()){
                 Evenement ev = new Evenement();
                 ev.setId(rs.getInt(1));
                 ev.setNom(rs.getString("nom"));
                 ev.setCapacite(rs.getInt("capacite"));
                 ev.setDate(rs.getString("date"));
                 ev.setDescription(rs.getString("description"));
                 ev.setImage(rs.getString("image"));
                 ev.setAdresse(rs.getString("adresse"));
                   FileInputStream inputStream;
        try  {
          
            inputStream = new FileInputStream("C:/Users/firas/OneDrive/Desktop/ProjetDev/Intégration/public/uploads/" +rs.getString("image"));
            
         //   inputStream = new FileInputStream("src/voyagep.png");
             Image image= new Image(inputStream);
          //   imgViewV = new ImageView(image);
                   ev.setImageE(image);
                     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEvController.class.getName()).log(Level.SEVERE, null, ex);
        }
             evenement.add(ev);    
                  
             }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
       
     return evenement;
    }

    @Override
    public void supprimerEvenement(int id) {
        try {
      String req = "DELETE FROM evenement WHERE id='"+id+"'"; 
       
            PreparedStatement ps = cnx.prepareStatement(req);// pour les requetes dynamiques
             ps.executeUpdate();//modfication dans la base de données
            System.out.println("Evenement supprimé avec succées");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Evenement> getEvenementliste() throws SQLException {
           
        ObservableList<Evenement> Evenementliste = FXCollections.observableArrayList();
        
         List <Evenement> id = new ArrayList<>(); 
        Statement stm = cnx.createStatement();
        String query = "select * from evenement";

        ResultSet rs;
        rs = stm.executeQuery(query);
        Evenement Evenement;
        while (rs.next()) {
            Evenement= new  Evenement (rs.getInt("id"), rs.getString("nom"), rs.getInt("capacite"), rs.getString("date"), rs.getString("description"), rs.getString("image"), rs.getString("adresse")); 
            //System.out.println(events);
            
            Evenementliste.add(Evenement);

        }
        
        return  Evenementliste;

    }
    
}
