package org.advent.dfs;

import org.advent.DonutMaze;
import org.advent.util.IntPoint;

import java.util.Map;
import java.util.Optional;

public class DfsCalcDonut implements DfsIDonut {

    public static boolean DEBUG = false;
    private Map<IntPoint, String> teleports;
    private DonutMaze map;
    private IntPoint start;
    private DfsNodeDonut top;

    public DfsCalcDonut(Map<IntPoint, String> teleports, DonutMaze map) {
        this.teleports = teleports;
        this.map = map;
        for (IntPoint p : teleports.keySet()) {
            if (teleports.get(p).equals("AA")) {
                start = p;
                break;
            }
        }
        top = new DfsNodeDonut(null, start, 0);
     //   top.DEBUG = true;
    }

    public int exploreFromStart() {
        top.explore(this, map);
        map.print(top, start);

        Optional<DfsNodeDonut> exit = findByPosition(findExitPos());
        if (exit.isPresent()) {
            return exit.get().getSteps();
        }

        return 0;
    }


    @Override
    public Optional<DfsNodeDonut> findByPosition(IntPoint searchedPos) {
        return top.findByPosition(searchedPos);
    }

    @Override
    public boolean isPositionViable(IntPoint p) {
        char c = map.getCharAt(p);
        // open space
        return c == '.';
        // wall
    }

    @Override
    public Optional<IntPoint> findTeleport(IntPoint pos) {
        String key = teleports.get(pos);
        if (key != null) {
            if (key.equals("AA") || key.equals("ZZ")) {
                return Optional.empty();

            }
            for (IntPoint exit : teleports.keySet()) {
                if(exit.equals(pos)) {
                    continue;
                }
                if (teleports.get(exit).equals(key)) {
                    return Optional.of(exit);
                }
            }
        }
        return Optional.empty();
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
