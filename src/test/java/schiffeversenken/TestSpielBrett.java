package schiffeversenken;

import de.lulf.practicewmust.schiffeversenken.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestSpielBrett {

    @Test
    public void testInitFeldgroesse() {
        Spielbrett brett = new Spielbrett();
        Assert.assertNotNull(brett.getFelder());
        Assert.assertEquals(0,brett.getFelder().size());
        brett.init(2,2);
        ArrayList<Feld> felder = brett.getFelder();
        Assert.assertEquals(4, brett.getFelder().size());
        Assert.assertEquals(0, felder.get(0).getX());
        Assert.assertEquals(0, felder.get(0).getY());
        Assert.assertEquals(0, felder.get(1).getX());
        Assert.assertEquals(1, felder.get(1).getY());
        Assert.assertEquals(1, felder.get(2).getX());
        Assert.assertEquals(0, felder.get(2).getY());
        Assert.assertEquals(1, felder.get(3).getX());
        Assert.assertEquals(1, felder.get(3).getY());
    }

    @Test
    public void testGetFeld() throws FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        Feld testFeld = brett.getFeld(2, 1);
        Assert.assertEquals(2, testFeld.getX());
        Assert.assertEquals(1, testFeld.getY());
    }

    @Test(expected = FeldNichtAufBrettException.class)
    public void testGetFeldException() throws FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        Feld testFeld = brett.getFeld(2, 5);
    }

    @Test(expected = FeldNichtAufBrettException.class)
    public void testSchiffRagtRechtsAusSpielfeld() throws FeldNichtAufBrettException, SchiffeUeberlappenSichException {
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        brett.setzeSchiff(3, 1, 2, true);
    }

    @Test(expected = FeldNichtAufBrettException.class)
    public void testSchiffRagtUntenAusSpielfeld() throws FeldNichtAufBrettException, SchiffeUeberlappenSichException {
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        brett.setzeSchiff(3, 1, 2, false);
    }

    @Test
    public void testSchiffSetzen001() throws FeldNichtAufBrettException, SchiffeUeberlappenSichException {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        Schiff schiff = brett.setzeSchiff(3, 2, 2, true);
        ArrayList<Feld> felder = schiff.getFelder();
        Assert.assertNotNull(felder);
        Assert.assertEquals(3, felder.size());
        Assert.assertEquals(2, felder.get(0).getX());
        Assert.assertEquals(2, felder.get(0).getY());
        Assert.assertEquals(3, felder.get(1).getX());
        Assert.assertEquals(2, felder.get(1).getY());
        Assert.assertEquals(4, felder.get(2).getX());
        Assert.assertEquals(2, felder.get(2).getY());
    }

    @Test
    public void testSchiffSetzen002() throws FeldNichtAufBrettException, SchiffeUeberlappenSichException {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        Schiff schiff = brett.setzeSchiff(3, 2, 2, false);
        ArrayList<Feld> felder = schiff.getFelder();
        Assert.assertNotNull(felder);
        Assert.assertEquals(3, felder.size());
        Assert.assertEquals(2, felder.get(0).getX());
        Assert.assertEquals(2, felder.get(0).getY());
        Assert.assertEquals(2, felder.get(1).getX());
        Assert.assertEquals(3, felder.get(1).getY());
        Assert.assertEquals(2, felder.get(2).getX());
        Assert.assertEquals(4, felder.get(2).getY());
    }

    @Test(expected = FeldNichtAufBrettException.class)
    public void testSchussAusserhalbSpielfeld() throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.processSchuss(10, 11);
    }

    @Test(expected = SchiffeUeberlappenSichException.class)
    public void testSchSchiffeUeberlappenSich() throws SchiffeUeberlappenSichException, FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(4,4).setZustand(FeldZustand.SCHIFF);
        brett.setzeSchiff(2,3, 4, true);
    }

    @Test
    public void testSchussWasser() throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        Assert.assertEquals(FeldZustand.WASSER, brett.getFeld(3, 4).getZustand());
        boolean result = brett.processSchuss(3, 4);
        Assert.assertFalse(result);
        Assert.assertEquals(FeldZustand.SCHUSSWASSER, brett.getFeld(3, 4).getZustand());
    }

    @Test
    public void testSchussTreffer() throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(3,4).setZustand(FeldZustand.SCHIFF);
        Assert.assertEquals(FeldZustand.SCHIFF, brett.getFeld(3, 4).getZustand());
        boolean result = brett.processSchuss(3, 4);
        Assert.assertTrue(result);
        Assert.assertEquals(FeldZustand.SCHUSSTREFFER, brett.getFeld(3, 4).getZustand());
    }

    @Test(expected = FeldBereitsBeschossenExcpetion.class)
    public void testSchussTrefferZweimal() throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(3,4).setZustand(FeldZustand.SCHUSSTREFFER);
        brett.processSchuss(3,4);
    }

    @Test(expected = FeldBereitsBeschossenExcpetion.class)
    public void testSchussWasserZweimal() throws FeldNichtAufBrettException, FeldBereitsBeschossenExcpetion {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(3,4).setZustand(FeldZustand.SCHUSSWASSER);
        brett.processSchuss(3,4);
    }

    @Test
    public void testToString() throws FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(1, 0).setZustand(FeldZustand.SCHIFF);
        brett.getFeld(1, 1).setZustand(FeldZustand.SCHUSSWASSER);
        brett.getFeld(1, 2).setZustand(FeldZustand.SCHUSSTREFFER);
        String string = brett.toString();
        String assertString =
                "W W W W W W W W W W " + "\n" +
                "S 0 X W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n" +
                "W W W W W W W W W W " + "\n";
        Assert.assertEquals(assertString, string);
    }

    @Test
    public void testToStringCovered() throws FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(10, 10);
        brett.getFeld(1, 0).setZustand(FeldZustand.SCHIFF);
        brett.getFeld(1, 1).setZustand(FeldZustand.SCHUSSWASSER);
        brett.getFeld(1, 2).setZustand(FeldZustand.SCHUSSTREFFER);
        String string = brett.toStringCovered();
        String assertString =
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? 0 X ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n" +
                "? ? ? ? ? ? ? ? ? ? " + "\n";
        System.out.println(assertString);
        Assert.assertEquals(assertString, string);
    }




}
