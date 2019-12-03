package org.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
    public static List<Command> parseList(String list) {
        List<Command> commands = new ArrayList<>();
        String[] split = list.split(",");
        Arrays.stream(split).forEach(c -> commands.add(new Command(c)));
        return commands;
    }
}
