package by;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionTestCase {
 
    @Test
    public void testFactorial() {
        Assert.assertEquals(
            120,
            Runner.factorial(5),
            0.0000000001
        );
    }

    @Test
    public void testFactorialZero() {
        Assert.assertEquals(
            1,
            Runner.factorial(0),
            0.0000000001
        );
    }

    @Test
    public void testFive() {
        Assert.assertEquals(
            Math.pow(Math.E, 5),
            Runner.expression(5, 0.0001),
            0.0001
        );
    }

    @Test
    public void testFour() {
        Assert.assertEquals(
            Math.pow(Math.E, 4),
            Runner.expression(4, 0.0001),
            0.0001
        );
    }

}