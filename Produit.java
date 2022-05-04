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
public class Produit {
      private int id;
     private String categorie_id;
      private String libelle;
       private String nom_p;
        private String nombre; 
         private int prix;
     private String reduction;
      private String date_p;
       private String marque;
        private String image; 

    public Produit() {
    }

    public Produit(String categorie_id, String libelle, String nom_p, String nombre, int prix, String reduction, String date_p, String marque, String image) {
        this.categorie_id = categorie_id;
        this.libelle = libelle;
        this.nom_p = nom_p;
        this.nombre = nombre;
        this.prix = prix;
        this.reduction = reduction;
        this.date_p = date_p;
        this.marque = marque;
        this.image = image;
    }

    public Produit(int id, String categorie_id, String libelle, String nom_p, String nombre, int prix, String reduction, String date_p, String marque, String image) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.libelle = libelle;
        this.nom_p = nom_p;
        this.nombre = nombre;
        this.prix = prix;
        this.reduction = reduction;
        this.date_p = date_p;
        this.marque = marque;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public String getDate_p() {
        return date_p;
    }

    public void setDate_p(String date_p) {
        this.date_p = date_p;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", libelle=" + libelle + ", nom_p=" + nom_p + ", nombre=" + nombre + ", prix=" + prix + ", reduction=" + reduction + ", date_p=" + date_p + ", marque=" + marque + ", image=" + image + '}';
    }
        
}
