package org.advent.dfsstep2.dfs;

import org.advent.util.IntPoint;

import java.util.Optional;

// inner portals go down one level / outer goes up one level
public class DfsNodeDonut2 {
    public static boolean DEBUG = false;
    protected IntPoint pos;
    protected int steps;
    protected int level;
    protected DfsNodeDonut2 parent;
    protected DfsNodeDonut2 teleport;
    private DfsNodeDonut2[] around;
    private boolean deadEnd;

    public DfsNodeDonut2(DfsNodeDonut2 parent, IntPoint pos, int steps, int level) {
        this.pos = pos;
        this.steps = steps;
        this.parent = parent;
        this.level = level;
        around = new DfsNodeDonut2[4];
        deadEnd = false;
        if (DEBUG) {
            System.out.println("Created: " + this);
        }
    }

    public IntPoint getPos() {
        return pos;
    }

    public void explore(DfsIDonut2 dfsi, DonutMazeStep2 map) {
        if (DEBUG) {
            System.out.println("Exploring: " + this);
        }
        if (around[0] == null) {
            IntPoint upP = pos.copy();
            upP.y--;
            around[0] = exploreForPos(dfsi, upP);
        }

        if (around[1] == null) {
            IntPoint upP = pos.copy();
            upP.y++;
            around[1] = exploreForPos(dfsi, upP);
        }

        if (around[2] == null) {
            IntPoint upP = pos.copy();
            upP.x--;
            around[2] = exploreForPos(dfsi, upP);
        }

        if (around[3] == null) {
            IntPoint upP = pos.copy();
            upP.x++;
            around[3] = exploreForPos(dfsi, upP);
        }
        if (DEBUG) {
            //       map.print(getTop(), pos, level);
        }

        if (around[0] != null && !around[0].deadEnd) {
            around[0].explore(dfsi, map);
        }
        if (around[1] != null && !around[1].deadEnd) {
            around[1].explore(dfsi, map);
        }
        if (around[2] != null && !around[2].deadEnd) {
            around[2].explore(dfsi, map);
        }
        if (around[3] != null && !around[3].deadEnd) {
            around[3].explore(dfsi, map);
        }
        if (teleport != null) {
            teleport.explore(dfsi, map);
        }
        if (around[0] == null && around[1] == null && around[2] == null && around[3] == null && teleport == null) {
            deadEnd = true;
            parent.childIsDeadEnd();
        }
    }

    protected void childIsDeadEnd() {
        int paths = 0;
        if (around[0] != null) {
            paths++;
        }
        if (around[1] != null) {
            paths++;
        }
        if (around[2] != null) {
            paths++;
        }
        if (around[3] != null) {
            paths++;
        }
        if (teleport != null) {
            paths++;
        }
        if (paths <= 1) {
            deadEnd = true;
            parent.childIsDeadEnd();
        }
    }

    private DfsNodeDonut2 getTop() {
        if (steps == 0) {
            return this;
        }
        return parent.getTop();
    }

    public void removeFromParent(DfsNodeDonut2 newParent) {
        if (parent.around[0] != null && parent.around[0].getPos().equals(this.pos)) {
            parent.around[0] = null;
        }
        if (parent.around[1] != null && parent.around[1].getPos().equals(this.pos)) {
            parent.around[1] = null;
        }
        if (parent.around[2] != null && parent.around[2].getPos().equals(this.pos)) {
            parent.around[2] = null;
        }
        if (parent.around[3] != null && parent.around[3].getPos().equals(this.pos)) {
            parent.around[3] = null;
        }
        if (parent.teleport != null && parent.teleport.getPos().equals(this.pos)) {
            DfsNodeDonut2 tmp = parent;
            DfsNodeDonut2 t = parent.teleport;
            parent.teleport = null;
            parent.removeFromParent(t);
            t.teleport = tmp;
        }

        parent = newParent;
    }

    protected IntPoint getStartPoint() {
        if (steps == 0) {
            return pos;
        }
        return parent.getStartPoint();
    }

