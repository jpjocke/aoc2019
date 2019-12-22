package org.advent;

import java.util.ArrayList;
import java.util.List;

public class ActionParser {

    public static List<Action> parse(String input) {
        String[] lines = input.split("\n");

        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (line.equals("deal into new stack")) {
                actions.add(new Action(Action.Type.NEW_STACK, 0));

            } else {
                String[] split = line.split(" ");
                int amount = Integer.parseInt(split[split.length - 1]);

                if (line.startsWith("deal with increment")) {
                    actions.add(new Action(Action.Type.DEAL_INCREMENT, amount));

                } else if (line.startsWith("cut")) {
                    actions.add(new Action(Action.Type.CUT, amount));
                } else {
                    throw new RuntimeException("NO ACTION");
                }
            }
        }

        return actions;
    }
}
