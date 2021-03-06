package org.advent.code;

import org.advent.util.code.Counter;
import org.advent.util.code.verifier.CodeVerifier;
import org.advent.util.code.verifier.DoubleDigitVerifier;
import org.advent.util.code.verifier.MaxFourVerifier;
import org.advent.util.code.verifier.MinFiveVerifier;
import org.advent.util.code.verifier.NoSameNumberVerifier;
import org.advent.util.code.verifier.OnlyDoubleDigitVerifier;
import org.advent.util.code.verifier.RisingNumbersVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CounterTests {



    @Test
    public void day4endTest() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,1,1,1,1};
        int[] numbers2 = new int[]{1,1,1,1,1,1};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4endTestFalse() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,1,1,1,1};
        int[] numbers2 = new int[]{1,1,1,1,1,2};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
    }

    @Test
    public void day4nextNumber1() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,1,1,1,9};
        int[] numbers2 = new int[]{1,1,1,1,2,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4nextNumber2() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,1,1,9,9};
        int[] numbers2 = new int[]{1,1,1,2,0,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4nextNumber3() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,1,9,9,9};
        int[] numbers2 = new int[]{1,1,2,0,0,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4nextNumber4() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,1,9,9,9,9};
        int[] numbers2 = new int[]{1,2,0,0,0,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4nextNumber5() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,9,9,9,9,9};
        int[] numbers2 = new int[]{2,0,0,0,0,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4nextNumber6() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());
        verifiers.add(new OnlyDoubleDigitVerifier());

        int[] numbers1 = new int[]{1,9,9,9,9,9};
        int[] numbers2 = new int[]{2,0,0,0,0,2};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isDone());
        c.nextNumber();
        c.nextNumber();
        c.nextNumber();
        Assert.assertTrue(c.isDone());
    }

    @Test
    public void day4isNumberOk() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new DoubleDigitVerifier());
        verifiers.add(new RisingNumbersVerifier());

        int[] numbers1 = new int[]{1,1,1,1,1,9};
        int[] numbers2 = new int[]{1,1,1,1,2,0};
        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertTrue(c.isCurrentNumberOk());
        c.nextNumber();
        Assert.assertFalse(c.isCurrentNumberOk());
    }

    @Test
    public void day7Max4False() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new MaxFourVerifier());
        int[] numbers1 = new int[]{1,1,1,1,9};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isCurrentNumberOk());
    }

    @Test
    public void day7Max4True() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new MaxFourVerifier());
        int[] numbers1 = new int[]{1,1,1,1,4};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertTrue(c.isCurrentNumberOk());
    }

    @Test
    public void day7notSameFalse() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new NoSameNumberVerifier());
        int[] numbers1 = new int[]{0,1,2,3,1};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isCurrentNumberOk());

    }

    @Test
    public void day7notSameTrue() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new NoSameNumberVerifier());
        int[] numbers1 = new int[]{0,1,2,3,4};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertTrue(c.isCurrentNumberOk());

    }

    @Test
    public void day7minFiveFalse() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new MinFiveVerifier());
        int[] numbers1 = new int[]{5,6,7,9,0};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertFalse(c.isCurrentNumberOk());
    }
    @Test

    public void day7minFiveTrue() {
        List<CodeVerifier> verifiers = new ArrayList<>();
        verifiers.add(new MinFiveVerifier());
        int[] numbers1 = new int[]{5,6,7,9,8};
        int[] numbers2 = new int[]{1,1,1,1,0};

        Counter c = new Counter(numbers1, numbers2, verifiers);
        Assert.assertTrue(c.isCurrentNumberOk());
    }
}
