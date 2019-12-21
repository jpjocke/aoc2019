package org.advent.dfsstep2.dfs;

import org.advent.util.IntPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DfsCalcDonut2 implements DfsIDonut2 {

    public static boolean DEBUG = false;
    private Map<IntPoint, String> teleports;
    private DonutMazeStep2 map;
    private IntPoint start;
    private DfsNodeDonut2 top;
    private int levels;
    private List<HashMap<IntPoint, DfsNodeDonut2>> searchMap;

    public DfsCalcDonut2(Map<IntPoint, String> teleports, DonutMazeStep2 map, int levels) {
        this.teleports = teleports;
        this.map = map;
        for (IntPoint p : teleports.keySet()) {
            if (teleports.get(p).equals("AA")) {
                start = p;
                break;
            }
        }
        searchMap = new ArrayList<>();
        for (int i = 0; i <= levels; i++) {
            searchMap.add(new HashMap<>());
        }

        top = new DfsNodeDonut2(null, start, 0, 0);
        addNode(top);
        this.levels = levels;
        top.DEBUG = true;
    }

    public int exploreFromStart() {
        top.explore(this, map);
        map.printFull(this, start, levels);

        Optional<DfsNodeDonut2> exit = findByPosition(findExitPos(), 0);
        if (exit.isPresent()) {
            return exit.get().getSteps();
        }

        return 0;
    }


    @Override
    public Optional<DfsNodeDonut2> findByPosition(IntPoint searchedPos, int level) {
        DfsNodeDonut2 node = searchMap.get(level).get(searchedPos);
        if (node == null) {
            return Optional.empty();
        }
        return Optional.of(node);
        //  return top.findByPosition(searchedPos, level);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
        char c = map.getCharAt(p);
        // open space
        return c == '.';
        // wall
    }

    @Override
    public Optional<PortalResult> findTeleport(IntPoint pos, int level) {
        String key = teleports.get(pos);
        if (key != null) {
            if (key.equals("AA") || key.equals("ZZ")) {
                return Optional.empty();

            }
            for (IntPoint exit : teleports.keySet()) {
                if (exit.equals(pos)) {
                    continue;
                }
                if (teleports.get(exit).equals(key)) {
                    // kolla position och om den går ner / upp
                    int nextLevel = level;
                    if (pos.x == 0) {
                        nextLevel--;
                    } else if (pos.x == map.getWidth() - 1) {
                        nextLevel--;
                    } else if (pos.y == 0) {
                        nextLevel--;
                    } else if (pos.y == map.getHeight() - 1) {
                        nextLevel--;
                    } else {
                        nextLevel++;
                    }
                    if (nextLevel < 0 || nextLevel >= levels) {
                        return Optional.empty();
                    }
                    PortalResult r = new PortalResult(exit, nextLevel);
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void addNode(DfsNodeDonut2 node) {
        HashMap<IntPoint, DfsNodeDonut2> map = searchMap.get(node.level);
        map.put(node.pos, node);
    }


    private IntPoint findExitPos() {
        for (IntPoint exit : teleports.keySet()) {
            if (teleports.get(exit).equals("ZZ")) {
                return exit;
            }
        }
        return null;
    }


}
