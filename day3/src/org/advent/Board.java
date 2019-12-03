package org.advent;


import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] board;
    private IntPoint start;
    private IntPoint[] wires;

    public Board(int boardSize, int wireSize) {
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }

        start = new IntPoint(boardSize / 2, boardSize / 2);
        wires = new IntPoint[wireSize];
        for (int i = 0; i < wireSize; i++) {
            wires[i] = new IntPoint(start.x, start.y);
        }
    }

    public void runLength(Command command, int wireIndex) {
        int length = command.getLength();
        IntPoint wire = wires[wireIndex];
        // 0 = 1, 1 = 2, using or makes the sum 3
        int points = wireIndex + 1;

        while (length > 0) {
            wire.runCommand(command);

            board[wire.x][wire.y] = board[wire.x][wire.y] | points;
            length--;
        }
    }

    public IntPoint getStart() {
        return start;
    }

    public List<IntPoint> getIntersections() {
        List<IntPoint> intersections = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 3) {
                    intersections.add(new IntPoint(i, j));
                }
            }
        }
        return intersections;
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            System.out.println(sb.toString());
        }
    }
}
