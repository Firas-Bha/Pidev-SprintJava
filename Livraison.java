/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class Livraison {
     private int id;
     private String num_l;
      private String nom_livreur;
       private String tel_livreur;
        private String date_livraison; 

    public Livraison() {
    }

    public Livraison(String num_l, String nom_livreur, String tel_livreur, String date_livraison) {
        this.num_l = num_l;
        this.nom_livreur = nom_livreur;
        this.tel_livreur = tel_livreur;
        this.date_livraison = date_livraison;
    }

    public Livraison(int id, String num_l, String nom_livreur, String tel_livreur, String date_livraison) {
        this.id = id;
        this.num_l = num_l;
        this.nom_livreur = nom_livreur;
        this.tel_livreur = tel_livreur;
        this.date_livraison = date_livraison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_l() {
        return num_l;
    }

    public void setNum_l(String num_l) {
        this.num_l = num_l;
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    public void setNom_livreur(String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    public String getTel_livreur() {
        return tel_livreur;
    }

    public void setTel_livreur(String tel_livreur) {
        this.tel_livreur = tel_livreur;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", num_l=" + num_l + ", nom_livreur=" + nom_livreur + ", tel_livreur=" + tel_livreur + ", date_livraison=" + date_livraison + '}';
    }
        
}
