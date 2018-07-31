package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BeanConnexion implements Serializable {

    private DataSource ds;

    public Connection Connecter() {
        InitialContext context;
        try {
            context = new InitialContext();
            if (ds == null) {
                ds = (DataSource) context.lookup("jdbc/Librairnet");
            }
        } catch (NamingException ex) {
            System.err.println("Erreur: Naming: " + ex.getMessage());
        }
        Connection co = null;
        try {
            co = ds.getConnection();
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL: " + ex.getMessage());
        }
        return co;
    }

    public ResultSet RequeteSelect(Connection co, String requete) {
        ResultSet rs = null;
        try {
            Statement st = co.createStatement();
            rs = st.executeQuery(requete);
            st.close();
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL " + ex.getMessage());
        }
        return rs;
    }

    public void fermerConnexion(Connection co) {
        try {
            co.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getMessage());
        }
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

}
