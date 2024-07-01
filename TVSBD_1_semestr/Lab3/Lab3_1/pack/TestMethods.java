package pack;

import org.junit.Test;
import org.junit.Assert;

public class TestMethods {
 
    @Test
    public void testCompare11() {
        Assert.assertEquals(
            -1,
            new StringComparator1("sa").compare("asa", "aasa")
        );
    }

    @Test
    public void testCompare12() {
        Assert.assertEquals(
            1,
            new StringComparator1("sa").compare("aaasa", "sa")
        );
    }

    @Test
    public void testCompare13() {
        Assert.assertEquals(
            0,
            new StringComparator1("sa").compare("asa", "asa")
        );
    }

    @Test
    public void testCompare21() {
        Assert.assertEquals(
            1,
            new StringComparator2().compare("aaa", "AAA")
        );
    }

    @Test
    public void testCompare22() {
        Assert.assertEquals(
            -1,
            new StringComparator2().compare("aaaAAAAAAAA", "aaaaaAAA")
        );
    }

    @Test
    public void testCompare23() {
        Assert.assertEquals(
            0,
            new StringComparator2().compare("AAA", "AAA")
        );
    }

    @Test
    public void testSymbolCount() {
        Assert.assertEquals(
            2,
            new SymbolCount().countOfSmallChars("aAaAA")
        );
    }

}

