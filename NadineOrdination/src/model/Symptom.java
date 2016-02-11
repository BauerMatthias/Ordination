package model;

/**
 * Created by Matthias on 04.02.2016.
 */
public class Symptom {

    private int nummer;
    private String beschreibung;

    public Symptom() {
    }

    public Symptom(String beschreibung) {
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
        return "Symptom{" +
                "nummer=" + nummer +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}
