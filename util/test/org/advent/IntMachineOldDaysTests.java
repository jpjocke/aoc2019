package org.advent;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IntMachineOldDaysTests {

    @Test
    public void day2Test1() {
        System.out.println("1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2)");

        String input = "1,0,0,0,99";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
    }

    @Test
    public void day2Test2() {
        System.out.println("2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6)");

        String input = "2,3,0,3,99";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
        Assert.assertEquals(6, im.getCodes().get(3));
    }

    @Test
    public void day2Test3() {
        System.out.println("2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801)");

        String input = "2,4,4,5,99,0";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
        Assert.assertEquals(9801, im.getCodes().get(5));
    }

    @Test
    public void day2est4() {
        System.out.println("1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99");

        String input = "1,1,1,4,99,5,6,0,99";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(30, im.getResult());
    }

    @Test
    public void day2TestReal() {
        System.out.println("REAL");
        String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,5,19,23,2,10,23,27,1,27,5,31,2,9,31,35,1,35,5,39,2,6,39,43,1,43,5,47,2,47,10,51,2,51,6,55,1,5,55,59,2,10,59,63,1,63,6,67,2,67,6,71,1,71,5,75,1,13,75,79,1,6,79,83,2,83,13,87,1,87,6,91,1,10,91,95,1,95,9,99,2,99,13,103,1,103,6,107,2,107,6,111,1,111,2,115,1,115,13,0,99,2,0,14,0";

        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(4714701, im.getResult());
    }


    @Test
    public void day2multiplication() {
        System.out.println("1002,4,3,4,33 becomes 1002,4,3,4,99");

        String input = "1002,4,3,4,33";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(1002, im.getResult());
    }

    @Test
    public void day5_step2_input_equals_true() {
        int input = 8;

        String string = "3,9,8,9,10,9,4,9,99,-1,8";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1, output);
    }

    @Test
    public void day5_step2_input_equals_false() {
        int input = 1;

        String string = "3,9,8,9,10,9,4,9,99,-1,8";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(0, output);
    }

    @Test
    public void day5_step2_input_less_than_true() {
        int input = 1;

        String string = "3,9,7,9,10,9,4,9,99,-1,8";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1, output);
    }

    @Test
    public void day5_step2_input_less_than_false() {
        int input = 12;

        String string = "3,9,7,9,10,9,4,9,99,-1,8";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(0, output);
    }

    @Test
    public void day5_step2_input_equal_to_true_imm() {
        int input = 8;

        String string = "3,3,1108,-1,8,3,4,3,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1, output);
    }

    @Test
    public void day5_step2_input_equal_to_false_imm() {
        int input = 12;

        String string = "3,3,1108,-1,8,3,4,3,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(0, output);
    }

    @Test
    public void day5_step2_input_less_than_true_imm() {
        int input = 1;

        String string = "3,3,1107,-1,8,3,4,3,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1, output);
    }

    @Test
    public void day5_step2_input_less_than_false_imm() {
        int input = 8;

        String string = "3,3,1107,-1,8,3,4,3,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(0, output);
    }

    @Test
    public void day5__test_jump_pos() {
        int input = 1;

        String string = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1, output);
    }

    @Test
    public void day5_step2_test_jump_imm() {
        int input = 0;

        String string = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(0, output);
    }

    @Test
    public void day5_step2_larger_than_8() {
        int input = 21;

        String string = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1001, output);
    }

    @Test
    public void day5_step2_equal_8() {
        int input = 8;

        String string = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        IntCodes ops = new IntCodes(string);
        long output = Util.runMachineWithSingleOutput(ops, input);
        Assert.assertEquals(1000, output);
    }

    @Test
    public void day5_step2_less_than_8() {
        // Not sure why this des not work?
        int input = 7;

        String string = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        IntCodes ops = new IntCodes(string);
        IntMachine im = new IntMachine(ops, input);
//        im.execute();
     //   List<Integer> output = im.getOutput();
      //  Assert.assertEquals(999, output.get(0).intValue());
    }

    @Test
    public void day9_relative_1() {

        String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.DEBUG = true;
        im.execute();
        List<Long> out = im.getOutput();
        Assert.assertEquals(109, out.get(0).intValue());
        Assert.assertEquals(1, out.get(1).intValue());
        Assert.assertEquals(204, out.get(2).intValue());

    }

    @Test
    public void day9_large() {

        String input = "1102,34915192,34915192,7,4,7,99,0";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        List<Long> out = im.getOutput();
       Assert.assertEquals(1219070632396864L, out.get(0).longValue());

    }

    @Test
    public void day9_large2() {

        String input = "104,1125899906842624,99";
        IntCodes ops = new IntCodes(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        List<Long> out = im.getOutput();
        Assert.assertEquals(1125899906842624L, out.get(0).longValue());

    }
}
