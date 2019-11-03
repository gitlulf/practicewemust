package de.lulf.practicewmust.schiffeversenken;

public class Feld {

    private int x;
    private int y;
    private FeldZustand zustand;

    public Feld(int i, int j) {
        x = i;
        y = j;
    }

    /**
     * Ein Feld isGleicheLage, wenn x und y übereinstimmen.
     * @param that
     * @return ob that die gleiche Lage hat wie this.
     */
    public boolean isGleicheLage(Feld that) {
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Ein Feld isGleicheLage wenn x und y übereinstimmen
     * @param x gefragte x - Koordinate
     * @param y gefragte y - Koordinate
     * @return ob das Feld die gleiche x - und y-Lage wie die Parameter hat.
     */
    public boolean isGleicheLage(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FeldZustand getZustand() {
        return zustand;
    }

    public void setZustand(FeldZustand zustand) {
        this.zustand = zustand;
    }
}
