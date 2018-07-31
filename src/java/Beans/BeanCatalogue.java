package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;

public class BeanCatalogue implements Serializable{

    HashMap<String, Livre> catalogue = null;
    //private ArrayList<String> catalogue = new ArrayList();

    
    
    public BeanCatalogue()  {
        this.catalogue = new HashMap();
    }

    public Collection getCatalogue(Connection co) {
        try {
            String request = "SELECT * FROM Livre";
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                catalogue.put(rs.getString("livIsbn"),
                        new Livre(rs.getString("livIsbn"),
                                rs.getDouble("livPrix"),
                                rs.getInt("tvaCode"),
                                rs.getString("livTitre"),
                                rs.getString("livSousTitre"),
                                rs.getString("livCouverture"),
                                rs.getDate("livDateSortie"),
                                rs.getString("livResume"),
                                rs.getInt("livStock"),
                                rs.getInt("livStatus"),
                                rs.getInt("autId"),
                                rs.getInt("ediId")
                        ));
            }
            st.close();
            rs.close();
            co.close();
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL: " + ex.getMessage());
        }
        return catalogue.values();
    }
    
     public Livre getLivre(String isbn/*, BeanCatalogue cat*/){
        Livre leLivre = this.catalogue.get(isbn);
        //Livre leLivre = cat.getCatalogue().get(isbn);
        return leLivre;
    }

    public HashMap<String, Livre> getCatalogue() {
        return catalogue;
    }
     
     
    

}
