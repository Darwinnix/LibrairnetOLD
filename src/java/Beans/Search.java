/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.Connexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi314
 */
public class Search implements Serializable{

    public static int ALL = 1;
    public static int THEME = 2;
    public static int MOTCLE = 3;
    public static int AUTEUR = 4;
    public static int ISBN = 5;
    public static int TITRE = 6;
    

    //ArrayList<Livre> search;
    HashMap<String, Livre> search;

    public Search() {
        search = new HashMap();
    }

    public void add(Livre l) {
        search.put(l.getIsbn(), l);
    }

    public void remove(Livre l) {
        search.remove(l.getIsbn());
    }

    public Collection<Livre> getSearch() {
        return search.values();
    }

    public void setSearch(int param, Connexion con) {

        
        String query = "SELECT livIsbn FROM Livre";
        if (param == ALL) {
            query = "SELECT livIsbn FROM Livre";
        }
        
        
        Connection c = con.getConnection();
        
        try {
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                this.add(new Livre(rs.getString("livIsbn"), con));
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("SEARCH Connection : "+ ex.getMessage()+" / "+ex.getSQLState()+" / "+ex.getErrorCode());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("SEARCH close Connection : "+ ex.getMessage()+" / "+ex.getSQLState()+" / "+ex.getErrorCode());
            }
        }

    }
    
    public void setSearch(int param, Connexion con,String recherche) {
        String query;
        
        if (param == AUTEUR) {
            query = "SELECT liv.livIsbn FROM Livre liv JOIN Auteur aut "
                    + "ON liv.autId = aut.autId WHERE ";
            query = myQuery(query, " aut.autNom aut.autPrenom", recherche);
            query += " GROUP BY liv.livIsbn";
            //System.out.println(query);
        } else if (param == THEME) {
            query = "SELECT liv.livIsbn FROM Livre liv JOIN Appartenance app ON liv.livIsbn = app.livIsbn "
                    + "JOIN SousTheme sou ON app.souId = sou.souId "
                    + "JOIN Theme the ON sou.theId = the.theId WHERE ";
            query = myQuery(query, " the.theNom", recherche);
            query += " GROUP BY liv.livIsbn";
            //System.out.println(query);
        } else if (param == TITRE) {
            query = "SELECT liv.livIsbn FROM Livre liv WHERE ";
            query = myQuery(query, "liv.livTitre liv.livSousTitre", recherche);
            
            //System.out.println(query);
        } else if (param == MOTCLE) {
            query = "SELECT liv.livIsbn FROM Livre liv "
                    + "JOIN Caracteristique car "
                    + "ON liv.livIsbn = car.livIsbn "
                    + "JOIN MotCle mot "
                    + "ON car.motId = mot.motId WHERE ";
            query = myQuery(query, "mot.motLibele", recherche);
            query += " GROUP BY liv.livIsbn";
            //System.out.println(query);
        } else if (param == ISBN) {
            query = "SELECT liv.livIsbn FROM Livre liv WHERE ";
            query = myQuery(query, "liv.livIsbn", recherche);
            
            //System.out.println(query);
        } else {
            query = "SELECT liv.livIsbn FROM Livre liv "
                    + "FULL OUTER JOIN Appartenance app "
                    + "ON liv.livIsbn = app.livIsbn "
                    + "FULL OUTER JOIN SousTheme sou "
                    + "ON app.souId = sou.souId "
                    + "FULL OUTER JOIN Theme the "
                    + "ON sou.theId = the.theId "
                    + "FULL OUTER JOIN Auteur aut "
                    + "ON liv.autId = aut.autId "
                    + "FULL OUTER JOIN Caracteristique car "
                    + "ON liv.livIsbn = car.livIsbn "
                    + "FULL OUTER JOIN MotCle mot "
                    + "ON car.motId = mot.motId "
                    + "WHERE ";
            query = myQuery(query, " aut.autNom aut.autPrenom the.theNom liv.livTitre liv.livSousTitre mot.motLibele liv.livIsbn", recherche);
            query += " GROUP BY liv.livIsbn";
            //System.out.println(query);
        }
         getFromBase(query,con);
    }

    public void getFromBase(String query,Connexion con) {
        
        Connection c = con.getConnection();

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                this.add(new Livre(rs.getString("livIsbn"),con));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     *
     * @param debutQuery
     * @param champs "On met les les champs rechecher avec un espace genre ->
     * 'Auteur.nom Auteur.prenom Auteur.commentaire' Il va les spliter pour la
     * recherche
     * @param recherches
     * @return
     */
    public String myQuery(String debutQuery, String champs, String recherches) {
        String query;
        StringBuffer sb = new StringBuffer(debutQuery);
        for (String champ : champs.trim().split(" ")) {
            //sb.append(champ+" ");
            for (String recherche : recherches.trim().split(" ")) {
                sb.append(champ + " LIKE '%" + recherche + "%' OR ");
            }
        }
        //sb.length()
        sb.delete(sb.length() - 3, sb.length());
        query = sb.toString();
        return query;
    }

}
