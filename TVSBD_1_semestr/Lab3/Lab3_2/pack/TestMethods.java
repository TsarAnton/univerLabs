package pack;

import org.junit.Test;
import org.junit.Assert;

public class TestMethods {
 
    @Test
    public void testIsRussianSymbol1() {
        Assert.assertEquals(
            true,
            Updater.isRussianSymbol('а')
        );
    }

    @Test
    public void testIsRussianSymbol2() {
        Assert.assertEquals(
            false,
            Updater.isRussianSymbol('v')
        );
    }

    @Test
    public void testCountOfSymbol() {
        Assert.assertEquals(
            3,
            new Updater("nanana").countOfSymbol('a')
        );
    }

}

