package de.lulf.practicewmust.schiffeversenken;

/**
 * Enum mit den verschiedenen Zuständen von Spielfeldern
 */
public enum FeldZustand {
    // Feld ohne Schiff, noch nicht beschossen
    WASSER,
    // Feld mit Schiff, noch nicht beschossen
    SCHIFF,
    // Feld mit Wasser, beschossen
    SCHUSSWASSER,
    // Feld mit Schiff, beschossen
    SCHUSSTREFFER;
}
