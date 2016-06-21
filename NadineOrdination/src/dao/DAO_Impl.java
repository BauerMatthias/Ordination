package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

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
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("DELETE FROM PATIENT WHERE P_ID = ? CASCADE");
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

    @Override
    public boolean saveAllKrankheitenByPatient(Patient p, ArrayList<Krankheit> k) {
        boolean ret = false;
        if(p == null){
            throw new IllegalArgumentException();
        }

        int i = 0;
        int h = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = c.prepareStatement("INSERT INTO PAT_KRANK (P_ID, K_ID) VALUES(?, ?);");
            p.setId(getIdFromPatient(p));

            for(Krankheit krank : k){
                pstm.setInt(1, p.getId());
                pstm.setInt(2, krank.getNummer());

                h = pstm.executeUpdate();

                if(h != 0){
                    ret = true;
                } else {
                    ret = false;
                }

                h = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public int getIdFromPatient(Patient p) {
        Patient ret = new Patient();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM PATIENT WHERE vname=? AND nname=?;";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);

            pstm.setString(1, p.getVorname());
            pstm.setString(2, p.getNachname());

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("P_ID"));
                ret.setVorname(rs.getString("VNAME"));
                ret.setNachname(rs.getString("NNAME"));
                ret.setAdresse(rs.getString("ADRESSE"));
                ret.setGebDatum(rs.getDate("GEBDATUM").toLocalDate());
                ret.setTelNummer(rs.getString("TELNR"));
                ret.setTarif(rs.getDouble("TARIF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret.getId();
    }

    @Override
    public ArrayList<Krankheit> getAllKrankheitenByPatient(Patient p) {
        ArrayList<Krankheit> ret = new ArrayList<Krankheit>();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM PAT_Krank WHERE P_ID=?";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            pstm.setInt(1, p.getId());
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ret.add(getKrankheitbyID(rs.getInt("K_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public Krankheit getKrankheitbyID(int id) {
        Krankheit ret = new Krankheit();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM KRANKHEIT WHERE K_ID=?";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int k_id = rs.getInt("K_ID");
                String beschreibung = rs.getString("BESCHREIBUNG");

                ret.setNummer(k_id);
                ret.setBeschreibung(beschreibung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public boolean updateKrankheit(Krankheit k) {
        if(k == null){
            throw new IllegalArgumentException("Übergebene Krankheit gleich NULL");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("UPDATE Krankheit SET BESCHREIBUNG = ? WHERE K_ID = ?");
            pstm.setString(1, k.getBeschreibung());
            pstm.setInt(2, k.getNummer());

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
    public boolean saveKrankheit(Krankheit k) {
        if(k == null){
            throw new IllegalArgumentException("Übergebene Krankheit ist NULL.");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = c.prepareStatement("INSERT INTO KRANKHEIT (BESCHREIBUNG) VALUES(?);");

            pstm.setString(1, k.getBeschreibung());

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
    public Krankheit getKrankheitbyBeschreibung(String beschreibung) {
        Krankheit ret = new Krankheit();
        try {
            Connection c = DBConnector.getConnection();
            String sql = "SELECT * FROM KRANKHEIT WHERE BESCHREIBUNG=?";
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
            pstm.setString(1, beschreibung);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int k_id = rs.getInt("K_ID");
                String beschr = rs.getString("BESCHREIBUNG");

                ret.setNummer(k_id);
                ret.setBeschreibung(beschr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }

        return ret;
    }

    @Override
    public boolean deleteKrankheit(Krankheit k) {
        if(k == null){
            throw new IllegalArgumentException("Übergebene Krankheit gleich NULL");
        }

        int i = 0;
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement pstm = DBConnector.getConnection().prepareStatement("DELETE FROM KRANKHEIT WHERE K_ID = ?");
            pstm.setInt(1, k.getNummer());
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
    public ArrayList<Statistik_Daten> getStatistikByPatient(Patient p, LocalDate fromDate, LocalDate toDate) {
        ArrayList<Statistik_Daten> retValue = new ArrayList<>();

        // Datumsstatistik
        if(p == null && fromDate != null && toDate != null){
            try {
                Connection c = DBConnector.getConnection();
                String sql = "SELECT * FROM BEHANDLUNG WHERE DATUM BETWEEN ? AND ?";
                PreparedStatement pstm = DBConnector.getConnection().prepareStatement(sql);
                pstm.setDate(1, Date.valueOf(fromDate));
                pstm.setDate(2, Date.valueOf(toDate));
                ResultSet rs = pstm.executeQuery();

                while (rs.next()) {
                    int p_id = rs.getInt("P_ID");
                    Patient currPat = getPatientbyID(p_id);
                    Date currDate = rs.getDate("DATUM");
                    double einnahme = rs.getDouble("EINNAHME");

                    LocalTime lt = LocalTime.of(0, 0);
                    LocalDate ld = currDate.toLocalDate();
                    LocalDateTime convertedDate = LocalDateTime.of(ld, lt);

                    if(retValue.size() > 0) {
                        for (int i = 0; i < retValue.size(); i++) {
                            if(retValue.get(i).getP().getId() == currPat.getId()){
                                retValue.get(i).setWert(retValue.get(i).getWert() + einnahme);
                            }
                        }
                    }

                    retValue.add(new Statistik_Daten(convertedDate, einnahme, currPat));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConnector.closeConnection();
            }

            return retValue;
        } else if(p != null && fromDate == null && toDate == null){     // Patientenstatistik ohne Datumangaben
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 4, 0, 0), 63.0, new Patient("Matthias", "Bauer")));
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 5, 0, 0), 17.0, new Patient("Matthias", "Bauer")));
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 6, 0, 0), 85.0, new Patient("Matthias", "Bauer")));
        } else if(p != null && fromDate != null && toDate != null) {     // Patientenstatistik mit Datumsangaben
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 7, 0, 0), 38.0, new Patient("Thomas", "Prager")));
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 8, 0, 0), 7.0, new Patient("Thomas", "Prager")));
            retValue.add(new Statistik_Daten(LocalDateTime.of(2016, 6, 9, 0, 0), 150.0, new Patient("Thomas", "Prager")));
        }

        return retValue;
    }

}
