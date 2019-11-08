package de.lulf.practicewmust.schiffeversenken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Spielcontroller {

    private Spiel spiel;

    public static void main(String[] args) {
        Spielcontroller spielcontroller = new Spielcontroller();
        spielcontroller.starteSpiel();
    }

    private void starteSpiel() {
        System.out.println("Wie groß soll das Spielfeld sein? Gib die Höhe ein.");
        int y = readNumberFromCommandLine();
        System.out.println("Wie groß soll die Breite sein?");
        int x = readNumberFromCommandLine();
        spiel = new Spiel();
        System.out.println("Bitte gib die Schiffsgrößen an. Wie viele Schiffe sollen im Einsatz sein?");
        int[] schiffsgroessen = promptSchiffgroessen(readNumberFromCommandLine());
        System.out.println("Spieler 1, wie heißt Du?");
        Spieler spieler1 = new Spieler();
        spieler1.setName(readStringFromCommandLine(null));
        spiel.setSpieler1(spieler1);
        Spielbrett brett1 = initSpielbrett(x, y, spieler1, schiffsgroessen);
        spiel.setBrett1(brett1);
        Spieler spieler2 = new Spieler();
        System.out.println("Spieler 2, wie heißt Du?");
        spieler2.setName(readStringFromCommandLine(null));
        spiel.setSpieler2(spieler2);
        Spielbrett brett2 = initSpielbrett(x, y, spieler2, schiffsgroessen);
        spiel.setBrett2(brett2);
        while(true) {
            processSchuss(1);
            if (pruefeSieg(1)) {
                break;
            }
            processSchuss(2);
            if (pruefeSieg(2)) {
                break;
            }
        }
    }

    private boolean pruefeSieg(int schuetze) {
        Spielbrett currentBrett;
        Spieler currentSpieler;
        Spieler schuetzenSpieler;
        if (Integer.valueOf(1).equals(schuetze)) {
            currentBrett = spiel.getBrett2();
            currentSpieler = spiel.getSpieler2();
            schuetzenSpieler = spiel.getSpieler1();
        } else if (Integer.valueOf(2).equals(schuetze)) {
            currentBrett = spiel.getBrett1();
            currentSpieler = spiel.getSpieler1();
            schuetzenSpieler = spiel.getSpieler2();
        } else {
            throw new IllegalArgumentException("nur 1 oder 2 ist gültig.");
        }
        for (Schiff currentSchiff : currentSpieler.getSchiffe()) {
            for (Feld currentFeld : currentSchiff.getFelder()) {
                if (FeldZustand.SCHIFF.equals(currentFeld.getZustand())) {
                    return false;
                }
            }
        }
        System.out.println(schuetzenSpieler.getName() + " hat gewonnen. Herzlichen Glückwunsch.");
        return true;
    }

    private void processSchuss(Integer schuetze) {
        Spielbrett currentBrett;
        Spieler currentSpieler;
        if (Integer.valueOf(1).equals(schuetze)) {
            currentBrett = spiel.getBrett2();
            currentSpieler = spiel.getSpieler2();
        } else if (Integer.valueOf(2).equals(schuetze)) {
            currentBrett = spiel.getBrett1();
            currentSpieler = spiel.getSpieler1();
        } else {
            throw new IllegalArgumentException("nur 1 oder 2 ist gültig.");
        }
        System.out.println(currentSpieler.getName() + ", zum Schuss bitte. Zunächst die x-Koordinate, dann die y-Koordinate:");
        while (true) {
            int xx = readNumberFromCommandLine();
            int yy = readNumberFromCommandLine();
            try {
                if (currentBrett.processSchuss(xx, yy)) {
                    System.out.println("Treffer!");
                } else {
                    System.out.println("Wasser");
                }
                System.out.println("Das gegnerische Feld sieht jetzt wie folgt aus:");
                System.out.println(currentBrett.toStringCovered());
                return;
            } catch (FeldNichtAufBrettException e) {
                System.out.println("Der Schuss ist nicht auf dem Brett. Bitte noch einmal versuchen.");
            } catch (FeldBereitsBeschossenExcpetion feldBereitsBeschossenExcpetion) {
                System.out.println("Dieses Feld ist bereits beschossen worden. Bitte noch einmal versuchen");
            }
        }
    }

    private Spielbrett initSpielbrett(int x, int y, Spieler spieler1, int[] schiffsgroessen) {
        Spielbrett brett1 = new Spielbrett();
        brett1.init(y,x);
        ArrayList<Schiff> schiffe = new ArrayList<Schiff>();
        for (int i = 0; i < schiffsgroessen.length; i++) {
            int current = schiffsgroessen[i];
            System.out.println(spieler1.getName() + ": Dein Spielfeld sieht momentan so aus: ");
            System.out.println(brett1.toString());
            System.out.println("Wo soll Dein Schiff mit der Größe " + current + " sein? Bitte gib die y-Koordinate ein.");
            int sy = readNumberFromCommandLine()-1;
            System.out.println("Bitte gib die x-Koordinate ein:");
            int sx = readNumberFromCommandLine()-1;
            System.out.println("Schwimmt das Schiff horizontal (h) oder vertikal (v)");
            String vertical = readStringFromCommandLine("h", "v");
            try {
                schiffe.add(brett1.setzeSchiff(current, sy, sx, "v".equals(vertical)));
            } catch (FeldNichtAufBrettException e) {
                System.out.println("Das Schiff ragt über den Spielfeldrand hinaus. Bitte nochmal setzen.");
                i--;
            } catch (SchiffeUeberlappenSichException e) {
                System.out.println("Das Schiff überlappt sich mit einem anderen Schiff. Bitte nochmal setzen.");
                i--;
            }
        }
        spieler1.setSchiffe(schiffe);
        System.out.println(spieler1.getName() + ", präge Dir Dein Spielfeld gut ein. Ab jetzt werden nur die beschossenen Felder angezeigt.");
        System.out.println(brett1.toString());
        return  brett1;
    }

    private Integer readNumberFromCommandLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer i = null;
        while (i == null) {
            try {
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException nex) {
                System.out.println("Bitte gib eine gültige Zahl ein.");
            } catch (IOException e) {
                System.out.println("Bitte gib eine gültige Zahl ein.");
            } }
        return null;
    }

    private String readStringFromCommandLine(String ... limitedTo) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String i = null;
        while (i == null) {
            try {
                String readString = br.readLine();

                if (limitedTo == null || Arrays.asList(limitedTo).contains(readString)) {
                    return readString;
                } else {
                    System.out.println("Ungültige Eingabe.");
                }
            } catch (IOException e) {
                System.out.println("oopsi .... Bitte nochmal probieren.");
            } }
        return null;
    }

    private int[] promptSchiffgroessen(int anzahl) {
        int[] groessen = new int[anzahl];
        for (int i = 0; i < anzahl; i++) {
            System.out.println("Wie groß soll Schiff " + (i + 1) + " sein?");
            groessen[i] = readNumberFromCommandLine();
        }
        return groessen;
    }



}
