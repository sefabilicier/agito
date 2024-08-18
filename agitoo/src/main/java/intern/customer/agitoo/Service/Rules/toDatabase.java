package intern.customer.agitoo.Service.Rules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class toDatabase {

    public static void isConnected () { //burada static vermemizin sebebi diğer classlarda çağrılmadan kullanılabiliyor olması
        final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
        final String JDBC_USER = "C##EGITIM";
        final String JDBC_PASSWORD = "1";

        Connection connection = null;

        try {
            Class.forName ("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection (JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println ("Successfully connected to the database!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println ("JDBC Driver not found.");
            e.printStackTrace ();
        }
    }
}
