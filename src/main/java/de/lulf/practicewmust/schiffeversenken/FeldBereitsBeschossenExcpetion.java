package de.lulf.practicewmust.schiffeversenken;

/**
 * Wird geworfen beim Versuch, ein bereits beschossenes Feld noch einmal zu beschießen.
 */
public class FeldBereitsBeschossenExcpetion extends Exception {
    public FeldBereitsBeschossenExcpetion(String message) {
        super (message);
    }
}
