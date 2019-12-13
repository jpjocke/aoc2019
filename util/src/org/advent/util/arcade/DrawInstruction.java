package org.advent.util.arcade;

import org.advent.util.IntPoint;

public class DrawInstruction {
    public IntPoint position;
    int instruction;

    public DrawInstruction(int x, int y, int instruction) {
        position = new IntPoint(x, y);
        this.instruction = instruction;
    }

    public boolean isBlock() {
        return instruction == 2;
    }

    public boolean isBall() {
        return instruction == 4;
    }


    public boolean isPaddle() {
        return instruction == 3;
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

    public boolean isScore() {
        return  position.x == -1 && position.y == 0;
    }

    @Override
    public String toString() {
        return "DrawInstruction{" +
                "position=" + position +
                ", instruction=" + instruction +
                '}';
    }
}
