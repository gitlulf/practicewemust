package de.lulf.practicewmust.schiffeversenken;

import java.util.ArrayList;

public class Spieler {

    private String name;

    private Spielbrett brett;

    private ArrayList<Schiff> schiffe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spielbrett getBrett() {
        return brett;
    }

    public void setBrett(Spielbrett brett) {
        this.brett = brett;
    }

    public ArrayList<Schiff> getSchiffe() {
        return schiffe;
    }

    public void setSchiffe(ArrayList<Schiff> schiffe) {
        this.schiffe = schiffe;
    }
}
