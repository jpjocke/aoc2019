package org.advent;

import java.util.List;

public class ManhattanComparer {
    public static int getSmallest(List<IntPoint> intersections, IntPoint comparer) {
        int smallest = Integer.MAX_VALUE;

        for(int i = 0; i < intersections.size(); i++) {
            int dist = intersections.get(i).manhattanDistance(comparer);
            if(dist < smallest) {
                smallest = dist;
            }
        }

        return smallest;
    }
}
