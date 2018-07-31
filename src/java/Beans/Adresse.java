package Beans;

import Classes.LibExceptions;
import Classes.mesOutils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Adresse implements Serializable{

    private int adrId;
    private String adrNom;
    private String adrPrenom;
    private String adrNumVoie;
    private String adrTypeVoie;
    private String adrNomVoie;
    private String adrCompVoie;
    private String adrCodePostal;
    private String adrVille;
    private String adrPays;
    private String adrLibele;
    private int adrStatut;

    public Adresse() {
    }

    public Adresse(int adrId, String adrNom, String adrPrenom, String adrNumVoie, String adrTypeVoie, String adrNomVoie, String adrCompVoie, String adrCodePostal, String adrVille, String adrPays, String adrLibele, int adrStatut) {
        this.adrId = adrId;
        this.adrNom = adrNom;
        this.adrPrenom = adrPrenom;
        this.adrNumVoie = adrNumVoie;
        this.adrTypeVoie = adrTypeVoie;
        this.adrNomVoie = adrNomVoie;
        this.adrCompVoie = adrCompVoie;
        this.adrCodePostal = adrCodePostal;
        this.adrVille = adrVille;
        this.adrPays = adrPays;
        this.adrLibele = adrLibele;
        this.adrStatut = adrStatut;
    }

    public Adresse(String adrNom, String adrPrenom, String adrNumVoie, String adrTypeVoie, String adrNomVoie, String adrCompVoie, String adrCodePostal, String adrVille, String adrPays, String adrLibele, int adrStatut) {
        this.adrNom = adrNom;
        this.adrPrenom = adrPrenom;
        this.adrNumVoie = adrNumVoie;
        this.adrTypeVoie = adrTypeVoie;
        this.adrNomVoie = adrNomVoie;
        this.adrCompVoie = adrCompVoie;
        this.adrCodePostal = adrCodePostal;
        this.adrVille = adrVille;
        this.adrPays = adrPays;
        this.adrLibele = adrLibele;
        this.adrStatut = adrStatut;
    }

    public int getAdrId() {
        return adrId;
    }

    public void setAdrId(int adrId) {
        this.adrId = adrId;
    }

    public String getAdrNom() {
        return adrNom;
    }

    public void setAdrNom(String adrNom) throws LibExceptions {
        mesOutils.checkNom(adrNom);
        this.adrNom = adrNom;
    }

    public String getAdrPrenom() {
        return adrPrenom;
    }

    public void setAdrPrenom(String adrPrenom) throws LibExceptions {
        mesOutils.checkPrenom(adrPrenom);
        this.adrPrenom = adrPrenom;
    }

    public String getAdrNumVoie() {
        return adrNumVoie;
    }

    public void setAdrNumVoie(String adrNumVoie) {
        this.adrNumVoie = adrNumVoie;
    }

    public String getAdrTypeVoie() {
        return adrTypeVoie;
    }

    public void setAdrTypeVoie(String adrTypeVoie) {
        this.adrTypeVoie = adrTypeVoie;
    }

    public String getAdrNomVoie() {
        return adrNomVoie;
    }

    public void setAdrNomVoie(String adrNomVoie) {
        this.adrNomVoie = adrNomVoie;
    }

    public String getAdrCompVoie() {
        return adrCompVoie;
    }

    public void setAdrCompVoie(String adrCompVoie) {
        this.adrCompVoie = adrCompVoie;
    }

    public String getAdrCodePostal() {
        return adrCodePostal;
    }

    public void setAdrCodePostal(String adrCodePostal) throws LibExceptions {
        mesOutils.checkCodePostal(adrCodePostal);
        this.adrCodePostal = adrCodePostal;
    }

    public String getAdrVille() {
        return adrVille;
    }

    public void setAdrVille(String adrVille) throws LibExceptions {
        mesOutils.checkVille(adrVille);
        this.adrVille = adrVille;
    }

    public String getAdrPays() {
        return adrPays;
    }

    public void setAdrPays(String adrPays) throws LibExceptions {
        mesOutils.checkPays(adrPays);
        this.adrPays = adrPays;
    }

    public String getAdrLibele() {
        return adrLibele;
    }

    public void setAdrLibele(String adrLibele) {
        this.adrLibele = adrLibele;
    }

    public int getAdrStatut() {
        return adrStatut;
    }

    public void setAdrStatut(int adrStatut) {
        this.adrStatut = adrStatut;
    }

    public int AdresseExistCommande() {
        int result = -1;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oops:ClassNotFound:" + ex.getMessage());

        }

        Connection connexion = null;

        try {
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Librairnet;username=sa;password=sa";
            connexion = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.err.println("Oops:connexion:" + ex.getMessage());
        }

        try {
            String query = "select count(distinct adrIdLivraison),count(distinct adrIdFacturation) from commande where adrIdLivraison =" + this.adrId
                    + "or adrIdFacturation =" + this.adrId;
            System.out.println(query);
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = rs.getInt(1) + rs.getInt(2);
//                System.out.println("="+ result);

            }

            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
            ex.printStackTrace();
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:close:" + ex.getMessage());
        }

        System.out.println("Done!");
        return result;
    }
    //une methode qui teste si une adresse existe dans la table de facturation.(non utiliser)
