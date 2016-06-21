package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Matthias on 11.06.2016.
 */
public class Statistik_Daten {

    private LocalDateTime date;
    private Double wert;
    private Patient p;

    public Statistik_Daten(LocalDateTime date, Double wert, Patient p) {
        this.date = date;
        this.wert = wert;
        this.p = p;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getWert() {
        return wert;
    }

    public void setWert(Double wert) {
        this.wert = wert;
    }

    public Patient getP() {
        return p;
    }

    public void setP(Patient p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return  date + "/" + wert + "/" + p;
    }
}
