package de.lulf.practicewmust.schiffeversenken;

public class Spiel {

    private Spielbrett brett1;

    private Spielbrett brett2;

    private Spieler spieler1;

    private Spieler spieler2;

    protected void init(int feldgroesseX, int feldgroesseY) {
        brett1 = new Spielbrett();
        brett1.init(feldgroesseX, feldgroesseY);
        brett2 = new Spielbrett();
        brett2.init(feldgroesseX, feldgroesseY);
    }

    public Spielbrett getBrett1() {
        return brett1;
    }

    public Spielbrett getBrett2() {
        return brett2;
    }

    public Spieler getSpieler1() {
        return spieler1;
    }

    public void setSpieler1(Spieler spieler1) {
        this.spieler1 = spieler1;
    }

    public Spieler getSpieler2() {
        return spieler2;
    }

    public void setSpieler2(Spieler spieler2) {
        this.spieler2 = spieler2;
    }
}
