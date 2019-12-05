package og.advent;

import org.advent.IntMachine;
import org.advent.IntCode;
import org.advent.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class VerifyTests {

    @Test
    public void oldTest1() {
        System.out.println("1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2)");

        String input = "1,0,0,0,99";
        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
    }

    @Test
    public void oldTest2() {
        System.out.println("2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6)");

        String input = "2,3,0,3,99";
        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
        Assert.assertEquals(6, im.getCodes().get(3).getValue());
    }

    @Test
    public void oldTest3() {
        System.out.println("2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801)");

        String input = "2,4,4,5,99,0";
        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(2, im.getResult());
        Assert.assertEquals(9801, im.getCodes().get(5).getValue());
    }

    @Test
    public void oldTest4() {
        System.out.println("1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99");

        String input = "1,1,1,4,99,5,6,0,99";
        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(30, im.getResult());
    }

    @Test
    public void oldTestReal() {
        System.out.println("REAL");
        String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,5,19,23,2,10,23,27,1,27,5,31,2,9,31,35,1,35,5,39,2,6,39,43,1,43,5,47,2,47,10,51,2,51,6,55,1,5,55,59,2,10,59,63,1,63,6,67,2,67,6,71,1,71,5,75,1,13,75,79,1,6,79,83,2,83,13,87,1,87,6,91,1,10,91,95,1,95,9,99,2,99,13,103,1,103,6,107,2,107,6,111,1,111,2,115,1,115,13,0,99,2,0,14,0";

        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(4714701, im.getResult());
    }


    @Test
    public void multiplication() {
        System.out.println("1002,4,3,4,33 becomes 1002,4,3,4,99");

        String input = "1002,4,3,4,33";
        List<IntCode> ops = Util.toOperations(input);
        IntMachine im = new IntMachine(ops);
        im.execute();
        Assert.assertEquals(1002, im.getResult());
    }
}
