
package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connexion implements Serializable{

    public static DataSource ds = null;
   

    public Connexion() {

    }

    public Connection getConnection() {

        try {

           
            if (ds == null) {
                InitialContext context = new InitialContext();
                ds = (DataSource) context.lookup("jdbc/Librairnet");
            }
            
            try {
                return ds.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (NamingException ex) {
            System.out.println("Oops: Naming: " + ex.getMessage());
        }

        return null;
    }

}
