package model;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Patient {

    private int id;
    private String vorname;
    private String nachname;
    private String adresse;
    private LocalDate gebDatum;
    private String telNummer;
    private ArrayList<Krankheit> krankheiten;
    private double tarif;

    public Patient() {
    }

    public Patient(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Patient(int id, String vorname, String nachname, String adresse, LocalDate gebDatum, String telNummer, double tarif) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gebDatum = gebDatum;
        this.telNummer = telNummer;
        this.tarif = tarif;
    }

    public Patient(int id, String vorname, String nachname, String adresse, LocalDate gebDatum, String telNummer, ArrayList<Krankheit> krankheiten, double tarif) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.gebDatum = gebDatum;
        this.telNummer = telNummer;
        this.krankheiten = krankheiten;
        this.tarif = tarif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(LocalDate gebDatum) {
        this.gebDatum = gebDatum;
    }

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }

    public ArrayList<Krankheit> getKrankheiten() {
        return krankheiten;
    }

    public void setKrankheiten(ArrayList<Krankheit> krankheiten) {
        this.krankheiten = krankheiten;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return vorname + " " + nachname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (id != patient.id) return false;
        if (vorname != null ? !vorname.equals(patient.vorname) : patient.vorname != null) return false;
        return !(nachname != null ? !nachname.equals(patient.nachname) : patient.nachname != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        return result;
    }
}
