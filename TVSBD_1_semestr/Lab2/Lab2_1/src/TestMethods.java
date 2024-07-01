package src;

import org.junit.Test;
import org.junit.Assert;



public class TestMethods {
 
    @Test
    public void test1FindPoint() {
        Assert.assertEquals(
            new Point(0, 3),
            Parallelogram.findPoint(new Point(0, 0), new Point(2, 2), new Point(2, 5))
        );
    }

    @Test
    public void test2FindPoint() {
        Assert.assertEquals(
            new Point(4, 7),
            Parallelogram.findPoint(new Point(2, 2), new Point(0, 0), new Point(2, 5))
        );
    }

    @Test
    public void test1GetMiddlePoint() {
        Assert.assertEquals(
            new Point(3, 3),
            new Segment(new Point(2, 2), new Point(4, 4)).getMiddlePoint()
        );
    }

    @Test
    public void test2GetMiddlePoint() {
        Assert.assertEquals(
            new Point(0, 0),
            new Segment(new Point(-5, -5), new Point(5, 5)).getMiddlePoint()
        );
    }

}