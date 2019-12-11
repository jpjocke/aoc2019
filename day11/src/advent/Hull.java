package advent;


import org.advent.util.IntPoint;

public class Hull {
    private int[][] board;
    private boolean[][] boardCount;

    public Hull(int width, int height, IntPoint start) {
        board = new int[height][width];
        boardCount = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = 0;
                if(start.x == x && start.y == y) {
                    board[y][x] = 1;
                }
                boardCount[y][x] = false;
            }
        }
    }

    public int getValueAt(IntPoint position) {
        return board[position.y][position.x];
    }

    public boolean setValueAt(IntPoint position, int value) {
        board[position.y][position.x] = value;
        if (!boardCount[position.y][position.x]) {
            boardCount[position.y][position.x] = true;
            return true;
        }
        return false;
    }


    public void print(Painter p) {
        for (int y = 0; y < board.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < board[y].length; x++) {
                if (p.position.y == y && p.position.x == x) {
                    switch (p.direction) {
                        case UP:
                            sb.append("^");
                            break;
                        case DOWN:
                            sb.append("v");
                            break;
                        case LEFT:
                            sb.append("<");
                            break;
                        case RIGHT:
                        default:
                            sb.append(">");
                            break;
                    }
                } else {
                    sb.append(board[y][x] == 0 ? "." : "#");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
