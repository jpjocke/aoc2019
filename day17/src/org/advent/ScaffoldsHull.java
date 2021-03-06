package org.advent;


import org.advent.util.IntPoint;

import java.util.List;

public class ScaffoldsHull {
    private int[][] board;

    public ScaffoldsHull(List<Long> output) {
        int width = 0;
        int height;
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i) == 10) {
                break;
            }
            width++;
        }

        // height = output.size() / width;

        board = new int[35][59];

        int y = 0;
        int x = 0;
        for (int i = 0; i < output.size(); i++) {
            long val = output.get(i);
            if (val == 10) {
                y++;
                x = 0;
                continue;
            }
            if (y > board.length) {
                break;
            }
            board[y][x] = (int) val;
            x++;
        }
    }

    public int getValueAt(IntPoint position) {
        return board[position.y][position.x];
    }

    public int countIntersections() {
        int val = 0;
        for (int y = 0; y < board.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < board[y].length; x++) {
                char c = (char)board[y][x];
                sb.append(c);
                if (x == 0 || x == board[y].length - 1) {
                    continue;
                }
                if (y == 0 || y == board.length - 1) {
                    continue;
                }
                // this
                if (board[y][x] != 35) {
                    continue;
                }
                // up
                if (board[y - 1][x] != 35) {
                    continue;
                }
                // down
                if (board[y + 1][x] != 35) {
                    continue;
                }
                // left
                if (board[y][x - 1] != 35) {
                    continue;
                }
                // right
                if (board[y][x + 1] != 35) {
                    continue;
                }

                // intersection
                val += y * x;
                sb.replace(sb.length() - 1, sb.length() - 1, "O");
            }
            System.out.println(sb);
        }
        return val;
    }

    public void print() {
        for (int y = 0; y < board.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < board[y].length; x++) {
                char c = (char)board[y][x];
                sb.append(c);
            }
            System.out.println(sb.toString());
        }


    }
}
