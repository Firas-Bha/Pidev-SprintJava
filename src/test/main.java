/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.Evenement;
import service.EvenementService;
import util.MyDB;
import java.util.Date;

/**
 *
 * @author firas
 */
public class main {
    
     public static void main(String[] args) {
           /*
    MyDB db =MyDB.getInstance();
    MyDB db2 =MyDB.getInstance();
    System.out.println(db);
    System.out.println(db2);
            */
           
    Evenement ev = new Evenement (21,"bouhmidon",22,"STR_TO_DATE('2012-05-29','%Y-%m-%d')","ff","ff","28");
    // Evenement ev1 = new Evenement (21,"bouhmidonn",22,"('2012-05-29','%Y-%m-%d')","ff","ff","28");
    EvenementService evs = new EvenementService();
    //evs. ajouterEvenement(ev); 
   // evs.modifierEvenement(ev);
    // evs.supprimerEvenement(ev1);
    // System.out.println(evs.recupererEvenement());
    
    
     
    }
    
}
