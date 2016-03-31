package dao;

import model.Behandlung;
import model.Krankheit;
import model.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Matthias on 10.03.2016.
 */
public interface DAO {

    // Gibt alle Patienten aus der Datenbank zurück
    public ArrayList<Patient> getAllPatienten();

    // Speichert einen neuen Patienten in der DB
    public boolean createPatient(Patient p);

    // Bearbeitet einen Patienten
    public boolean updatePatient(Patient p);

    // Gibt einen Patienten anhand einer ID zurück
    public Patient getPatientbyID(int id);

    // Löscht einen Patienten aus der DB
    public boolean deletePatient(Patient p);

    // Gibt den Tarif für diese Person aus
    public double getTarifByPatient(Patient p);

    // Gibt alle Krankheiten aus der Datenbank zurück
    public ArrayList<Krankheit> getAllKrankheiten();

    // Speichert eine neue Bahandlung in der DB
    public boolean createBehandlung(Behandlung b);

    // Löscht eine Behandlung aus der DB
    public boolean deleteBehandlung(Behandlung b);

    // Gibt alle Behandlungen aus der DB zurück
    public ArrayList<Behandlung> getAllBehandlungen();

    // Speichert alle Krankheiten für einen Patienten ab
    public boolean saveAllKrankheitenByPatient(Patient p, ArrayList<Krankheit> k);

    // Gibt die ID eines Patienten zurück
    public int getIdFromPatient(Patient p);

    // Gibt alle Krankheiten eines Patienten zurück
    public ArrayList<Krankheit> getAllKrankheitenByPatient(Patient p);

    // Gibt die Krankheit mit der ID zurück
    public Krankheit getKrankheitbyID(int id);

    // Verändert die übergebene Krankheit
    public boolean updateKrankheit(Krankheit k);

    // Speichert die neue Krankheit ab
    public boolean saveKrankheit(Krankheit k);

    // Gibt die Krankheit mit der Beschreibung zurück
    public Krankheit getKrankheitbyBeschreibung(String beschreibung);

    // Löscht eine Krankheit aus der Datenbank
    public boolean deleteKrankheit(Krankheit k);

    // Holt die Statistik eines Patienten in einem Zeitraum
    public Map<LocalDate, Double> getStatistikByPatient(Patient p, LocalDate fromDate, LocalDate toDate);
}
