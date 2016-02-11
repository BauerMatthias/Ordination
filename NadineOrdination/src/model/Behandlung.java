package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Behandlung {

    private int nummer;
    private Patient patient;
    private LocalDate datum;
    private ArrayList<Krankheit> krankheiten;

    public Behandlung() {
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
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

    @Override
    public String toString() {
        return "Behandlung{" +
                "nummer=" + nummer +
                ", patient=" + patient +
                ", datum=" + datum +
                ", krankheiten=" + krankheiten +
                '}';
    }
}
