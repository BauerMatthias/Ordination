package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.LogManager;

/**
 * Created by Matthias on 04.02.2016.
 */
public class DBConnector {

    private static Connection con = null;

    private static Connection openConnection() throws SQLException{
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection();        // TODO: link to database
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static Connection getConnection(){
        try {
            con = openConnection();
        } catch(SQLException ex){
            System.err.println("Fehler beim oeffnen der Verbindung: " + ex);
        }

        return con;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex){
            System.err.println("Fehler beim schliessen der Verbdingung: " + ex);
        }
    }

}
