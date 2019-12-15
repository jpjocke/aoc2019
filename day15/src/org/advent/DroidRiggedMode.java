package org.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DroidRiggedMode {
    Labyrinth labyrinth;
    Droid d;
    List<Integer> directions;

    public DroidRiggedMode(Labyrinth labyrinth, Droid d) {
        this.labyrinth = labyrinth;
        this.d = d;
        directions = new ArrayList<>();
        buildDirections();
    }

    public void goRigged(){
        for(int i = 0; i < directions.size(); i++) {
            d.runAndReport(directions.get(i));
        }
        System.out.println("steps: " + (directions.size() - 1));
    }

    private void buildDirections() {
        addDirection(2, 4);
        addDirection(4, 2);
        addDirection(12, 3);
        addDirection(4, 1);
        addDirection(2, 3);
        addDirection(2, 2);
        addDirection(2, 3);
        addDirection(2, 2);
        addDirection(2, 3);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(2, 2);
        addDirection(2, 3);
        addDirection(4, 2);
        addDirection(4, 4);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(4, 1);
        addDirection(2, 3);
        addDirection(4, 1);
        addDirection(2, 4);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(2, 1);
        addDirection(6, 4);
        addDirection(4, 2);
        addDirection(2, 3);
        addDirection(4, 2);
        addDirection(2, 3);
        addDirection(4, 1);
        addDirection(2, 3);
        addDirection(6, 2);
        addDirection(2, 3);
        addDirection(2, 2);
        addDirection(4, 4);
        addDirection(2, 1);
        addDirection(2, 4);
        addDirection(2, 2);
        addDirection(6, 4);
        addDirection(2, 1);
        addDirection(4, 3);
        addDirection(2, 1);
        addDirection(4, 4);
        addDirection(2, 1);
        addDirection(6, 4);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(2, 1);
        addDirection(2, 4);
        addDirection(4, 2);
        addDirection(4, 4);
        addDirection(2, 1);
        addDirection(2, 3);
        addDirection(2, 1);
        addDirection(2, 4);
        addDirection(14, 1);
        addDirection(2, 3);
        addDirection(8, 2);
        addDirection(2, 3);
        addDirection(2, 1);
        addDirection(4, 3);
        addDirection(2, 2);
        addDirection(2, 4);
        addDirection(2, 2);
        addDirection(2, 3);
        addDirection(2, 2);
        addDirection(4, 4);
        addDirection(2, 1);
        addDirection(2, 4);
        addDirection(2, 2);
        // reveal it
        addDirection(1, 1);
    }

    private void addDirection(int amount, int dir) {
        for(int i = 0; i < amount; i++) {
            directions.add(dir);
        }
    }
}
