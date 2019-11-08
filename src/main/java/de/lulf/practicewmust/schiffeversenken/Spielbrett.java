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
     * @param vertical vertical oder nicht (vertikal)
     * @return Das Schiff auf dem Spielbrett
     * @throws FeldNichtAufBrettException wenn das Schiff über das Spielfeld hinausragt
     */
    public Schiff setzeSchiff(int laenge, int xStart, int yStart, boolean vertical)
            throws FeldNichtAufBrettException, SchiffeUeberlappenSichException {
        ArrayList<Feld> felderMitNeuemSchiff = new ArrayList<Feld>();
        try {
            for(int i = 0; i < laenge; i++) {
                Feld currentFeld;
                if (vertical) {
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
            if (FeldZustand.SCHIFF.equals(currentFeld.getZustand())) {
                throw new SchiffeUeberlappenSichException("Ein Feld soll auf 'Schiff gesetzt werden' wo bereits 'Schiff' ist");
            }
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

    /**
     * Verarbeitet einen Schuss auf das Brett
     * @param x x-Koordinate des Schusses
     * @param y y-Korrdinate des Schusses
     * @return true bei einem Treffer, sonst false
     */
    public boolean processSchuss(int x, int y) throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Feld feld = getFeld(x,y);
        if (FeldZustand.SCHIFF.equals(feld.getZustand())) {
            feld.setZustand(FeldZustand.SCHUSSTREFFER);
            return true;
        } else if (FeldZustand.WASSER.equals(feld.getZustand())) {
            feld.setZustand(FeldZustand.SCHUSSWASSER);
            return false;
        } else {
            throw new FeldBereitsBeschossenExcpetion("Das Feld " + x + ", " + y + " ist bereits beschossen worden.");
        }
    }

    /**
     *
     * @return eine String-Repräsention des Spielbrettes mit den Zuständen der Felder
     */
    public String toString() {
        return toStringFlexibel(false);
    }

    /**
     *
     * @return eine String-Repräsentation des Spielbrettes mit den Zuständen der bereits beschossenen Felder. Die
     * noch nicht beschossenen Felder werden als '?' dargestellt.
     */
    public String toStringCovered() {
        return toStringFlexibel(true);
    }

    /**
     *
     * @param covered wenn ja, sind die noch nicht beschossenen Felder mit einem '?' verdeckt, die beschossenen nicht
     * @return Eine String-Repräsentation des Spielbretts, je nach Parameter 'covered' mit oder ohne abgedeckten Feldern
     */
    private String toStringFlexibel(boolean covered) {
        String theString = "";
        int currentX = 0;
        for (Feld feld : felder) {
            if (currentX < feld.getX()) {
                theString = theString + "\n";
            }
            currentX = feld.getX();
            theString = theString + uebersetzeZustand(feld.getZustand(), covered) + " ";
        }
        theString = theString + "\n";
        return theString;
    }

    /**
     * Übersetzt den Zustand der Felder in eine String-Repräsentation
     * @param f der Feldzustand, der übersetzt werden soll
     * @param covered wenn ja, sind die noch nicht beschossenen Felder durch ein '? ersetzt, sonst nicht.
     * @return den String, der den Feldzustand repräsentiert.
     */
    private String uebersetzeZustand(FeldZustand f, boolean covered) {
        if (FeldZustand.SCHUSSWASSER.equals(f)) {
            return "0";
        }
        if (FeldZustand.SCHUSSTREFFER.equals(f)) {
            return "X";
        }
        if (FeldZustand.WASSER.equals(f)) {
            if (covered) {
                return "?";
            } else {
                return "W";
            }
        }
        if (FeldZustand.SCHIFF.equals(f)) {
            if (covered) {
                return "?";
            } else {
                return "S";
            }
        }
        return "?";
    }
}
