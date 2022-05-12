/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Cours {
    private int Id, salle, SerieC;

    private String TypeC, NiveauC, ImageC,couleur; 
    private String DateC, HeureDebutC, DureeC;

    public Cours(int Id, int salle, int SerieC, String TypeC, String NiveauC, String ImageC, String couleur, String DateC, String HeureDebutC, String DureeC) {
        this.Id = Id;
        this.salle = salle;
        this.SerieC = SerieC;
        this.TypeC = TypeC;
        this.NiveauC = NiveauC;
        this.ImageC = ImageC;
        this.couleur = couleur;
        this.DateC = DateC;
        this.HeureDebutC = HeureDebutC;
        this.DureeC = DureeC;
    }

    public Cours() {
    }

    
    public Cours(int salle, int SerieC, String TypeC, String NiveauC, String ImageC, String couleur, String DateC, String HeureDebutC, String DureeC) {
        this.salle = salle;
        this.SerieC = SerieC;
        this.TypeC = TypeC;
        this.NiveauC = NiveauC;
        this.ImageC = ImageC;
        this.couleur = couleur;
        this.DateC = DateC;
        this.HeureDebutC = HeureDebutC;
        this.DureeC = DureeC;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public int getSerieC() {
        return SerieC;
    }

    public void setSerieC(int SerieC) {
        this.SerieC = SerieC;
    }

    public String getTypeC() {
        return TypeC;
    }

    public void setTypeC(String TypeC) {
        this.TypeC = TypeC;
    }

    public String getNiveauC() {
        return NiveauC;
    }

    public void setNiveauC(String NiveauC) {
        this.NiveauC = NiveauC;
    }

    public String getImageC() {
        return ImageC;
    }

    public void setImageC(String ImageC) {
        this.ImageC = ImageC;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDateC() {
        return DateC;
    }

    public void setDateC(String DateC) {
        this.DateC = DateC;
    }

    public String getHeureDebutC() {
        return HeureDebutC;
    }

    public void setHeureDebutC(String HeureDebutC) {
        this.HeureDebutC = HeureDebutC;
    }

    public String getDureeC() {
        return DureeC;
    }

    public void setDureeC(String DureeC) {
        this.DureeC = DureeC;
    }
    
    
}
