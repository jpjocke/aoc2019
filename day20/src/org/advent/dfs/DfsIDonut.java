package org.advent.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;

public interface DfsIDonut {
    /**
     * Finds the Node that has the position
     * @param position
     * @return
     */
    Optional<DfsNodeDonut> findByPosition(IntPoint searchedPos);

    /**
     * Chacks the dungeon and returns true if it is possible to go there.
     * @return
     */
    boolean isPositionViable(IntPoint p);


    Optional<IntPoint> findTeleport(IntPoint pos);

}
