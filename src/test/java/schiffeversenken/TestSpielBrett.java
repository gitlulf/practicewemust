package schiffeversenken;

import de.lulf.practicewmust.schiffeversenken.Feld;
import de.lulf.practicewmust.schiffeversenken.Spielbrett;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestSpiel {

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

}
