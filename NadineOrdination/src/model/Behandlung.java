package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Behandlung {

    private int id;
    private Patient patient;
    private LocalDate datum;
    private ArrayList<Krankheit> krankheiten;
    private Behandlung_Beschreibung beschreibung;
    private int dauer;
    private double einnahme;
    private String bemerkung;

    public Behandlung() {
    }

    public Behandlung(int id, Patient patient, LocalDate datum, ArrayList<Krankheit> krankheiten, Behandlung_Beschreibung beschreibung, int dauer, double einnahme, String bemerkung) {
        this.id = id;
        this.patient = patient;
        this.datum = datum;
        this.krankheiten = krankheiten;
        this.beschreibung = beschreibung;
        this.dauer = dauer;
        this.einnahme = einnahme;
        this.bemerkung = bemerkung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public ArrayList<Krankheit> getKrankheiten() {
        return krankheiten;
    }

    public void setKrankheiten(ArrayList<Krankheit> krankheiten) {
        this.krankheiten = krankheiten;
    }

    public Behandlung_Beschreibung getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(Behandlung_Beschreibung beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public double getEinnahme() {
        return einnahme;
    }

    public void setEinnahme(double einnahme) {
        this.einnahme = einnahme;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    @Override
    public String toString() {
        return "Behandlung{" +
                "id=" + id +
                ", patient=" + patient +
                ", datum=" + datum +
                ", krankheiten=" + krankheiten +
                ", beschreibung=" + beschreibung +
                ", dauer=" + dauer +
                ", einnahme=" + einnahme +
                ", bemerkung='" + bemerkung + '\'' +
                '}';
    }
}
