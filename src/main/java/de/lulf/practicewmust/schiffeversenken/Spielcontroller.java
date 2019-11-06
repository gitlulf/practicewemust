package de.lulf.practicewmust.schiffeversenken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spielcontroller {

    private Spiel spiel;

    public static void main(String[] args) {
        Spielcontroller spielcontroller = new Spielcontroller();
        spielcontroller.starteSpiel();
    }

    private void starteSpiel() {
        System.out.println("Wie groß soll das Spielfeld sein? Gib die Breite ein.");
        int x = readNumberFromCommandLine();
        System.out.println("Wie groß soll die Höhe sein?");
        int y = readNumberFromCommandLine();
        spiel = new Spiel();
        System.out.println("Bitte gib die Schiffsgrößen an. Wie viele Schiffe sollen im Einsatz sein?");
        promptSchiffgroessen(readNumberFromCommandLine());
        System.out.println("Spieler 1, wie heißt Du?");
        String nameSpieler1 = readStringFromCommandLine();

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

    private String readStringFromCommandLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String i = null;
        while (i == null) {
            try {
                return br.readLine();
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