//        public int AdresseFacturationExistCommande(){
//
//        int result =-1;
//        
//       try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        } catch (ClassNotFoundException ex) {
//            System.err.println("Oops:ClassNotFound:" + ex.getMessage());
//            
//        }
//
//        Connection connexion = null;
//
//        try {
//            String url = "jdbc:sqlserver://localhost:1433;"
//                    + "databaseName=Librairnet;username=sa;password=sa";
//            connexion = DriverManager.getConnection(url);
//        } catch (SQLException ex) {
//            System.err.println("Oops:connexion:" + ex.getMessage());
//        }
//        
//        try {
//            String query= "select count(distinct adrIdFacturation)  as coucou from commande where  adrIdFacturation="+this.adrId; 
//            System.out.println( query);
//            Statement stmt= connexion.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while(rs.next()){
//                result= rs.getInt(1);
//                System.out.println("="+ result);
//
//            }
//            
//            stmt.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.err.println("Oops:SQL:"+ ex.getErrorCode()+"/"+ex.getMessage());
//            ex.printStackTrace();
//        }
//        
//        
//        
//        
//        
//        
//        try {
//            connexion.close();
//        } catch (SQLException ex) {
//            System.err.println("Oops:close:"+ ex.getMessage());
//        }
//        
//        System.out.println("Done!");
//        return result;
//    }

    //méthodes qui update un objet adresse dans la table adresse.après vérification(une nouvelle adresse dans la table)qui existe pas dans la table commande
    public void ModifierAdresse() {
        if (AdresseExistCommande() == 0) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                System.err.println("Oops:ClassNotFound:" + ex.getMessage());
                return;
            }

            Connection connexion = null;

            try {
                String url = "jdbc:sqlserver://localhost:1433;"
                        + "databaseName=Librairnet;username=sa;password=sa";
                connexion = DriverManager.getConnection(url);
            } catch (SQLException ex) {
                System.err.println("Oops:connexion:" + ex.getMessage());
            }

            try {
                String query = "update Adresse set adrNom = ? ,adrPrenom = ? ,adrNumVoie = ? ,adrTypeVoie = ?"
                        + ", adrNomVoie = ? , adrCompVoie = ? , adrCodePostal = ? ,adrVille =?,adrPays=? ,adrLibele=?,adrStatut=? where  adrId= ?";
                PreparedStatement pstmt = connexion.prepareStatement(query);

                pstmt.setString(1, adrNom);
                pstmt.setString(2, adrPrenom);
                pstmt.setString(3, adrNumVoie);
                pstmt.setString(4, adrTypeVoie);
                pstmt.setString(5, adrNomVoie);
                pstmt.setString(6, adrCompVoie);
                pstmt.setString(7, adrCodePostal);
                pstmt.setString(8, adrVille);
                pstmt.setString(9, adrPays);
                pstmt.setString(10, adrLibele);
                pstmt.setInt(11, adrStatut);
                pstmt.setInt(12, adrId);

                int result = pstmt.executeUpdate();

                System.out.println("Result:" + result);

                pstmt.close();
            } catch (SQLException ex) {
                System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
            }

            try {
                connexion.close();
            } catch (SQLException ex) {
                System.err.println("Oops:close:" + ex.getMessage());
            }

            System.out.println("Done!");
        } else {
            JOptionPane.showMessageDialog(null, "l'Adresse Existe", " Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void AjouterAdresse() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oops:ClassNotFound:" + ex.getMessage());
            return;
        }

        Connection connexion = null;

        try {
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Librairnet;username=sa;password=sa";
            connexion = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.err.println("Oops:connexion:" + ex.getMessage());
        }

        try {
            String query = "insert into Adresse values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, adrNom);
            pstmt.setString(2, adrPrenom);
            pstmt.setString(3, adrNumVoie);
            pstmt.setString(4, adrTypeVoie);
            pstmt.setString(5, adrNomVoie);
            pstmt.setString(6, adrCompVoie);
            pstmt.setString(7, adrCodePostal);
            pstmt.setString(8, adrVille);
            pstmt.setString(9, adrPays);
            pstmt.setString(10, adrLibele);
            pstmt.setInt(11, adrStatut);

            int result = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                setAdrId(rs.getInt(1));
//                System.out.println("key "+rs.getInt(1));
            }
            System.out.println("Result:" + result);

            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:close:" + ex.getMessage());
        }

        System.out.println("Done!");
    }

    @Override
    public String toString() {
        return adrId + " - " + adrNom;
    }

    //pour ajouter une adresse je regarde si elle existe dans la base de donner.
    public int ComparaisonAdresse() {
        int resultat = -1;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oops:Driver:" + ex.getMessage());
            return resultat;
        }
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Librairnet;user=sa;password=sa");
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
            return resultat;
        }

        String query = "select adrNom,adrPrenom,adrNumVoie,adrTypeVoie,adrNomVoie,adrCompVoie,adrCodePostal,adrVille,adrPays,adrLibele,adrStatut"
                + " from Adresse";

        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
