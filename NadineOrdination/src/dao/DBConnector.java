package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection con = null;

    private static Connection openConnection() throws SQLException{
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:C:\\Users\\Matthias\\Desktop\\Ordination\\Ordination\\NadineOrdination\\db", "admin", "admin");
        } catch (ClassNotFoundException ex) {
            System.err.println("Datenbanktreiber nicht verfuegbar - Fehler: " + ex);
        } catch (SQLException e){
            System.err.println("Datenbankverbindung fehlgeschlagen - Fehler: " + e);
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
