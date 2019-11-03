package de.lulf.practicewmust.schiffeversenken;

import java.util.ArrayList;

public class Schiff {

    public Schiff(int laenge) {
        this.laenge = laenge;
    }

    private int laenge;

    private ArrayList<Feld> felder = new ArrayList<Feld>();

    public int getLaenge() {
        return laenge;
    }

    public void addFeld(Feld currentFeld) {
        felder.add(currentFeld);
    }

    public ArrayList<Feld> getFelder() {
        return felder;
    }
}
