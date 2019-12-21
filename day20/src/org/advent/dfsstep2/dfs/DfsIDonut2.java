package org.advent.dfsstep2.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;

public interface DfsIDonut2 {
    /**
     * Finds the Node that has the position
     * @return
     */
    Optional<DfsNodeDonut2> findByPosition(IntPoint searchedPos, int level);

    /**
     * Chacks the dungeon and returns true if it is possible to go there.
     * @return
     */
    boolean isPositionViable(IntPoint p);


    Optional<PortalResult> findTeleport(IntPoint pos, int level);

}
