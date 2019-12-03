package org.advent;

public class Command {
    public enum Direction {UP, RIGHT, DOWN, LEFT}
    private final Direction direction;
    private final int length;

    public Command(String toParse) {
        char dir = toParse.charAt(0);
        length = Integer.parseInt(toParse.substring(1));
        if (dir == 'U') {
            direction = Direction.UP;
        } else if (dir == 'R') {
            direction = Direction.RIGHT;
        } else if (dir == 'D') {
            direction = Direction.DOWN;
        } else {
            direction = Direction.LEFT;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }
}
