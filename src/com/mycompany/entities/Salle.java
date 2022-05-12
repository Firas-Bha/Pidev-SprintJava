/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class Salle {
    private int Id,CapaciteS,nbCoursMaxS;
    private float Surface;
    private String NomS,description;

    public Salle() {
    }
    
    public Salle(int Id, int CapaciteS, int nbCoursMaxS, float Surface, String NomS, String description) {
        this.Id = Id;
        this.CapaciteS = CapaciteS;
        this.nbCoursMaxS = nbCoursMaxS;
        this.description = description;
        this.Surface = Surface;
        this.NomS = NomS;
    }

    public Salle(int CapaciteS, int nbCoursMaxS, float Surface, String NomS, String description) {
        this.CapaciteS = CapaciteS;
        this.nbCoursMaxS = nbCoursMaxS;
        this.description = description;
        this.Surface = Surface;
        this.NomS = NomS;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCapaciteS() {
        return CapaciteS;
    }

    public void setCapaciteS(int CapaciteS) {
        this.CapaciteS = CapaciteS;
    }

    public int getNbCoursMaxS() {
        return nbCoursMaxS;
    }

    public void setNbCoursMaxS(int nbCoursMaxS) {
        this.nbCoursMaxS = nbCoursMaxS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSurface() {
        return Surface;
    }

    public void setSurface(float Surface) {
        this.Surface = Surface;
    }

    public String getNomS() {
        return NomS;
    }

    public void setNomS(String NomS) {
        this.NomS = NomS;
    }
    
    
    
}
