package com.advent;

public class MapCalculator {

    public static int calculateTotalOrbits(SpaceObject root) {

        return countOrbitsRecursive(root, 0);
    }

    private static int countOrbitsRecursive(SpaceObject spaceObject,  int level) {
        int childCount = 0;
        for (int i = 0; i < spaceObject.getChildren().size(); i++) {
            SpaceObject child = spaceObject.getChildren().get(i);
            childCount += countOrbitsRecursive(child, level + 1);
        }
        return level + childCount;
    }
}
