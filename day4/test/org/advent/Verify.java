package org.advent;

import org.advent.verifier.DoubleDigitVerifier;
import org.advent.verifier.RisingNumbersVerifier;
import org.junit.Assert;
import org.junit.Test;

public class Verify {
    DoubleDigitVerifier ddv = new DoubleDigitVerifier();
    RisingNumbersVerifier rv = new RisingNumbersVerifier();

    @Test
    public void doubleTest() {
        int[] numbers = new int[]{1,1,1,1,1,1};
        Assert.assertTrue(ddv.verifyCode(numbers));
        Assert.assertTrue(rv.verifyCode(numbers));
    }

    @Test
    public void risingFalseTest() {
        int[] numbers = new int[]{2,2,3,4,5,0};
        Assert.assertTrue(ddv.verifyCode(numbers));
        Assert.assertFalse(rv.verifyCode(numbers));
    }

    @Test
    public void doubleFalse() {
        int[] numbers = new int[]{1,2,3,7,8,9};
        Assert.assertFalse(ddv.verifyCode(numbers));
        Assert.assertTrue(rv.verifyCode(numbers));
    }
}
