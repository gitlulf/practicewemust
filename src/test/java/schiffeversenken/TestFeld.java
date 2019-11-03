package schiffeversenken;

import de.lulf.practicewmust.schiffeversenken.Feld;
import org.junit.Assert;
import org.junit.Test;

public class TestFeld {

    @Test
    public void testisGleicheLage() {
        Feld f1 = new Feld(1, 2);
        Assert.assertTrue(f1.isGleicheLage(new Feld(1,2)));
        Assert.assertFalse(f1.isGleicheLage(new Feld(2, 1)));
    }

    @Test
    public void testisGleicheLage2() {
        Feld f1 = new Feld(1, 2);
        Assert.assertTrue(f1.isGleicheLage(1,2));
        Assert.assertFalse(f1.isGleicheLage(2,1));
    }
}
