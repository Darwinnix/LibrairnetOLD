/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Exception.LibException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.sql.Date;

/**
 *
 * @author cdi314
 */
public class Livre implements Serializable {

    private String isbn;
    private double prix;
    private int idTva;
    private String titre;
    private String sousTitre;
    private String couverture;
    private Date dateSortie;
    private String resume;
    private int stock;
    private int statut;
    private int idAuteur;
    private int idEditeur;
    private Tva taux = new Tva();
    private Evenement evt = new Evenement();
    
    private String auteur;
    private String editeur;
    private ArrayList<Critique> critiques;
    private Integer moyenne;
    private String etoile;
    
    

    //private int[] idSousTheme;
    //private Critique[] critique;
    public Livre() {

    }

    public Livre(String isbn) {
        this.isbn = isbn;
    }

    public Livre(String isbn, Connexion c) {
        this.isbn = isbn;
        this.getLivre(isbn, c);
        this.getAuteur(c);

    }

    public Livre(String isbn, double prix, String titre, String sousTitre, String couverture, Date dateSortie, String resume, int stock, int statut) {
        this.isbn = isbn;
        this.prix = prix;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.couverture = couverture;
        this.dateSortie = dateSortie;
        this.resume = resume;
        this.stock = stock;
        this.statut = statut;

    }

    public Livre(String isbn, double prix, int idTva, String titre, String sousTitre, String couverture, Date dateSortie, String resume, int stock, int statut, int idAuteur, int idEditeur) {
        this.isbn = isbn;
        this.prix = prix;
        this.idTva = idTva;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.couverture = couverture;
        this.dateSortie = dateSortie;
        this.resume = resume;
        this.stock = stock;
        this.statut = statut;
        this.idAuteur = idAuteur;
        this.idEditeur = idEditeur;

    }

    public String getIsbn()// throws LibException 
    {
//        if(isbn.trim().isEmpty()){
//            throw new LibException("ISBN est vide");
//        }else if(!isbn.matches("[0-9]{3}-[0-9]{1}-[0-9]{4}-[0-9]{4}-[0-9]{1}")){
//            throw new LibException("ISBN n'a pas le bon format");
//        }else{
        return isbn;
//        }
    }

    public void setIsbn(String isbn) {
//        if (!isbn.matches("[0-9]{3}-[0-9]{1}-[0-9]{4}-[0-9]{4}-[0-9]{1}")) {
//            throw new LibException("L'isbn");
//        }
        this.isbn = isbn;
    }

    public double getPrix() {
        
        return prix;
    }

 public Livre(String isbn, double prix, int idTva, String titre, String sousTitre, String couverture, Date dateSortie, String resume, int stock, int statut, int idAuteur, int idEditeur, Tva taux) {
        this.isbn = isbn;
        this.prix = prix;
        this.idTva = idTva;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.couverture = couverture;
        this.dateSortie = dateSortie;
        this.resume = resume;
        this.stock = stock;
        this.statut = statut;
        this.idAuteur = idAuteur;
        this.idEditeur = idEditeur;
        this.taux = taux;
    }

    public Evenement getEvt() {
        BeanConnexion bc = new BeanConnexion();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();

        try {
            Connection co = bc.Connecter();
            String request = "select * from Evenement eve join Participation par on eve.eveId = par.eveId join Livre liv on par.livIsbn = liv.livIsbn where '" + sdf.format(today) + "' between eveDebut and eveFin and liv.livIsbn = '" + this.isbn + "'";
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(request);

            if (rs.next()) {
                this.evt = new Evenement(rs.getString("eveIntitule"),
                        rs.getDate("eveDebut"),
                        rs.getDate("eveFin"),
                        rs.getString("eveImage"),
                        rs.getString("eveDescription"),
                        rs.getFloat("eveRemise")
                );
            }
            co.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL: " + ex.getMessage());
        }
        return evt;
    }

    public Tva getTaux() {
        BeanConnexion bc = new BeanConnexion();
        try {
            Connection co = bc.Connecter();
            String request = "SELECT * FROM Tva where tvaCode = " + this.idTva;
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(request);
            if (rs.next()) {
                this.taux = new Tva(rs.getInt("tvaCode"), rs.getDouble("tvaTaux"));
            }
            co.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL: " + ex.getMessage());
        }
        return taux;
    }

    public String getPrixString() {
        return Double.toString(prix);
    }