//                System.out.println(adrNom + "==" + rs.getString("adrNom") + "/"
//                        + adrPrenom + "==" + rs.getString("adrPrenom") + "/"
//                        + "/" + adrNumVoie + "==" + rs.getString("adrNumVoie")
//                        + "/" + adrTypeVoie + "==" + rs.getString("adrTypeVoie")
//                        + "/" + adrNomVoie + "==" + rs.getString("adrNomVoie")
//                        + "/" + adrCompVoie + "==" + rs.getString("adrCompVoie")
//                        + "/" + adrCodePostal + "==" + rs.getString("adrCodePostal")
//                        + "/" + adrVille + "==" + rs.getString("adrVille")
//                        + "/" + adrPays + "==" + rs.getString("adrPays")
//                        + "/" + adrLibele + "==" + mesOutils.StringNull(rs.getString("adrLibele"))+"====="+ 
//                        (adrNom.equalsIgnoreCase(rs.getString("adrNom")) && adrPrenom.equalsIgnoreCase(rs.getString("adrPrenom"))
//                        && adrNumVoie.equalsIgnoreCase(rs.getString("adrNumVoie")) && adrTypeVoie.equalsIgnoreCase(rs.getString("adrTypeVoie"))
//                        && adrNomVoie.equalsIgnoreCase(rs.getString("adrNomVoie")) && mesOutils.StringNull(adrCompVoie).equalsIgnoreCase(rs.getString("adrCompVoie"))
//                        && adrCodePostal.equalsIgnoreCase(rs.getString("adrCodePostal")) && adrVille.equalsIgnoreCase(rs.getString("adrVille")) && adrPays.equalsIgnoreCase(rs.getString("adrPays"))
//                        && mesOutils.StringNull(adrLibele).equalsIgnoreCase(rs.getString("adrLibele"))));
                
                
                if (adrNom.equalsIgnoreCase(rs.getString("adrNom")) && adrPrenom.equalsIgnoreCase(rs.getString("adrPrenom"))
                        && mesOutils.StringNull(adrNumVoie).equalsIgnoreCase(mesOutils.StringNull(rs.getString("adrNumVoie"))) && mesOutils.StringNull(adrTypeVoie).equalsIgnoreCase(mesOutils.StringNull(rs.getString("adrTypeVoie")))
                        && mesOutils.StringNull(adrNomVoie).equalsIgnoreCase(mesOutils.StringNull(rs.getString("adrNomVoie"))) && mesOutils.StringNull(adrCompVoie).equalsIgnoreCase(mesOutils.StringNull(rs.getString("adrCompVoie")))
                        && adrCodePostal.equalsIgnoreCase(rs.getString("adrCodePostal")) && adrVille.equalsIgnoreCase(rs.getString("adrVille")) && adrPays.equalsIgnoreCase(rs.getString("adrPays"))
                        && mesOutils.StringNull(adrLibele).equalsIgnoreCase(mesOutils.StringNull(rs.getString("adrLibele")))) {
                    JOptionPane.showMessageDialog(null, "l'Adresse Existe", " Erreur", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return resultat;
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:Close:" + ex.getErrorCode() + ":" + ex.getMessage());
            return resultat;
        }

        System.out.println("Done!");

        return resultat;

    }

//    public static void main(String[] args) {
//        Adresse a = new Adresse("momo", "momo", "", "", "", "", "67200", "strass", "france", "", "");
//        a.AjouterAdresse();
//
//    }

}
