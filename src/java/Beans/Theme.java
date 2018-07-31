/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Yavuz
 */
public class Theme implements Serializable{
    
    private int id;
    private String nom;
    
    //private Vector<SousTheme> sousThemes ;

    public Theme() {
    }
    
    public Theme(int id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
 
    
    
    @Override
    public String toString() {
        return nom;
    }
    
    
    
    
}
