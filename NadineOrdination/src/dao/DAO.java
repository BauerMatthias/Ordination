package dao;

import model.Behandlung;
import model.Krankheit;
import model.Patient;

import java.util.ArrayList;

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
}