    public void setPrix(double prix) {

        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getStock() {
        return stock;
    }

    public String getStockString() {
        return String.valueOf(getStock());
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getIdTva() {
        return idTva;
    }

    public void setIdTva(int idTva) {
        this.idTva = idTva;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public int getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(int idEditeur) {
        this.idEditeur = idEditeur;
    }

    public void update(Connexion c, String ancienIsbn) {
        String query = "UPDATE Livre SET livIsbn = ? ,tvaCode = ? ,ediId = ?  ,autId = ?"
                + ",livTitre = ? ,livPrix = ?,livSousTitre = ? ,livCouverture = ?"
                + ",livDateSortie = ? ,livResume = ?,livStock = ?  ,livStatus = ?"
                + " WHERE livIsbn = ?";

//        java.sql.Date date = new java.sql.Date();
        Connection con = c.getConnection();
        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, isbn);
                ps.setInt(2, getIdTva());
                ps.setInt(3, getIdEditeur());

                ps.setInt(4, getIdAuteur());
                ps.setString(5, titre);
                ps.setDouble(6, prix);
                ps.setString(7, sousTitre);
                ps.setString(8, couverture);
                ps.setDate(9, new java.sql.Date(dateSortie.getTime()));
                ps.setString(10, resume);
                ps.setInt(11, stock);
                ps.setInt(12, statut);

                ps.setString(13, ancienIsbn);

                ps.executeUpdate();
                //System.out.println("fin de prepare statement");
                ps.close();
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertInBase(Connexion c) {

        String query = "INSERT INTO Livre(livIsbn, livTitre, livPrix, livSousTitre, livCouverture, livDateSortie, "
                + "livResume, livStock,tvaCode,autId,ediId) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

//        java.sql.Date date = new java.sql.Date();
        Connection con = c.getConnection();
        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, getIsbn());
                ps.setString(2, titre);
                ps.setDouble(3, prix);
                ps.setString(4, sousTitre);
                ps.setString(5, couverture);
                ps.setDate(6, new java.sql.Date(dateSortie.getTime()));
                ps.setString(7, resume);
                ps.setInt(8, stock);
                ps.setInt(9, getIdTva());
                ps.setInt(10, getIdAuteur());
                ps.setInt(11, getIdEditeur());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Vector<String> getSousThemes(Connexion con) {
        Vector<String> st = new Vector();
        String query = "SELECT souLibelle FROM Livre liv "
                + "JOIN Appartenance app  "
                + "ON liv.livIsbn = app.livISBN "
                + "JOIN SousTheme sou "
                + "ON app.souId = sou.souId "
                + "WHERE liv.livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                st.add(rs.getString("souLibelle"));
            }

            rs.close();
            stmt.close();
            //c.close();
        } catch (SQLException ex) {
            System.err.println("SQL getSousTheme. Classe client. " + ex.getMessage());
            return null;
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return st;
    }

    public Vector<SousTheme> getSousThemesObject(Connexion con) {
        Vector st = new Vector();
        String query = "SELECT * FROM Livre liv "
                + "JOIN Appartenance app  "
                + "ON liv.livIsbn = app.livISBN "
                + "JOIN SousTheme sou "
                + "ON app.souId = sou.souId "
                + "WHERE liv.livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                st.add(new SousTheme(rs.getInt("souId"), rs.getString("souLibelle"), rs.getInt("theId")));
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("SQL getSousThemeObjet. Classe client. " + ex.getMessage());
            return null;
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return st;
    }

    public Theme getTheme(Connexion con) {
        Theme t = null;

        String query = "SELECT * FROM Livre liv "
                + "JOIN Appartenance app "
                + "ON liv.livIsbn = app.livISBN "
                + "JOIN SousTheme sou "
                + "ON app.souId = sou.souId "
                + "JOIN Theme the "
                + "ON sou.theId = the.theId "
                + "WHERE liv.livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                t = new Theme(rs.getInt("theId"), rs.getString("theNom"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("SQL getTheme. Classe client. " + ex.getMessage());
            return t;
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return t;
    }

    @Deprecated
    public Vector<String> getThemes(Connexion con) {

        Vector<String> st = new Vector();
        String query = "SELECT theNom FROM Livre liv "
                + "JOIN Appartenance app "
                + "ON liv.livIsbn = app.livISBN "
                + "JOIN SousTheme sou "
                + "ON app.souId = sou.souId "
                + "JOIN Theme the "
                + "ON sou.theId = the.theId "
                + "WHERE liv.livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                st.add(rs.getString("theNom"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("SQL getTheme. Classe client. " + ex.getMessage());
            return st;
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return st;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;

    }

    public Auteur getAuteur(Connexion con) {

        Auteur v = null;// = new Auteur();

        String query = "SELECT * FROM Auteur aut JOIN Livre liv ON aut.autId = liv.autId WHERE liv.autId = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setInt(1, idAuteur);
            //c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Auteur(rs.getInt("autId"), rs.getString("autNom"),
                        rs.getString("autPrenom"), rs.getString("autCommentaire"));

            }

            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;

    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Editeur getEditeur(Connexion con) {
        Editeur v = null;// = new Auteur();

        String query = "SELECT * FROM Editeur edi JOIN Livre liv ON edi.ediId = liv.ediId WHERE liv.ediId = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setInt(1, idEditeur);
            //c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Editeur(rs.getInt("ediId"), rs.getString("ediNom"), rs.getInt("ediStatut"));
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;
    }

    public int getMoyenne() {
        return moyenne;
    }

    public Tva getTva(Connexion con) {

        Tva v = null;// = new Tva();

        String query = "SELECT * FROM Tva tva JOIN Livre liv ON tva.tvaCode = liv.tvaCode WHERE liv.livIsbn = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setString(1, isbn);
            //c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Tva(rs.getInt("tvaCode"), rs.getDouble("tvaTaux"));
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;
    }

    public void setCritiques(ArrayList<Critique> critiques) {
        this.critiques = critiques;
    }

    public ArrayList<Critique> getCritiques() {

        return critiques;
    }

    private ArrayList<Critique> getCritique(Connexion con) {
        critiques = new ArrayList();
        String query = "SELECT * FROM Critique WHERE livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();

        Statement stmt;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                critiques.add(new Critique(rs.getString("criCommentaire"), rs.getInt("criNote")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!critiques.isEmpty()){
            moyenne = 0;
            for (Critique critique : critiques) {
                moyenne += critique.getNote();
            }
            moyenne /= critiques.size();
        }
        setEtoile();
        return critiques;
    }

    public String getEtoile() {
   
        return etoile;
    }

    public void setEtoile(){
             etoile = "";
        if (moyenne != null) {
            for (int i = 0; i < moyenne; i++) {
                etoile += "<span class=\"glyphicon glyphicon-star\"></span>";
            }
            for (int i = 0; i < 5 - moyenne; i++) {
                etoile += "<span class=\"glyphicon glyphicon-star-empty\"></span>";
            }
        }
    }
    private void getLivre(String isbn, Connexion con) {
        String query = "SELECT * FROM Livre WHERE livIsbn = '" + isbn + "'";
        Connection c = con.getConnection();
        if (c != null) {
            Statement stmt;
            try {
                stmt = c.createStatement();

                //c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    //this.setIsbn(rs.getString("livIsbn"));                
                    setTitre(rs.getString("livTitre"));
                    setSousTitre(rs.getString("livSousTitre"));
                    setPrix(rs.getDouble("livPrix"));
                    setCouverture(rs.getString("livCouverture"));
                    setDateSortie(rs.getDate("livDateSortie"));
                    setIdAuteur(rs.getInt("autId"));
                    setIdEditeur(rs.getInt("ediId"));
                    setIdTva(rs.getInt("tvaCode"));
                    setResume(rs.getString("livResume"));
                    setStock(rs.getInt("livStock"));
                    setStatut(rs.getInt("livStatus"));
                    Auteur aut = getAuteur(con);
                    setAuteur(aut.toString());
                    Editeur ed = getEditeur(con);
                    setEditeur(ed.toString());
                    setCritiques(getCritique(con));

                }
                stmt.close();
                rs.close();

            } catch (SQLException ex) {

            } finally {
                try {
                    c.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Vector getMoi(Connexion con) {
        Connection c = con.getConnection();
        Vector v = new Vector();

        v.add(isbn);
        v.add(this);
        v.add(getEditeur(con));
        v.add(prix);
        v.add(stock);
        return v;
    }

    public void decrementerStock(int nombreDeLivreADecrementer, Connexion c) {
        stock -= nombreDeLivreADecrementer;
        updateStock(stock, c);
    }

    public void incrementerStock(int nbLivreIncrementer, Connexion c) {
        stock += nbLivreIncrementer;
        updateStock(stock, c);
    }

    public void updateStock(int stock, Connexion con) {
        String query = "UPDATE Livre SET = ? livStock  WHERE livIsbn = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setInt(1, stock);
            stmt.setString(2, isbn);
            //c.prepareStatement(query);
            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * cree le 6/7 a 19:38
     *
     * @return STATUT DU LIVRE sous forme object
     */
    public Statut getStatutObjet(Connexion con) {
        Statut s = null;
        String query = "SELECT * FROM Statut WHERE staCode = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setInt(1, statut);
            //c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = new Statut(rs.getInt("staCode"), rs.getString("staIntitule"));
            }

            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }

    /**
     * cree le 8/7 - 00:40
     *
     * @return
     */
    public Vector<MotCle> getMotCle(Connexion con) {
        Vector mc = new Vector();

        String query = "  SELECT * FROM MotCle mot"
                + "  JOIN Caracteristique car"
                + "  ON mot.motId = car.motId"
                + "  JOIN Livre liv"
                + "  ON car.livIsbn = liv.livIsbn"
                + "  WHERE liv.livIsbn = ?";
        Connection c = con.getConnection();
        PreparedStatement stmt;
        try {
            stmt = c.prepareStatement(query);
            stmt.setString(1, isbn);
            //c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mc.add(new MotCle(rs.getInt("motId"), rs.getString("motLibele")));
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mc;
    }

    @Override
    public String toString() {
        return titre;
    }

}
