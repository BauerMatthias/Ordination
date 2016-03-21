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
    private String beschreibung;
    private double einnahme;
    private String bemerkung;

    public Behandlung() {
    }

    public Behandlung(int id, Patient patient, LocalDate datum, String beschreibung, double einnahme, String bemerkung) {
        this.id = id;
        this.patient = patient;
        this.datum = datum;
        this.beschreibung = beschreibung;
        this.einnahme = einnahme;
        this.bemerkung = bemerkung;
    }

    public Behandlung(int id, Patient patient, LocalDate datum, ArrayList<Krankheit> krankheiten, String beschreibung, double einnahme, String bemerkung) {
        this.id = id;
        this.patient = patient;
        this.datum = datum;
        this.krankheiten = krankheiten;
        this.beschreibung = beschreibung;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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
                ", einnahme=" + einnahme +
                ", bemerkung='" + bemerkung + '\'' +
                '}';
    }
}
