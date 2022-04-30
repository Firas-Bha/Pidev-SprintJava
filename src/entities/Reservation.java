/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author firas
 */
public class Reservation {
    private int id;
    private String Nom;
    private String date_res;
    private int event_id;
    private int nbr_personnes;
    private String email;
    private int capacite;
    private int nbPerDispo;


   

    public Reservation() {
    }

    public Reservation( String date_res,int event_id, int nbr_personnes,String email) {
      
        this.date_res = date_res;
         this.event_id=event_id; 
        this.nbr_personnes = nbr_personnes;
        this.email = email;
    }
     public Reservation(int id, String Nom,String date_res, int nbr_personnes,String email) {
        this.id = id;
        this.Nom = Nom;
        this.date_res = date_res;
        this.event_id = event_id;
        this.nbr_personnes = nbr_personnes;
        this.email = email;
    }
       public Reservation(String date_res ,String Nom, int nbr_personnes,String email) {
        this.Nom=Nom;
         
        this.date_res = date_res;
        this.nbr_personnes = nbr_personnes;
        this.email = email;
    }
       public Reservation(int Event_id ,String Nom) {
        this.Nom=Nom;
        this.event_id = event_id;
    }
     

    public int getId() {
        return id;
    }

    public String getDate_res() {
        return date_res;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }
    
     public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
     public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
     public int getNbPerDispo() {
        return nbPerDispo;
    }

    public void setNbPerDispo(int nbPerDispo) {
        this.nbPerDispo = nbPerDispo;
    }
  
}
