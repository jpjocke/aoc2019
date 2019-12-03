package org.advent;

import java.util.ArrayList;
import java.util.List;

public class StepFinder {

    public static int getShortestSteps(Board board, List<Command> wire1, List<Command> wire2) {
        List<IntPoint> intersections = board.getIntersections();
        List<IntPoint> counts = new ArrayList<>();

        for (int i = 0; i < intersections.size(); i++) {
            // seems to be off by 1
            int one = board.getStepsToIntersectionForCommands(wire1, intersections.get(i)) + 1;
            int two = board.getStepsToIntersectionForCommands(wire2, intersections.get(i)) + 1;
            counts.add(new IntPoint(one, two));
        }

        int smallest = Integer.MAX_VALUE;
        counts.stream().forEach(c -> System.out.println(c + ": " + c.x + c.y + " steps"));
        for(int i = 0; i < counts.size(); i++) {
            int dist = counts.get(i).x + counts.get(i).y;
            if(dist < smallest) {
                smallest = dist;
            }
        }

        return smallest;
    }
}
