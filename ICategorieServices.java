/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.util.List;
import entities.Categorie;
/**
 *
 * @author MSI
 */
public interface ICategorieServices {
       public void AjouterCategorie(Categorie c);
public void SupprimerCategorie(String nom_c);
//public void ModifierTrip(Trip p);
         public List<Categorie> afficher();
        public void ModifierCategorie(Categorie c, String nom_c);
        public int modifier (Categorie c);
}
