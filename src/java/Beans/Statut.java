
package Beans;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Statut implements Serializable{
    private int staCode;
    private String staIntitule;
    
    
    
    //constructeurs

    public Statut() {
    }

    public Statut(int staCode,Connection c) {
        this.staCode = staCode;
        getStatut(staCode,c);
    }

    public Statut(int staCode, String staIntitule) {
        this.staCode = staCode;
        this.staIntitule = staIntitule;
    }
    
    //methodes
    public void getStatut(int staCode,Connection c){
        String query = "SELECT * FROM Statut WHERE staCode = '"+staCode+"'";
                      
        try {
            Statement st = c.createStatement();           
            ResultSet rs = st.executeQuery(query);           
            if (rs.next()) {                                              
                setStaIntitule(rs.getString("staIntitule"));              
            }
            st.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(AjouteLivre.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Statut.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //accesseurs

    public int getStaCode() {
        return staCode;
    }

    public void setStaCode(int staCode) {
        this.staCode = staCode;
    }

    public String getStaIntitule() {
        return staIntitule;
    }

    public void setStaIntitule(String staIntitule) {
        this.staIntitule = staIntitule;
    }
    
    //overrides

    @Override
    public String toString() {
        return staIntitule ;
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
        final Statut other = (Statut) obj;
        if (this.staCode != other.staCode) {
            return false;
        }
        if (!Objects.equals(this.staIntitule, other.staIntitule)) {
            return false;
        }
        return true;
    }
    
    
}
