package org.advent;


import org.advent.util.IntPoint;

public class Labyrinth {
    private int[][] board;

    public Labyrinth(int width, int height, IntPoint start) {
        board = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = -1;
                if (start.x == x && start.y == y) {
                    board[y][x] = 10;
                }
            }
        }
    }

    public int getValueAt(IntPoint position) {
        return getValueAt(position.x, position.y);
    }

    public int getValueAt(int x, int y) {
        return board[y][x];
    }

    public void setValueAt(IntPoint position, int value) {
        board[position.y][position.x] = value;
    }


    public void print(Droid p) {
        for (int y = 0; y < board.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < board[y].length; x++) {
                if (p.position.y == y && p.position.x == x) {
                    sb.append("D");
                } else {
                    if (board[y][x] == -1) {
                        sb.append("?");
                    } else if (board[y][x] == 0) {
                        sb.append("#");
                    } else if (board[y][x] == 1) {
                        sb.append(" ");
                    } else if (board[y][x] == 2) {
                        sb.append("O");
                    } else if (board[y][x] == 10) {
                        sb.append("S");
                    }
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void solidify() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == -1) {
                    board[y][x] = 0;
                }
            }
        }
    }

    public boolean spreadOxygen() {
        boolean allOxygen = true;
        int[][] copy = copy();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 2) {
                    // spread...
                    if (y > 0 && board[y - 1][x] != 0 && board[y - 1][x] != 2) {
                        copy[y - 1][x] = 2;
                        allOxygen = false;
                    }
                    if (y < board.length && board[y + 1][x] != 0 && board[y + 1][x] != 2) {
                        copy[y + 1][x] = 2;
                        allOxygen = false;
                    }

                    if (x > 0 && board[y][x - 1] != 0 && board[y][x - 1] != 2) {
                        copy[y][x - 1] = 2;
                        allOxygen = false;
                    }
                    if (x < board[y].length && board[y][x + 1] != 0 && board[y][x + 1] != 2) {
                        copy[y][x + 1] = 2;
                        allOxygen = false;
                    }
                }
            }
        }
        board = copy;
        return allOxygen;
    }

    private int[][] copy() {
        int[][] copy = new int[board.length][board[0].length];

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                copy[y][x] = board[y][x];

            }
        }
        return copy;
    }
}
