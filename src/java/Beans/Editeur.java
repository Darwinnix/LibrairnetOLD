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
import java.util.Objects;

/**
 *
 * @author cdi314
 */
public class Editeur implements Serializable {

    private int id;
    private String nom;
    private Integer statut;

    public Editeur() {

    }

    public Editeur(int id, String nom, Integer statut) {
        this.id = id;
        this.nom = nom;
        this.statut = statut;
    }

    public Editeur(String nom, Integer statut) {
        this.nom = nom;
        this.statut = statut;
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

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
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
        final Editeur other = (Editeur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public void ajouterBase(Connection c) {

        String query = "INSERT INTO Editeur(ediNom,ediStatut) VALUES(?,?)";

        try {
            PreparedStatement st = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, nom);
            st.setInt(2, statut);

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println("Oops sql insert auteur: " + ex.getMessage());
        }

    }

}
