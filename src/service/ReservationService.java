/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import com.google.common.base.Joiner;
import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.util.TextUtils;
import util.MyDB;

/**
 *
 * @author firas
 */
public class ReservationService implements IServiceReservation <Reservation>  {
   
    Connection cnx;

    public ReservationService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouterReservation(Reservation t) {
       try {
            String req = "insert into reservation (date_res,event_id,Nom,nbr_personnes,email,capacite)"
                    +"values('"+t.getDate_res()+"','"+t.getEvent_id()+"','"+t.getNom()+"','"+t.getNbr_personnes()+"','"+t.getEmail()+"','"+t.getCapacite()+"')";
            Statement st = cnx.createStatement(); //yhez les données ou hothom fel BD/ pour les requetes statiques
            st.executeUpdate(req);
            System.out.println("Reservation ajoutée avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierReservation( Reservation p,int id) {
  try{      
        String requete3 = "update reservation set date_res=?,event_id =?,nbr_personnes=?,email=? where id=?";
                    
            PreparedStatement pst=cnx.prepareStatement(requete3);
        pst.setString(1, p.getDate_res());
        pst.setInt(2, p.getEvent_id());
        pst.setInt(3, p.getNbr_personnes());
        pst.setString(4, p.getEmail());
         pst.setInt(5, p.getId());
        pst.executeUpdate();
            System.out.println("Réservation modifié avec succès");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }
    

    @Override
    public void supprimerReservation(int id) {
        try {
      String req = "DELETE FROM reservation WHERE id='"+id+"'"; 
       
            PreparedStatement ps = cnx.prepareStatement(req);// pour les requetes dynamiques
             ps.executeUpdate();//modfication dans la base de données
            System.out.println(" Réservation supprimé avec succées");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> recupererReservation() {
       List <Reservation> reservation = new ArrayList<>();//list interface array list classe
        try {
             String req = "SELECT t.id,t.date_res,t.event_id,t.nbr_personnes,t.email,t1.Nom,t1.capacite FROM reservation t  join evenement t1  on t.event_id=t1.id ";
             
             Statement st=cnx.createStatement();
             ResultSet rs = st.executeQuery(req);  // Pour bien structurer les données
             
             while(rs.next()){
                 Reservation t1 = new Reservation();
                 t1.setId(rs.getInt(1));
                 t1.setDate_res(rs.getString(2));
                 t1.setEvent_id( rs.getInt(3));
                 t1.setNbr_personnes(rs.getInt(4));   
                 t1.setEmail(rs.getString(5)); 
                 t1.setNom(rs.getString("Nom"));
                 System.out.println(t1.getNom());
                 reservation.add(t1);
                 t1.setCapacite(rs.getInt(7)); 
             }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
     return reservation;
    }
    
     public ObservableList<Reservation> getReservationliste() throws SQLException {
           
        ObservableList<Reservation> Reservationliste = FXCollections.observableArrayList();
        
         List <Reservation> id = new ArrayList<>(); 
        Statement stm = cnx.createStatement();
        String query ="SELECT t.id,t.date_res,t.event_id,t.nbr_personnes,t.email,t1.Nom FROM reservation t  join evenement t1  on t.event_id=t1.id ";

        ResultSet rs;
        rs = stm.executeQuery(query);
        Reservation Reservation;
        while (rs.next()) {
            Reservation = new  Reservation (rs.getInt("id"),rs.getString("Nom"), rs.getString("date_res"), rs.getInt("nbr_personnes"), rs.getString("email")); 
            //System.out.println(events);
            
            Reservationliste.add(Reservation);

        }
        return  Reservationliste;

    }
     
       public List<String> listidEvRes() throws SQLException {
        List<String> idr = new ArrayList<>();
         String Nom="";
        Statement stm = cnx.createStatement();
        String query = "select distinct event_id,t1.Nom from reservation t join evenement t1 on t.event_id=t1.id";
        ResultSet rs;
        rs = stm.executeQuery(query);
        
        while (rs.next()) {
     // = new  Reservation (rs.getInt("date_res"),rs.getString("Nom"), rs.getString("date_res"), rs.getInt("nbr_personnes"), rs.getString("email")); 

               idr.add(rs.getString("event_id"));
               idr.add(rs.getString("Nom"));
            
           

        }
        return idr;
     
    }
       
       
       public Map<String, Integer> NbResByEv() {
        Map<String, Integer> mp =new HashMap<>();
        try {
           
            Statement stm = cnx.createStatement();
            ResultSet rs;
            String query="SELECT count(*) as nbRes, evenement  FROM  reservation group by evenement  ";
            rs=stm.executeQuery(query);
            while(rs.next()){
               
                mp.put(rs.getString("evenement"),rs.getInt("nbRes"));
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage  ");
        }
        return mp ;
    }
       
        public List<String> sommePersonne(int id) throws SQLException {
         ObservableList<Reservation> Reservationliste = FXCollections.observableArrayList();
        List<String>  reservation = new ArrayList<>();
        StringBuilder strbul = new StringBuilder();
        String query = "select distinct sum(nbr_personnes),capacite from reservation  where event_id='"+id+"'";
        PreparedStatement stm = cnx.prepareStatement(query);
        ResultSet rs;
        rs = stm.executeQuery(query);
        
        while (rs.next()) {
     // = new  Reservation (rs.getInt("datde_res"),rs.getString("Nom"), rs.getString("date_res"), rs.getInt("nbr_personnes"), rs.getString("email")); 

               //idp.add(rs.getString("event_id"));
               Reservation t1 = new Reservation();
               reservation.add(rs.getString("sum(nbr_personnes)"));
               // t1.setCapacite(rs.getInt(2)); 
              // reservation.setNbr_personnes(rs.getInt(1));
               // reservation.add(t1);
              // t1.setNbPerDispo(rs.getInt(query));   
           
        }
       
        
        return reservation;
    
    }
        
        
  
    
}