/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yavuz
 */
public class Auteur implements Serializable{
    private int id;
    private String nom;
    private String prenom;
    private String commentaire;

    public Auteur(){
        
    }
    
    public Auteur(int id, String nom, String prenom, String commentaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.commentaire = commentaire;
    }

    public Auteur( String nom, String prenom, String commentaire) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.commentaire = commentaire;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
    public void ajouterBase(Connection c){
        
        String query = "INSERT INTO Auteur(autNom,autPrenom,autCommentaire) VALUES(?,?,?) ";
        
        try {
            PreparedStatement st = c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, nom);
            st.setString(2, prenom);
            st.setString(3, commentaire);
            
            st.executeUpdate();
            
            ResultSet rs= st.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println("Oops sql insert auteur: " + ex.getMessage());
        }
        
    }
    
    
}
