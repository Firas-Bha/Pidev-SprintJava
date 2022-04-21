/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author firas
 */
public interface IServiceReservation <T> {
    
    public void ajouterReservation (T t) ; // prendre un objet de type t 
    public void modifierReservation (T p,int id);
    public void supprimerReservation(int id);
    public List<T> recupererReservation ();
    
}
