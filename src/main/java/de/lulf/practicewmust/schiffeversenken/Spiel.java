package de.lulf.practicewmust.schiffeversenken;

public class Spiel {

    Spielbrett brett;

    public Spiel(int feldgroesseX, int feldgroesseY) {
        init(feldgroesseX, feldgroesseY);
    }

    protected void init(int feldgroesseX, int feldgroesseY) {
        brett = new Spielbrett();
        brett.init(feldgroesseX, feldgroesseY);
    }

    public Spielbrett getBrett() {
        return brett;
    }


}
