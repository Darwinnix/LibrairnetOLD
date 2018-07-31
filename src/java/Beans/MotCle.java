/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi314
 */
public class MotCle implements Serializable{
    
    private int id;
    private String motCle;

    public MotCle(int id, String motCle) {
        this.id = id;
        this.motCle = motCle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }
    
    
    
//    public void MAJDansBase(){
//        Connection c = Connexion.connection();
//        
//        String query = "UPDATE  MotCle (MotLibelle) VALUES(?)";
//        
//        try {
//            PreparedStatement ps =c.prepareStatement(query);
//            ps.setString(1, motCle.getText());
//            
//            ps.executeUpdate();
//            ps.close();
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(FenetreTheme.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Connexion.close(c);
//    }

    @Override
    public String toString() {
        return  motCle;
    }
}
