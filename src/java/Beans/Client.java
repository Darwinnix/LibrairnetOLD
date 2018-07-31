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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yavuz
 */
public class Client implements Serializable{
    
    private String login;
    private String password;
    private String nom;
    private String prenom;
    
    private boolean connected;

    public Client(String login, String password, Connexion con) {
        this.connected=false;
        this.login = login;
        this.password = password;
        this.setConnected(login, password, con);
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    public void setConnected(String login,String password,Connexion con){
        String query = "SELECT * FROM Client WHERE cliLogin = ? AND cliPwd = ?";
        
        Connection c = con.getConnection();
        
        try {
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                this.setNom(rs.getString("cliNom"));
                this.setPrenom(rs.getString("cliPrenom"));
                this.setConnected(true);
            }else{
                setConnected(false);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    
    
    
}
