/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.util.List;
import entities.Livraison;
/**
 *
 * @author MSI
 */
public interface ILivraisonServices {
    public void AjouterLivraison(Livraison l);
public void SupprimerLivraison(String nom_livreur);
//public void ModifierTrip(Trip p);
         public List<Livraison> afficher();
        public void ModifierLivraison(Livraison l, String nom_livreur);
        public int modifier (Livraison l);
}
