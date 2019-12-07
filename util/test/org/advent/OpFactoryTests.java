package org.advent;

import org.advent.util.Util;
import org.advent.util.intmachine.IntCode;
import org.advent.util.intmachine.operations.Op;
import org.advent.util.intmachine.operations.OpAdd;
import org.advent.util.intmachine.operations.OpEquals;
import org.advent.util.intmachine.operations.OpFactory;
import org.advent.util.intmachine.operations.OpInput;
import org.advent.util.intmachine.operations.OpJumpIfFalse;
import org.advent.util.intmachine.operations.OpJumpIfTrue;
import org.advent.util.intmachine.operations.OpLessThan;
import org.advent.util.intmachine.operations.OpMultiply;
import org.advent.util.intmachine.operations.OpOutput;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OpFactoryTests {

    @Test
    public void toExit() {
        String input = "99";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op.isExit());
    }

    @Test
    public void toAdd() {
        String input = "1,1,1,1,1";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpAdd);
    }

    @Test
    public void toMultiply() {
        String input = "2,1,1,1,1";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpMultiply);
    }

    @Test
    public void toInput() {
        String input = "3,50";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpInput);
    }

    @Test
    public void toOutput() {
        String input = "4,50";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpOutput);
    }

    @Test
    public void toJumpIfTrue() {
        String input = "5,1,0";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpJumpIfTrue);
    }

    @Test
    public void toJumpIfFalse() {
        String input = "6,1,0";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpJumpIfFalse);
    }

    @Test
    public void toLessThan() {
        String input = "7,1,0,3";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpLessThan);
    }

    @Test
    public void toEquals() {
        String input = "8,1,0,3";
        List<IntCode> ops = Util.toOperations(input);
        OpFactory factory = new OpFactory(ops, new ArrayList<>(), 0);
        Op op = factory.buildOp(0);

        Assert.assertTrue(op instanceof OpEquals);
    }
}
