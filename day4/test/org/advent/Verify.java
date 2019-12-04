package org.advent;

import org.advent.verifier.DoubleDigitVerifier;
import org.advent.verifier.OnlyDoubleDigitVerifier;
import org.advent.verifier.RisingNumbersVerifier;
import org.junit.Assert;
import org.junit.Test;

public class Verify {
    DoubleDigitVerifier ddv = new DoubleDigitVerifier();
    OnlyDoubleDigitVerifier oddv = new OnlyDoubleDigitVerifier();
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

    @Test
    public void step2() {
        int[] one = new int[]{1,1,2,2,3,3};
        Assert.assertTrue(ddv.verifyCode(one));
        Assert.assertTrue(rv.verifyCode(one));
        Assert.assertTrue(oddv.verifyCode(one));


        int[] two = new int[]{1,2,3,4,4,4};
        Assert.assertTrue(ddv.verifyCode(two));
        Assert.assertTrue(rv.verifyCode(two));
        Assert.assertFalse(oddv.verifyCode(two));


        int[] three = new int[]{1,1,1,1,2,2};
        Assert.assertTrue(ddv.verifyCode(three));
        Assert.assertTrue(rv.verifyCode(three));
        Assert.assertTrue(oddv.verifyCode(three));
    }
}
