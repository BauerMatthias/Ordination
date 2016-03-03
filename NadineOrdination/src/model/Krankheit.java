package model;

import java.util.ArrayList;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Krankheit {

    private int nummer;
    private String beschreibung;

    public Krankheit() {
    }

    public Krankheit(int nummer, String beschreibung) {
        this.nummer = nummer;
        this.beschreibung = beschreibung;
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

    @Override
    public String toString() {
        return "Krankheit{" +
                "nummer=" + nummer +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}
