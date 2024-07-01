package src;

import org.junit.Test;
import org.junit.Assert;

public class TestMethods {
 
    @Test
    public void testIsPossiblePriceChecked() {
        Assert.assertEquals(
            false,
            new Buyer(new int[3],3).isPossiblePriceChecked(2)
        );
    }

    @Test
    public void testIsPossiblePriceSaved() {
        Assert.assertEquals(
            false,
            new Buyer(new int[5], 5).isPossiblePriceChecked(3)
        );
    }

    @Test
    public void testIsClearMoneyToPay() {
        Assert.assertEquals(
            true,
            new Person(new int[5], 5).isClearMoneyToPay(3)
        );
    }
}
