package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.List;

public class CachedDistance {
    int steps;
    IntPoint from;
    IntPoint to;
    char toKey;
    String blockingDoors;


    public CachedDistance(IntPoint from, IntPoint to, char toKey) {
        this.from = from;
        this.to = to;
        this.toKey = toKey;
        blockingDoors = "";
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public IntPoint getTo() {
        return to;
    }

    public IntPoint getFrom() {
        return from;
    }

    public boolean isDoorsInWay(List<Character> doors) {
        for (int i = 0; i < doors.size(); i++) {
            if (blockingDoors.contains(String.valueOf(doors.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public void addBlockingDoor(char door) {
        blockingDoors = blockingDoors + door;
    }
}
