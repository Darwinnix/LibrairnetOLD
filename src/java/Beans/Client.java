package Beans;

import Classes.LibExceptions;

import Classes.mesOutils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client {

    private int cliId;
    private String cliNom;
    private String cliPrenom;
    private String cliGenre;
    private Date cliNaissance;
    private String cliMail;
    private String cliLogin;
    private String cliPwd;
    private String cliObs;
    private String ErreurFatal = null;
    private String Message = null;
    private Vector<Adresse> adrFacture = new Vector();
    private Vector<Adresse> adrLivraison = new Vector();

    public Client() {
    }

//    public Client(String cliNom, String cliPrenom) {
//        this.cliNom = cliNom;
//        this.cliPrenom = cliPrenom;
//    }
     public Client(String login, String pwd) {
        this.cliLogin = login;
        this.cliPwd = pwd;
    }

    public Client(int cliId, String cliNom, String cliPrenom, String cliGenre, Date cliNaissance, String cliMail, String cliLogin, String cliPwd, String cliObs) {
        this.cliId = cliId;
        this.cliNom = cliNom;
        this.cliPrenom = cliPrenom;
        this.cliGenre = cliGenre;
        this.cliNaissance = cliNaissance;
        this.cliMail = cliMail;
        this.cliLogin = cliLogin;
        this.cliPwd = cliPwd;
        this.cliObs = cliObs;
    }

    public int getCliId() {
        return cliId;
    }

    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

    public String getCliNom() {
        return cliNom;
    }

    public void setCliNom(String cliNom) throws LibExceptions {
      mesOutils.checkNom(cliNom);
        this.cliNom = cliNom;
        
    }

    public String getCliPrenom() {
        return cliPrenom;
    }

    public void setCliPrenom(String cliPrenom) throws LibExceptions {
        mesOutils.checkPrenom(cliPrenom);
      this.cliPrenom = cliPrenom;
        
    }

    public String getCliGenre() {
        return cliGenre;
    }

    public void setCliGenre(String cliGenre) throws LibExceptions {
        mesOutils.checkGenre(cliGenre);
        this.cliGenre = cliGenre;
    }

    public Date getCliNaissance() {
        return cliNaissance;
    }

    public void setCliNaissance(Date cliNaissance) throws LibExceptions {
        mesOutils.checkDate(cliNaissance);

        this.cliNaissance = cliNaissance;
    }
 public void setCliNaissance(String cliNaissance) throws LibExceptions {

        this.cliNaissance = mesOutils.checkDate(cliNaissance);
    }
    public String getCliMail() {
        return cliMail;
    }

    public void setCliMail(String cliMail) throws LibExceptions {
        mesOutils.checkMail(cliMail);
        this.cliMail = cliMail;
    }

    public String getCliLogin() {
        return cliLogin;
    }

    public void setCliLogin(String cliLogin) throws LibExceptions {
        mesOutils.checkLogin1(cliLogin);
        this.cliLogin = cliLogin;
    }

    public String getCliPwd() {
        return cliPwd;
    }

    public void setCliPwd(String cliPwd) throws LibExceptions {
        mesOutils.checkPwd(cliPwd);
        this.cliPwd = cliPwd;
    }
    
    public boolean chekloginPwd(String login,String pwd){
      return mesOutils.checkLogin2(login, pwd);
  }
    public String getCliObs(){
        
        return cliObs;
    }

    public void setCliObs(String cliObs) throws LibExceptions {
        mesOutils.checkObsr(cliObs);
        this.cliObs = cliObs;
    }

    public Vector<Adresse> getAdrFacture() {
        return adrFacture;
    }

    public void setAdrFacture(Vector<Adresse> adrFacture) {
        this.adrFacture = adrFacture;
    }

    public Vector<Adresse> getAdrLivraison() {
        return adrLivraison;
    }

    public void setAdrLivraison(Vector<Adresse> adrLivraison) {
        this.adrLivraison = adrLivraison;
    }
    

    public Vector getVector() {
        Vector v = new Vector();
        v.add(this);
        v.add(cliId);
        v.add(cliNom);
        v.add(cliPrenom);
        v.add(cliGenre);
        v.add(cliNaissance);
        v.add(cliMail);
        v.add(cliLogin);
        v.add(cliPwd);
        v.add(cliObs);
        return v;
    }
    public void UpdateClient(){
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
            String query= "update Client set cliNom = ? ,cliPrenom = ? ,cliGenre = ? ,cliNaissance = ?"
                    + ", cliMail = ? , cliLogin = ? , cliPwd = ? , cliObs=? where cliId = ?";
            PreparedStatement pstmt= connexion.prepareStatement( query);
            
            pstmt.setString(1,cliNom);
            pstmt.setString(2,cliPrenom);
            pstmt.setString(3,cliGenre);
            pstmt.setDate(4,cliNaissance);
            pstmt.setString(5,cliMail);
            pstmt.setString(6,cliLogin);
            pstmt.setString(7,cliPwd);
            pstmt.setString(8,cliObs);
            pstmt.setInt(9,cliId);
            
            int result= pstmt.executeUpdate();
            
            System.out.println("Result:"+ result);
            
            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:"+ ex.getErrorCode()+"/"+ex.getMessage());
        }
        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:close:"+ ex.getMessage());
        }

        System.out.println("Done!");
    }
      public void InsertClient(){
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
            String query= "insert into Client values (?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt= connexion.prepareStatement( query);
            
            pstmt.setString(1,cliNom);
            pstmt.setString(2,cliPrenom);
            pstmt.setString(3,cliGenre);
            pstmt.setDate(4,cliNaissance);
            pstmt.setString(5,cliMail);
            pstmt.setString(6,cliLogin);
            pstmt.setString(7,cliPwd);
            pstmt.setString(8,cliObs);
            
            int result= pstmt.executeUpdate();
            
            System.out.println("Result:"+ result);
            
            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:"+ ex.getErrorCode()+"/"+ex.getMessage());
        }
        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:close:"+ ex.getMessage());
        }

        System.out.println("Done!");
    }
    public int ExistClient(){
        int res = -1;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oops:ClassNotFound:" + ex.getMessage());
            return res;
        }

        Connection connexion = null;

        try {
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Librairnet;username=sa;password=sa";
            connexion = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.err.println("Oops:connexion:" + ex.getMessage());
            return res;
        }
      String query = "SELECT cliLogin FROM Client "
                + " ORDER BY 1;";
        try {
                    Statement stmt = connexion.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        if(mesOutils.StringNull(cliLogin).equalsIgnoreCase(mesOutils.StringNull(rs.getString("cliLogin")))){
                            JOptionPane.showMessageDialog(null, "le client Existe", " Erreur", JOptionPane.ERROR_MESSAGE);
                            return 0;
                   }
                    }
