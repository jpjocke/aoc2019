package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedDistanceList {
    private Map<IntPoint, CachedDistance> distances;

    public CachedDistanceList() {
        this.distances = new HashMap<>();
    }

    public void add(CachedDistance cachedDistance) {
        distances.put(cachedDistance.to, cachedDistance);
    }

    public boolean isPositionReachable(IntPoint pos, List<Character> doors) {
        CachedDistance d = distances.get(pos);
        return d.isDoorsInWay(doors);
    }

    public CachedDistance get(Object key) {
        return distances.get(key);
    }
}
