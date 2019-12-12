package org.advent;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCodes;
import org.junit.Assert;
import org.junit.Test;


public class UtilTests {

    @Test
    public void toOps() {
        String input = "1,0,0,0,99";
        IntCodes ops = new IntCodes(input);
        Assert.assertEquals(1, ops.get(0));
        Assert.assertEquals(0, ops.get(1));
        Assert.assertEquals(0, ops.get(2));
        Assert.assertEquals(0, ops.get(3));
        Assert.assertEquals(99, ops.get(4));
    }

    @Test
    public void toOps2() {
        String input = "2,3,0,3,99";
        IntCodes ops = new IntCodes(input);
        Assert.assertEquals(2, ops.get(0));
        Assert.assertEquals(3, ops.get(1));
        Assert.assertEquals(0, ops.get(2));
        Assert.assertEquals(3, ops.get(3));
        Assert.assertEquals(99, ops.get(4));
    }

    @Test
    public void testLCM() {
        Assert.assertEquals(36, Util.lcm(12, 18));
    }
}
