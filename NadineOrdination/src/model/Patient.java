package model;

import java.util.ArrayList;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Patient {

    private int nummer;
    private String vorname;
    private String nachname;
    private String adresse;
    private String telNummer;
    private ArrayList<Krankheit> krankheiten;

    public Patient(int nummer, String vorname, String nachname, String adresse, String telNummer) {
        this.nummer = nummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.telNummer = telNummer;
    }

    public Patient() {
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
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

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "nummer=" + nummer +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telNummer='" + telNummer + '\'' +
                ", krankheiten=" + krankheiten +
                '}';
    }
}
