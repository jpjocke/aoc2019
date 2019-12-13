package org.advent.util.arcade;

import org.advent.util.IntPoint;

public class DrawInstruction {
    IntPoint position;
    int instruction;

    public DrawInstruction(int x, int y, int instruction) {
        position = new IntPoint(x, y);
        this.instruction = instruction;
    }

    public boolean isBlock() {
        return instruction == 2;
    }

    public String getSymbol() {
        if (instruction == 0) {
            return " ";
        }
        if (instruction == 1) {
            return "X";
        }
        if (instruction == 2) {
            return "#";
        }

        if (instruction == 3) {
            return "-";
        }
        return "O";
    }

    @Override
    public String toString() {
        return "DrawInstruction{" +
                "position=" + position +
                ", instruction=" + instruction +
                '}';
    }
}
