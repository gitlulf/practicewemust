package de.lulf.practicewmust.schiffeversenken;

import java.util.ArrayList;

public class Spielbrett {

    ArrayList<Feld> felder = new ArrayList<Feld>();

    public ArrayList<Feld> getFelder() {
        return felder;
    }

    /**
     * Initialisiert ein Spielbrett mit x Spalten und y Zeilen
     * @param feldgroesseX Anzahl der Spalten
     * @param feldgroesseY Anzahl der Zeilen
     */
    public void init(int feldgroesseX, int feldgroesseY) {
        for (int i = 0; i< feldgroesseX; i++ ) {
            for (int j = 0; j < feldgroesseY; j++) {
                Feld feld = new Feld(i, j);
                feld.setZustand(FeldZustand.WASSER);
                felder.add(feld);
            }
        }
    }

    /**
     * Setzt ein Schiff auf das Spielfeld
     * @param laenge Länge des Schiffs
     * @param xStart Startpunkt in x- Koordinate
     * @param yStart Startpunkt y Koordinate
     * @param horizontal horizontal oder nicht (vertikal)
     * @return Das Schiff auf dem Spielbrett
     * @throws FeldNichtAufBrettException wenn das Schiff über das Spielfeld hinausragt
     */
    public Schiff setzeSchiff(int laenge, int xStart, int yStart, boolean horizontal) throws FeldNichtAufBrettException {
        ArrayList<Feld> felderMitNeuemSchiff = new ArrayList<Feld>();
        try {
            for(int i = 0; i < laenge; i++) {
                Feld currentFeld;
                if (horizontal) {
                    currentFeld = getFeld(xStart + i, yStart);
                } else {
                    currentFeld = getFeld(xStart, yStart + i);
                }
                felderMitNeuemSchiff.add(currentFeld);
            }
        } catch (FeldNichtAufBrettException f) {
            throw new FeldNichtAufBrettException("Schiff ragt über Spielfeld hinaus.");
        }
        Schiff schiff = new Schiff(laenge);
        for (Feld currentFeld : felderMitNeuemSchiff) {
            currentFeld.setZustand(FeldZustand.SCHIFF);
            schiff.addFeld(currentFeld);
        }
        return schiff;
    }

    /**
     * Gibt das Feld mit den gewünnschten Koordinaten zurück
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @return das Feld mit den gewünschten Koordinaten
     * @throws FeldNichtAufBrettException wenn ein Feld angefragt wird, das sich nicht auf dem Spielfeld befindet.
     */
    public Feld getFeld(int x, int y) throws FeldNichtAufBrettException {
        for (Feld f : felder) {
            if (f.isGleicheLage(x, y )) {
                return f;
            }
        }
        throw new FeldNichtAufBrettException("Feld mit den Koordinaten " + x + "/" + y + " existiert nicht auf dem Spielbrett.");
    }


}
