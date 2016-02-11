package model;

import java.util.ArrayList;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Krankheit {

    private int nummer;
    private String beschreibung;
    private ArrayList<Symptom> symptome;

    public Krankheit() {
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public ArrayList<Symptom> getSymptome() {
        return symptome;
    }

    public void setSymptome(ArrayList<Symptom> symptome) {
        this.symptome = symptome;
    }

    @Override
    public String toString() {
        return "Krankheit{" +
                "nummer=" + nummer +
                ", beschreibung='" + beschreibung + '\'' +
                ", symptome=" + symptome +
                '}';
    }
}
