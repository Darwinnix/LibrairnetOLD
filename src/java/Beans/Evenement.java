package Beans;

//import static Outils.Singleton.getInstance;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Vector;
import javax.swing.JOptionPane;

public class Evenement implements Serializable{

    private int eveId;
    private String eveIntitule;
    private Date eveDebut;
    private Date eveFin;
    private String eveImage;
    private String eveDescription;
    private float eveRemise;

    //constructeurs
    public Evenement() {
    }

//    public Evenement(int eveId) {
//        this.eveId = eveId;
//        getEvenement(eveId);
//    }

    public Evenement(String eveIntitule, Date eveDebut, Date eveFin, String eveImage, String eveDescription, float eveRemise) {
        this.eveIntitule = eveIntitule;
        this.eveDebut = eveDebut;
        this.eveFin = eveFin;
        this.eveDescription = eveDescription;
        this.eveRemise = eveRemise;
    }

    
    
    public Evenement(int eveId, String eveIntitule, Date eveDebut, Date eveFin, String eveImage, String eveDescription, float eveRemise) {
        this.eveId = eveId;
        this.eveIntitule = eveIntitule;
        this.eveDebut = eveDebut;
        this.eveFin = eveFin;
        this.eveImage = eveImage;
        this.eveDescription = eveDescription;
        this.eveRemise = eveRemise;
    }

    //Methodes
    
    
    
//    public void getEvenement(int eveId) {
//        String query = "SELECT * FROM Evenement WHERE adrId = '" + eveId + "'";
//        Connection co = null;
//        try {
//            co = getInstance().getConnection();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            if (rs.next()) {
//                setEveIntitule(rs.getString("eveIntitule"));
//                setEveDebut(rs.getDate("eveDebut"));
//                setEveFin(rs.getDate("eveFin"));
//                setEveDescription(rs.getString("eveDescription"));
//                setEveRemise(rs.getFloat("eveRemise"));
//            }
//            st.close();
//            rs.close();
//            co.close();
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//    }
//
//    public Vector getVectorEvenement(Date maDate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Vector<Evenement> v = new Vector<Evenement>();
//        String query = "SELECT * FROM Evenement WHERE eveDebut <= '" + sdf.format(maDate) + "' AND eveFin >= '" + sdf.format(maDate) + "'";
//        Connection co = null;
//        try {
//            co = getInstance().getConnection();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                v.add(new Evenement(
//                        rs.getInt("eveId"),
//                        rs.getString("eveIntitule"),
//                        rs.getDate("eveDebut"),
//                        rs.getDate("eveFin"),
//                        rs.getString("eveDescription"),
//                        rs.getFloat("eveRemise")));
//            }
//            st.close();
//            rs.close();
//            co.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//        return v;
//    }
//    
//    public boolean getParticipation(int eveId, String livIsbn) {
//        int result = 0;
//        String query = "SELECT * FROM Participation WHERE eveId = " + eveId + " and livIsbn = '" + livIsbn + "'";
//        Connection co = null;
//        try {
//            co = getInstance().getConnection();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                result = rs.getInt("eveId");
//            }
//            st.close();
//            rs.close();
//            co.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//        if (result == 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    //accesseurs
    public int getEveId() {
        return eveId;
    }

    public void setEveId(int eveId) {
        this.eveId = eveId;
    }

    public String getEveIntitule() {
        return eveIntitule;
    }

    public void setEveIntitule(String eveIntitule) {
        this.eveIntitule = eveIntitule;
    }

    public Date getEveDebut() {
        return eveDebut;
    }

    public void setEveDebut(Date eveDebut) {
        this.eveDebut = eveDebut;
    }

    public Date getEveFin() {
        return eveFin;
    }

    public void setEveFin(Date eveFin) {
        this.eveFin = eveFin;
    }

    public String getEveDescription() {
        return eveDescription;
    }

    public void setEveDescription(String eveDescription) {
        this.eveDescription = eveDescription;
    }

    public float getEveRemise() {
        return eveRemise;
    }

    public void setEveRemise(float eveRemise) {
        this.eveRemise = eveRemise;
    }

    //overrides
    @Override
    public String toString() {
        return eveIntitule + " : du " + eveDebut + " au " + eveFin + " : - " + eveRemise + "%";
    }

}
