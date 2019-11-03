package de.lulf.practicewmust.schiffeversenken;

/**
 * Wird immer geworfen, wenn ein Feld angefragt wird, das nicht auf dem Brett ist.
 */
public class FeldNichtAufBrettException extends Exception {

    FeldNichtAufBrettException(String message) {
        super(message);
    }
}
