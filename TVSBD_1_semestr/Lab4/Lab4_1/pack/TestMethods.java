package pack;

import org.junit.Test;
import org.junit.Assert;

public class TestMethods {
 
    @Test
    public void testGetData() {
        String data = "data";
        Node<String> node = new Node<String>(data);
        Assert.assertEquals(
            data,
            node.getData()
        );
    }

    @Test
    public void testStackPop() {
        Stack<String> stack = new Stack<String>(3);
        stack.push("a");
        stack.push("b");
        String c = "c";
        stack.push(c);
        Assert.assertEquals(
            c,
            stack.pop()
        );
    }

    @Test
    public void testStackIsEmpty1() {
        Stack<String> stack = new Stack<String>(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        Assert.assertEquals(
            false,
            stack.isEmpty()
        );
    }

    @Test
    public void testStackIsEmpty2() {
        Stack<String> stack = new Stack<String>(3);
        Assert.assertEquals(
            true,
            stack.isEmpty()
        );
    }

    @Test
    public void testStackisFull1() {
        Stack<String> stack = new Stack<String>(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        Assert.assertEquals(
            true,
            stack.isFull()
        );
    }

    @Test
    public void testStackIsFull2() {
        Stack<String> stack = new Stack<String>(3);
        stack.push("a");
        stack.push("b");
        Assert.assertEquals(
            false,
            stack.isFull()
        );
    }

    @Test
    public void testIsWashing1() {
        Washer washer = new Washer();
        Plate plate = new Plate("red", 1, 1);
        washer.plate = plate;
        Assert.assertEquals(
            true,
            washer.isWashing()
        );
    }

    @Test
    public void testIsWashing2() {
        Assert.assertEquals(
            false,
            new Washer().isWashing()
        );
    }

    @Test
    public void testQueuePop() {
        Queue<String> queue = new Queue<String>();
        String a = "a";
        queue.push(a);
        queue.push("b");
        queue.push("c");
        Assert.assertEquals(
            a,
            queue.pop()
        );
    }

    @Test
    public void testQueuesafePop() {
        Queue<String> queue = new Queue<String>();
        String a = "a";
        queue.push(a);
        queue.push("b");
        queue.push("c");
        Assert.assertEquals(
            a,
            queue.safePop()
        );
    }

    @Test
    public void testQueueIsEmpty1() {
        Queue<String> queue = new Queue<String>();
        String a = "a";
        queue.push(a);
        queue.push("b");
        queue.push("c");
        Assert.assertEquals(
            false,
            queue.isEmpty()
        );
    }

    @Test
    public void testQueueIsEmpty2() {
        Queue<String> queue = new Queue<String>();
        Assert.assertEquals(
            true,
            queue.isEmpty()
        );
    }

}


