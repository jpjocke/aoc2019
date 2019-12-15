package org.advent;

import org.advent.util.IntPoint;

import java.util.Arrays;

public class DroidExploreMode implements DroidMode {
    Labyrinth labyrinth;
    Droid d;

    public DroidExploreMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
    }

    public int getNextDir() {
        int[] around = buildAround();
        System.out.println("Around: " + Arrays.toString(around));

        if (around[0] == -1) {
            return 1;
        }
        if (around[1] == -1) {
            return 2;
        }

        if (around[2] == -1) {
            return 3;
        }

        if (around[3] == -1) {
            return 4;
        }

        return getRecommendedDir();
    }

    private int[] buildAround() {
        IntPoint fakePos = d.getPosition().copy();
        int[] around = new int[4];
        // up
        fakePos.y--;
        around[0] = labyrinth.getValueAt(fakePos);
        // down
        fakePos.y++;
        fakePos.y++;
        around[1] = labyrinth.getValueAt(fakePos);
        // left
        fakePos.y--;
        fakePos.x--;
        around[2] = labyrinth.getValueAt(fakePos);
        // right
        fakePos.x++;
        fakePos.x++;
        around[3] = labyrinth.getValueAt(fakePos);
        return around;
    }

    private int getRecommendedDir() {
        int[] rec = new int[4];
        rec[0] = recommendedScoreVertical(-1);
        rec[1] = recommendedScoreVertical(1);
        rec[2] = recommendedScoreHorizontal(-1);
        rec[3] = recommendedScoreHorizontal(1);

        int maxIndex = 0;
        for (int i = 1; i < rec.length; i++) {
            if (rec[i] > rec[maxIndex]) {
                maxIndex = i;
            }
        }

        System.out.println("Recommeded score: " + Arrays.toString(rec));
        // diection is +1
        return maxIndex + 1;
    }

    private int recommendedScoreVertical(int dir) {
        int value = 0;
        IntPoint fakePos = d.getPosition().copy();
        fakePos.y += dir;
        int v = labyrinth.getValueAt(fakePos);

        while (true) {
            if (v == 0 || v == -1) {
                break;
            }
            value += getValueScore(v);

            v = labyrinth.getValueAt(fakePos.x - 1, fakePos.y);
            value += getValueScore(v);

            v = labyrinth.getValueAt(fakePos.x + 1, fakePos.y);
            value += getValueScore(v);

            fakePos.y += dir;
            v = labyrinth.getValueAt(fakePos);
        }

        return value;
    }

    private int recommendedScoreHorizontal(int dir) {
        int value = 0;
        IntPoint fakePos = d.getPosition().copy();
        fakePos.x += dir;
        int v = labyrinth.getValueAt(fakePos);

        while (true) {
            value += getValueScore(v);
            if (v == 0 || v == -1) {
                break;
            }

            v = labyrinth.getValueAt(fakePos.x, fakePos.y - 1);
            value += getValueScore(v);

            v = labyrinth.getValueAt(fakePos.x, fakePos.y + 1);
            value += getValueScore(v);

            fakePos.x += dir;
            v = labyrinth.getValueAt(fakePos);
        }

        return value;
    }

    private int getValueScore(int value) {
        if (value == -1) {
            return 100;
        }
        if (value == 0) {
            return 0;
        }
        return 1;
    }
}
