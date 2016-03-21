package dao;

import model.Behandlung;
import model.Behandlung_Beschreibung;
import model.Krankheit;
import model.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Matthias on 10.03.2016.
 */
public class DAO_Impl implements DAO {

    @Override
    public ArrayList<Patient> getAllPatienten() {
        ArrayList<Patient> ret = new ArrayList<Patient>();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM PATIENT";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int pid = rs.getInt("P_ID");
                String vname = rs.getString("VNAME");
                String nname = rs.getString("NNAME");
                String adresse = rs.getString("ADRESSE");
                Date gebDatum = rs.getDate("GEBDATUM");
                String telnr = rs.getString("TELNR");
                Double tarif = rs.getDouble("TARIF");

                LocalDate convertedDate = gebDatum.toLocalDate();

                ret.add(new Patient(pid, vname, nname, adresse, convertedDate, telnr, tarif));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public boolean createPatient(Patient p) {
        if(p == null){
            throw new IllegalArgumentException();
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("INSERT INTO PATIENT (VNAME, NNAME, ADRESSE, GEBDATUM, TELNR, TARIF) VALUES(?, ?, ?, ?, ?, ?)");

            pstm.setString(1, p.getVorname());
            pstm.setString(2, p.getNachname());
            pstm.setString(3, p.getAdresse());
            Date convertedDate = Date.valueOf(p.getGebDatum());
            pstm.setDate(4, convertedDate);
            pstm.setString(5, p.getTelNummer());
            pstm.setDouble(6, p.getTarif());

            i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        if(i >= 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePatient(Patient p) {
        if(p == null){
            throw new IllegalArgumentException("Übergebener Patient gleich NULL");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("UPDATE Patient SET VNAME = ?, NNAME = ?, ADRESSE = ?, GEBDATUM = ?, TELNR = ?, TARIF = ? WHERE P_ID = ?");
            pstm.setString(1, p.getVorname());
            pstm.setString(2, p.getNachname());
            pstm.setString(3, p.getAdresse());
            pstm.setDate(4, Date.valueOf(p.getGebDatum()));
            pstm.setString(5, p.getTelNummer());
            pstm.setDouble(6, p.getTarif());

            pstm.setInt(7, p.getId());

            i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        if(i >= 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Patient getPatientbyID(int id) {
        Patient ret = null;
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM PATIENT WHERE P_ID = ?";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                int pid = rs.getInt("P_ID");
                String vname = rs.getString("VNAME");
                String nname = rs.getString("NNAME");
                String adresse = rs.getString("ADRESSE");
                Date gebDatum = rs.getDate("GEBDATUM");
                String telnr = rs.getString("TELNR");
                Double tarif = rs.getDouble("TARIF");

                LocalDate convertedDate = gebDatum.toLocalDate();

                ret = new Patient(pid, vname, nname, adresse, convertedDate, telnr, tarif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public boolean deletePatient(Patient p) {
        if(p == null){
            throw new IllegalArgumentException("Übergebene Behandlung gleich NULL");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("DELETE FROM PATIENT WHERE P_ID = ?");
            pstm.setInt(1, p.getId());
            i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        if(i >= 1){
            return true;
        } else {
            System.out.println("Es wurde kein Datensatz gelöscht.");
            return false;
        }
    }

    @Override
    public double getTarifByPatient(Patient p) {
        if(p == null){
            throw new IllegalArgumentException("Kein Patient übergeben");
        }

        Double ret = -1.0;
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT TARIF FROM PATIENT WHERE P_ID = ?";
            PreparedStatement pstm = c.prepareStatement(sql);
            System.out.println("Übergebener patient: " + p.getId());
            pstm.setInt(1, p.getId());
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                ret = rs.getDouble("Tarif");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        System.out.println("Tarif lautet: " + ret);

        return ret;

    }

    @Override
    public ArrayList<Krankheit> getAllKrankheiten() {
        ArrayList<Krankheit> ret = new ArrayList<Krankheit>();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM KRANKHEIT";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int k_id = rs.getInt("K_ID");
                String beschreibung = rs.getString("BESCHREIBUNG");

                ret.add(new Krankheit(k_id, beschreibung));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public boolean createBehandlung(Behandlung b) {
        if(b == null){
            throw new IllegalArgumentException();
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = c.prepareStatement("INSERT INTO BEHANDLUNG (P_ID, DATUM, BEH_BESCHREIBUNG, EINNAHME, BEMERKUNG) VALUES(?, ?, ?, ?, ?);");

            pstm.setInt(1, b.getPatient().getId());
            pstm.setDate(2, Date.valueOf(b.getDatum()));
            pstm.setString(3, b.getBeschreibung());
            pstm.setDouble(4, b.getEinnahme());
            pstm.setString(5, b.getBemerkung());

            i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        if(i >= 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBehandlung(Behandlung b) {
        if(b == null){
            throw new IllegalArgumentException("Übergebene Behandlung gleich NULL");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("DELETE FROM BEHANDLUNG WHERE B_ID = ?");
            pstm.setInt(1, b.getId());
            i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        if(i >= 1){
            return true;
        } else {
            System.out.println("Es wurde kein Datensatz gelöscht.");
            return false;
        }
    }

    @Override
    public ArrayList<Behandlung> getAllBehandlungen() {
        ArrayList<Behandlung> ret = new ArrayList<Behandlung>();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM BEHANDLUNG";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                int b_id = rs.getInt("B_ID");
                Patient p = getPatientbyID(rs.getInt("P_ID"));
                Date datum = rs.getDate("datum");
                String beh_beschreibung = rs.getString("BEH_BESCHREIBUNG");
                Double einnahme = rs.getDouble("EINNAHME");
                String bemerkung = rs.getString("BEMERKUNG");

                LocalDate convertedDate = datum.toLocalDate();

                ret.add(new Behandlung(b_id, p, convertedDate, beh_beschreibung, einnahme, bemerkung));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }
}
