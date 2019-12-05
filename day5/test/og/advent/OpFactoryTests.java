package og.advent;

import org.advent.IntCode;
import org.advent.Util;
import org.advent.operations.Op;
import org.advent.operations.OpAdd;
import org.advent.operations.OpFactory;
import org.advent.operations.OpInput;
import org.advent.operations.OpMultiply;
import org.advent.operations.OpOutput;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OpFactoryTests {

    @Test
    public void toExit() {
        String input = "99";
        List<IntCode> ops = Util.toOperations(input);
        Op op = OpFactory.buildOp(0, ops, new ArrayList<>());

        Assert.assertTrue(op.isExit());
    }

    @Test
    public void toAdd() {
        String input = "1,1,1,1,1";
        List<IntCode> ops = Util.toOperations(input);
        Op op = OpFactory.buildOp(0, ops, new ArrayList<>());

        Assert.assertTrue(op instanceof OpAdd);
    }

    @Test
    public void toMultiply() {
        String input = "2,1,1,1,1";
        List<IntCode> ops = Util.toOperations(input);
        Op op = OpFactory.buildOp(0, ops, new ArrayList<>());

        Assert.assertTrue(op instanceof OpMultiply);
    }

    @Test
    public void toInput() {
        String input = "3,50";
        List<IntCode> ops = Util.toOperations(input);
        Op op = OpFactory.buildOp(0, ops, new ArrayList<>());

        Assert.assertTrue(op instanceof OpInput);
    }

    @Test
    public void toOutput() {
        String input = "4,50";
        List<IntCode> ops = Util.toOperations(input);
        Op op = OpFactory.buildOp(0, ops, new ArrayList<>());

        Assert.assertTrue(op instanceof OpOutput);
    }
}
