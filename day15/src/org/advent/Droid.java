package org.advent;

import org.advent.util.IntPoint;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

public class Droid {
    IntPoint position;
    Labyrinth labyritnh;
    IntMachine machine;

    public Droid(IntPoint position, Labyrinth labyrinth, IntCodes instructions) {
        this.position = position;
        this.labyritnh = labyrinth;
        machine = new IntMachine(instructions);
    }

    public int runAndReport(int direction) {
        System.out.println("---------------------------------");
        machine.addInput(direction);
        machine.execute();

        int report = (int) machine.getLastOutput();

        reportAndMove(direction, report);
        labyritnh.print(this);
        return report;
    }

    private void reportAndMove(int direction, int report) {
        System.out.println("Moved: " + direction + " found: " + report);
        IntPoint fakePos = position.copy();
        if (direction == 1) {
            if (report == 0) {
                // wall
                fakePos.y--;
                labyritnh.setValueAt(fakePos, report);
            } else {
                position.y--;
                labyritnh.setValueAt(position, report);
            }
        }

        if (direction == 2) {
            if (report == 0) {
                // wall
                fakePos.y++;
                labyritnh.setValueAt(fakePos, report);
            } else {
                position.y++;
                labyritnh.setValueAt(position, report);
            }
        }

        if (direction == 3) {
            if (report == 0) {
                // wall
                fakePos.x--;
                labyritnh.setValueAt(fakePos, report);
            } else {
                position.x--;
                labyritnh.setValueAt(position, report);
            }
        }

        if (direction == 4) {
            if (report == 0) {
                // wall
                fakePos.x++;
                labyritnh.setValueAt(fakePos, report);
            } else {
                position.x++;
                labyritnh.setValueAt(position, report);
            }
        }
    }

    public IntPoint getPosition() {
        return position;
    }
}
