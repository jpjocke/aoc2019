package org.advent;


public class TractorSpace {
    double a;
    double b;
    private int[][] board;

    public TractorSpace(int boardSize) {
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void setValueAt(int x, int y, int value) {
        board[y][x] = value;
    }

    public int countOnes() {
        int c = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                c += board[i][j];
            }
        }
        return c;
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j] == 1 ? "X" : ".");
            }
            System.out.println(sb.toString());
        }
    }

    public void findVectors() {
        double a1 = 0;
        double b1 = 0;
        int pointer = 0;
        while (a1 == 0 || b1 == 0) {
            if (board[board.length - 1][pointer] == 1 && a1 == 0) {
                a1 = pointer;
            }
            if (board[board.length - 1][pointer] == 0 && a1 != 0) {
                b1 = pointer - 1;
            }
            pointer++;
        }

        a = a1 / 50d;
        b = b1 / 50d;
    }

    public void printByVectors() {
        for (int y = 0; y < 100; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 100; x++) {
                sb.append(tractorByVector(y, x) == 1 ? 'X' :'.');
            }
            System.out.println(sb.toString());
        }
    }

    public int tractorByVector(int y, int x) {
        double x1 = a * y;
        double x2 = b * y;
        if(x >= x1 && x <= x2) {
            return 1;
        }
        return 0;
    }
}
