package by.vsu;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionTestCase {
 
    @Test
    public void testDividingZeroByNumber() {
        Assert.assertEquals(
            0,
            Runner.expression(1, 5, 5),
            0.0000000001
        );
    }

    @Test
    public void testDividingBiggerNumberBySmaller() {
        Assert.assertEquals(
            2,
            Runner.expression(4, 4, 5),
            0.0000000001
        );
    }

    @Test
    public void testDividingSmallerNumberByBigger() {
        Assert.assertEquals(
            0.5,
            Runner.expression(1, 1, 2),
            0.0000000001
        );
    }

}