    public DfsNodeDonut2 exploreForPos(DfsIDonut2 dfsi, IntPoint p) {
        // check map if we can go here
        if (dfsi.isPositionViable(p)) {

            // check if we already have been here
            Optional<DfsNodeDonut2> optionalDfsNode = dfsi.findByPosition(p, level);

            if (optionalDfsNode.isPresent()) {
                return takeNodeIfCloser(p, optionalDfsNode.get());
            } else {

                Optional<PortalResult> oPortalResult = dfsi.findTeleport(p, level);
                if (oPortalResult.isPresent()) {
                    PortalResult pr = oPortalResult.get();
                    DfsNodeDonut2 d = new DfsNodeDonut2(this, p, steps + 1, level);

                    if (DEBUG) {
                        System.out.println("teleport to: " + pr.pos + " level: " + pr.level);
                    }
                    // have we been at the teleport?
                    Optional<DfsNodeDonut2> optionalExit = dfsi.findByPosition(pr.pos, pr.level);
                    if (optionalExit.isPresent()) {
                        DfsNodeDonut2 node = takeNodeIfCloser(p, optionalExit.get());
                        if (node != null) {
                            d.teleport = node;
                        }
                    } else {
                        if (DEBUG) {
                            System.out.println("New teleport level: " + pr.level);
                        }
                        DfsNodeDonut2 t = new DfsNodeDonut2(d, pr.pos, steps + 2, pr.level);
                        dfsi.addNode(t);
                        d.teleport = t;
                    }

                    dfsi.addNode(d);
                    return d;
                } else {
                    // we have not been here
                    DfsNodeDonut2 node = new DfsNodeDonut2(this, p, steps + 1, level);
                    dfsi.addNode(node);
                    return node;

                }
            }
        }
        return null;
    }

    private DfsNodeDonut2 takeNodeIfCloser(IntPoint p, DfsNodeDonut2 node) {
        if (DEBUG) {
            System.out.println("Already explored: " + p + " had steps: " + node.getSteps());
        }
        // check if it is closer this way
        if (node.getSteps() > steps + 1) {
            if (DEBUG) {
                System.out.println("Stealing dfs at: " + p);
            }
            node.removeFromParent(this);
            node.setSteps(steps + 1);
            return node;
        } else {
            // old way was closer, skip this
            return null;
        }
    }

    public Optional<DfsNodeDonut2> findByPosition(IntPoint p, int level) {
        if (pos.equals(p) && this.level == level) {
            return Optional.of(this);
        }
        if (deadEnd) {
            return Optional.empty();
        }
        Optional<DfsNodeDonut2> child = Optional.empty();
        if (around[0] != null) {
            child = around[0].findByPosition(p, level);
        }
        if (around[1] != null && !child.isPresent()) {
            child = around[1].findByPosition(p, level);
        }
        if (around[2] != null && !child.isPresent()) {
            child = around[2].findByPosition(p, level);
        }
        if (around[3] != null && !child.isPresent()) {
            child = around[3].findByPosition(p, level);
        }
        if (teleport != null && !child.isPresent()) {
            child = teleport.findByPosition(p, level);
        }

        return child;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
        int childSteps = steps + 1;

        if (around[0] != null) {
            around[0].setSteps(childSteps);
        }
        if (around[1] != null) {
            around[1].setSteps(childSteps);
        }
        if (around[2] != null) {
            around[2].setSteps(childSteps);
        }
        if (around[3] != null) {
            around[3].setSteps(childSteps);
        }
        if (teleport != null) {
            teleport.setSteps(childSteps);
        }
    }

    @Override
    public String toString() {
        return "DfsNodeDonut2{" +
                "pos=" + pos +
                ", steps=" + steps +
                ", level=" + level +
                ", parent=" + (parent != null ? parent.pos.toString() : "-") +
                ", up=" + (around[0] != null ? around[0].pos.toString() : "-") +
                ", down=" + (around[1] != null ? around[1].pos.toString() : "-") +
                ", left=" + (around[2] != null ? around[2].pos.toString() : "-") +
                ", right=" + (around[3] != null ? around[3].pos.toString() : "-") +
                ", teleport=" + (teleport != null ? teleport.pos.toString() : "-") +
                '}';
    }
}
