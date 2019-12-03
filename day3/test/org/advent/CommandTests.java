package org.advent;

import org.junit.Assert;
import org.junit.Test;

public class CommandTests {
    @Test
    public void Up() {
        String up = "U71";
        Command command = new Command(up);
        Assert.assertEquals(Command.Direction.UP, command.getDirection());
    }

    @Test
    public void Right() {
        String right = "R71";
        Command command = new Command(right);
        Assert.assertEquals(Command.Direction.RIGHT, command.getDirection());
    }

    @Test
    public void Down() {
        String down = "D71";
        Command command = new Command(down);
        Assert.assertEquals(Command.Direction.DOWN, command.getDirection());
    }

    @Test
    public void Left() {
        String left = "L71";
        Command command = new Command(left);
        Assert.assertEquals(Command.Direction.LEFT, command.getDirection());
    }

    @Test
    public void Length2() {
        String left = "L2";
        Command command = new Command(left);
        Assert.assertEquals(2, command.getLength());
    }

    @Test
    public void Length21() {
        String left = "L21";
        Command command = new Command(left);
        Assert.assertEquals(21, command.getLength());
    }

    @Test
    public void Length234() {
        String left = "L234";
        Command command = new Command(left);
        Assert.assertEquals(234, command.getLength());
    }
}