//            jTextFieldNumero.setText(null);
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("Oops:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
                    return res;
                }
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Oops:close:" + ex.getMessage());
                    return res;
                }
              
                System.out.println("Done!");
                  return res;
            
    }
    public int LoginValide(String login,String pwd,Connection conect){
            int res = -1;
            Statement stmt = null;
            ResultSet rs = null;

         
           String query = "SELECT * FROM Client "
                    + " where cliLogin='"+login+"' and cliPwd='"+pwd+"'"
                + " ORDER BY 1;";
        try {
                    stmt = conect.createStatement();
                    rs = stmt.executeQuery(query);
                    
                    while (rs.next()) {

                    if(login.equals(rs.getString("cliLogin"))&& pwd.equals(rs.getString("cliPwd"))){
                            cliId = Integer.parseInt(rs.getString("cliId"));
                            cliNom = rs.getString("cliNom");
                            cliPrenom = rs.getString("cliPrenom");
                            cliGenre=rs.getString("cliPrenom");
                            cliNaissance=rs.getDate("cliNaissance");
                            cliMail=rs.getString("cliMail");
                            cliLogin=rs.getString("cliLogin");
                            cliPwd=rs.getString("cliPwd");
                            cliObs=rs.getString("cliObs");        
                            return 0;
                   }
                    else{
                        Message = "Login / Mot de passe invalide.";
                    }
                    }
                    
                    

                } catch (SQLException ex) {
                    ErreurFatal = "Problème de Base de Donnée";
                    System.err.println("Oops 0:SQL:" + ex.getErrorCode() + "/" + ex.getMessage());
                    return res;
                }finally{
                if(rs != null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Oops 2 sql : "+ex.getMessage());
                         ErreurFatal = "Problème de Base de Donnée";
                        return res;
                    }
                }
                 if(stmt != null){
                     try {
                        stmt.close();
                    } catch (SQLException ex) {
                        System.out.println("Oops 3 sql : "+ex.getMessage());
                         ErreurFatal = "Problème de Base de Donnée";
                        return res;
                    }
                 }

        }
                
            return res;  
            
            
            
    }
    public void AjouterClient(Connection con){
        try {
            String query= "insert into Client values (?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt= con.prepareStatement( query);
            
            pstmt.setString(1,cliNom);
            pstmt.setString(2,cliPrenom);
            pstmt.setString(3,cliGenre);
            pstmt.setDate(4,cliNaissance);
            pstmt.setString(5,cliMail);
            pstmt.setString(6,cliLogin);
            pstmt.setString(7,cliPwd);
            pstmt.setString(8,cliObs);
            
            int result= pstmt.executeUpdate();
            
            System.out.println("Result:"+ result);
            
            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:"+ ex.getErrorCode()+"/"+ex.getMessage());
        }
       
        System.out.println("Done!");
        
    }
}

   


