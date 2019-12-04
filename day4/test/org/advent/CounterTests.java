package org.advent;

import org.junit.Assert;
import org.junit.Test;

public class CounterTests {

    @Test
    public void endTest() {
        int[] numbers1 = new int[]{1,1,1,1,1,1};
        int[] numbers2 = new int[]{1,1,1,1,1,1};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void endTestFalse() {
        int[] numbers1 = new int[]{1,1,1,1,1,1};
        int[] numbers2 = new int[]{1,1,1,1,1,2};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
    }

    @Test
    public void nextNumber1() {
        int[] numbers1 = new int[]{1,1,1,1,1,9};
        int[] numbers2 = new int[]{1,1,1,1,2,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void nextNumber2() {
        int[] numbers1 = new int[]{1,1,1,1,9,9};
        int[] numbers2 = new int[]{1,1,1,2,0,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void nextNumber3() {
        int[] numbers1 = new int[]{1,1,1,9,9,9};
        int[] numbers2 = new int[]{1,1,2,0,0,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void nextNumber4() {
        int[] numbers1 = new int[]{1,1,9,9,9,9};
        int[] numbers2 = new int[]{1,2,0,0,0,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void nextNumber5() {
        int[] numbers1 = new int[]{1,9,9,9,9,9};
        int[] numbers2 = new int[]{2,0,0,0,0,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void nextNumber6() {
        int[] numbers1 = new int[]{1,9,9,9,9,9};
        int[] numbers2 = new int[]{2,0,0,0,0,2};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        c.nextNumber();
        c.nextNumber();
    //    c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void isNUmberOk() {
        int[] numbers1 = new int[]{1,1,1,1,1,9};
        int[] numbers2 = new int[]{1,1,1,1,2,0};
        Counter c = new Counter(numbers1, numbers2);
        Assert.assertTrue(c.isCurrentNumberOk());
        c.nextNumber();
        Assert.assertFalse(c.isCurrentNumberOk());
    }
}
