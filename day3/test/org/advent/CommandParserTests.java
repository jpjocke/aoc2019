package org.advent;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CommandParserTests {
    @Test
    public void List() {
        String list = "R75,D30,R83,U83";
        List<Command> commands = CommandParser.parseList(list);

        Assert.assertEquals(Command.Direction.RIGHT, commands.get(0).getDirection());
        Assert.assertEquals(75, commands.get(0).getLength());

        Assert.assertEquals(Command.Direction.DOWN, commands.get(1).getDirection());
        Assert.assertEquals(30, commands.get(1).getLength());

        Assert.assertEquals(Command.Direction.RIGHT, commands.get(2).getDirection());
        Assert.assertEquals(83, commands.get(2).getLength());

        Assert.assertEquals(Command.Direction.UP, commands.get(3).getDirection());
        Assert.assertEquals(83, commands.get(3).getLength());
    }

}
