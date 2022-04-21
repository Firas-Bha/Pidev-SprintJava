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
public interface IServiceEvenement <T> {
    public void ajouterEvenement (T t) ; // prendre un objet de type t 
    public void modifierEvenement (int id, String nom,int capacite,String date ,String description,String image,String adresse);
    public void supprimerEvenement(int id);
    public List<T> recupererEvenement ();
}
