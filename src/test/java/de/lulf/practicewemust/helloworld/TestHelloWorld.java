package de.lulf.practicewemust.helloworld;

import org.junit.Assert;
import org.junit.Test;

public class TestHelloWorld {

    @Test
    public void test1plus1() {
        Assert.assertEquals(1, 1);
        HelloWorld helloworld = new HelloWorld();
        Assert.assertEquals(2, helloworld.addiere(1,1));
        //
    }
}
