package de.lulf.practicewmust.schiffeversenken;

public class Spielcontroller {

    private Spiel spiel;

    private int feldgroesseX = 12;
    private int feldgroesseY = 12;

    public void init() {
        Spiel spiel = new Spiel(feldgroesseX, feldgroesseY);

    }


}
