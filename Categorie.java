/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author MSI
 */
public class Categorie {
   private int id;
     private String nom_c;
      private String type;
       private String sexe;
        private String categorie_order; 

    public Categorie() {
    }

    public Categorie(String nom_c, String type, String sexe, String categorie_order) {
        this.nom_c = nom_c;
        this.type = type;
        this.sexe = sexe;
        this.categorie_order = categorie_order;
    }

    public Categorie(int id, String nom_c, String type, String sexe, String categorie_order) {
        this.id = id;
        this.nom_c = nom_c;
        this.type = type;
        this.sexe = sexe;
        this.categorie_order = categorie_order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCategorie_order() {
        return categorie_order;
    }

    public void setCategorie_order(String categorie_order) {
        this.categorie_order = categorie_order;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_c=" + nom_c + ", type=" + type + ", sexe=" + sexe + ", categorie_order=" + categorie_order + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.nom_c);
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.sexe);
        hash = 17 * hash + Objects.hashCode(this.categorie_order);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_c, other.nom_c)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.categorie_order, other.categorie_order)) {
            return false;
        }
        return true;
    }

   
  
}
