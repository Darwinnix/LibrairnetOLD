/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author cdi314
 */
public class Critique implements Serializable {

    private String nom;
    private String commentaire;
    private Integer note;

    private boolean videCommentaire = true;
    private boolean videNote = true;

    public Critique(String commentaire) {
        this.setCommentaire(commentaire);
        this.videNote = false;
    }

    public Critique(int note) {
        this.setNote(note);
        this.videCommentaire = false;
    }

    public Critique(String commentaire, int note, String nom) {
        setCommentaire(commentaire);
        setNote(note);
        setNom(nom);
        this.videNote = false;
        this.videCommentaire = false;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
        this.videCommentaire = false;
    }

    public int getNote() {
        return note;
    }

    public void setNote(Integer note) {
            this.note = note;
            this.videNote = false;
    }

    public boolean isVideCommentaire() {
        return videCommentaire;
    }

    public void setVideCommentaire(boolean videCommentaire) {
        this.videCommentaire = videCommentaire;
    }

    public boolean getVideCommentaire(){
        return this.videCommentaire;
    }
    
    public boolean isVideNote() {
        return videNote;
    }

    public void setVideNote(boolean videNote) {
        this.videNote = videNote;
    }

    
}
