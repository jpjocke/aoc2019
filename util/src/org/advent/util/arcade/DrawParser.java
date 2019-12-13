package org.advent.util.arcade;

import java.util.ArrayList;
import java.util.List;

public class DrawParser {

    public static List<DrawInstruction> parse(List<Long> instructions) {
        List<DrawInstruction> draws = new ArrayList<>();

        int index = 0;
        while (index < instructions.size()) {
            int x = instructions.get(index).intValue();
            index++;
            int y = instructions.get(index).intValue();
            index++;
            int instruction = instructions.get(index).intValue();
            index++;
            draws.add(new DrawInstruction(x, y, instruction));
        }
        return draws;
    }
}
