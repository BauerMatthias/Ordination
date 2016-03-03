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
        return "Patient{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse='" + adresse + '\'' +
                ", gebDatum=" + gebDatum +
                ", telNummer='" + telNummer + '\'' +
                ", krankheiten=" + krankheiten +
                ", tarif=" + tarif +
                '}';
    }
}
