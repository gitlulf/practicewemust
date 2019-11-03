package schiffeversenken;

import de.lulf.practicewmust.schiffeversenken.Feld;
import de.lulf.practicewmust.schiffeversenken.FeldNichtAufBrettException;
import de.lulf.practicewmust.schiffeversenken.Schiff;
import de.lulf.practicewmust.schiffeversenken.Spielbrett;
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
    public void schiffRagtRechtsAusSpielfeld() throws FeldNichtAufBrettException{
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        brett.setzeSchiff(3, 1, 2, true);
    }

    @Test(expected = FeldNichtAufBrettException.class)
    public void schiffRagtUntenAusSpielfeld() throws FeldNichtAufBrettException {
        Spielbrett brett = new Spielbrett();
        brett.init(3, 3);
        brett.setzeSchiff(3, 1, 2, false);
    }

    @Test
    public void schiffSetzen001() throws FeldNichtAufBrettException {
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
    public void schiffSetzen002() throws FeldNichtAufBrettException {
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



}
