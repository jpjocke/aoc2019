package org.advent.util.arcade;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    List<DrawInstruction> draws;

    public Printer() {
        this.draws = new ArrayList<>();
    }

    public List<DrawInstruction> getInstructions() {
        return draws;
    }

    public void update(List<DrawInstruction> updates) {
        for (int i = draws.size() - 1; i >= 0; i--) {
            for (int j = 0; j < updates.size(); j++) {
                if (draws.get(i).position.equals(updates.get(j).position)) {
                    draws.remove(i);
                    break;
                }
            }
        }
        draws.addAll(updates);
    }

    public void print() {
        int score = 0;

        int width = 0;
        int height = 0;

        for (int i = 0; i < draws.size(); i++) {
            DrawInstruction di = draws.get(i);
            if (di.position.x > width) {
                width = di.position.x;
            }
            if (di.position.y > height) {
                height = di.position.y;
            }
        }

        for (int i = 0; i < draws.size(); i++) {
            DrawInstruction di = draws.get(i);
            if (di.isScore()) {
                score = di.instruction;
            }
        }

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                for (int i = 0; i < draws.size(); i++) {
                    DrawInstruction di = draws.get(i);
                    if (x == di.position.x && y == di.position.y) {
                        sb.append(di.getSymbol());
                        break;
                    }
                }
            }
            System.out.println(sb);
        }
        System.out.println("Score: " + score);

    }
}
