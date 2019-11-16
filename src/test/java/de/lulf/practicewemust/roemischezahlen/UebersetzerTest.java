package de.lulf.practicewemust.roemischezahlen;

import org.junit.Assert;
import org.junit.Test;

public class UebersetzerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentNull(){
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument(){
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument2(){
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("I3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument3(){
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("XXXX3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinus5NotAllowed() {
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("VX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinusKleiner1ProzentNotAllowed() {
        Uebersetzer t = new Uebersetzer();
        t.uebersetzeRoemischeZahl("IC");
    }

    @Test
    public void test4() {
        Uebersetzer q = new Uebersetzer();
        Assert.assertEquals(4,q.uebersetzeRoemischeZahl("IV"));
    }

    @Test
    public void test999() {
        Uebersetzer q = new Uebersetzer();
        Assert.assertEquals(999,q.uebersetzeRoemischeZahl(""));
    }

    @Test
    public void testEinfacheZahl() {
        Uebersetzer q = new Uebersetzer();
        Assert.assertEquals(1,q.uebersetzeRoemischeZahl("I"));
        Assert.assertEquals(5,q.uebersetzeRoemischeZahl("V"));
        Assert.assertEquals(101,q.uebersetzeRoemischeZahl("CI"));
        Assert.assertEquals(1001,q.uebersetzeRoemischeZahl("MI"));

    }

}