/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Yavuz
 */
public class SousTheme implements Serializable{
    
    private int id;
    private String nom;
    private int idtheme;

    public SousTheme(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public SousTheme(int id, String nom, int idtheme) {
        this.id = id;
        this.nom = nom;
        this.idtheme = idtheme;
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
        final SousTheme other = (SousTheme) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
