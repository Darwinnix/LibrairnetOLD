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

    private String commentaire;
    private int note;

    public Critique(String commentaire) {
        this.setCommentaire(commentaire);
        this.setNote(0);
    }

    public Critique(int note) {
        this.setCommentaire("");
        this.setNote(note);
    }

    public Critique(String commentaire, int note) {
        setCommentaire(commentaire);
        setNote(note);
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        if (commentaire == null) {
            this.commentaire = "";
        } else {
            this.commentaire = commentaire;
        }
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        if (note > 5) {
            this.note = 5;
        } else if (note < 0) {
            this.note = 0;
        } else {
            this.note = note;
        }

    }

}
