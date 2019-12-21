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
    protected DfsNodeDonut2 up;
    protected DfsNodeDonut2 down;
    protected DfsNodeDonut2 left;
    protected DfsNodeDonut2 right;
    protected DfsNodeDonut2 teleport;

    public DfsNodeDonut2(DfsNodeDonut2 parent, IntPoint pos, int steps, int level) {
        this.pos = pos;
        this.steps = steps;
        this.parent = parent;
        this.level = level;
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
        if (up == null) {
            IntPoint upP = pos.copy();
            upP.y--;
            up = exploreForPos(dfsi, upP);
        }

        if (down == null) {
            IntPoint upP = pos.copy();
            upP.y++;
            down = exploreForPos(dfsi, upP);
        }

        if (left == null) {
            IntPoint upP = pos.copy();
            upP.x--;
            left = exploreForPos(dfsi, upP);
        }

        if (right == null) {
            IntPoint upP = pos.copy();
            upP.x++;
            right = exploreForPos(dfsi, upP);
        }
        if (DEBUG) {
          //       map.print(getTop(), pos, level);
        }

        if (up != null) {
            up.explore(dfsi, map);
        }
        if (down != null) {
            down.explore(dfsi, map);
        }
        if (left != null) {
            left.explore(dfsi, map);
        }
        if (right != null) {
            right.explore(dfsi, map);
        }
        if (teleport != null) {
            teleport.explore(dfsi, map);
        }
    }

    private DfsNodeDonut2 getTop() {
        if (steps == 0) {
            return this;
        }
        return parent.getTop();
    }

    public void removeFromParent() {
        if (parent.up != null && parent.up.getPos().equals(this.pos)) {
            parent.up = null;
        }
        if (parent.down != null && parent.down.getPos().equals(this.pos)) {
            parent.down = null;
        }
        if (parent.left != null && parent.left.getPos().equals(this.pos)) {
            parent.left = null;
        }
        if (parent.right != null && parent.right.getPos().equals(this.pos)) {
            parent.right = null;
        }
        if (parent.teleport != null && parent.teleport.getPos().equals(this.pos)) {
            DfsNodeDonut2 tmp = parent;
            DfsNodeDonut2 t = parent.teleport;
            parent.teleport = null;
            parent.removeFromParent();
            t.teleport = tmp;
        }

        // also remove children!!
        up = null;
        down = null;
        left = null;
        right = null;
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
                        d.teleport = t;
                    }


                    return d;
                } else {
                    // we have not been here
                    return new DfsNodeDonut2(this, p, steps + 1, level);

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
            node.removeFromParent();
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
        Optional<DfsNodeDonut2> child = Optional.empty();
        if (up != null) {
            child = up.findByPosition(p, level);
        }
        if (down != null && !child.isPresent()) {
            child = down.findByPosition(p, level);
        }
        if (left != null && !child.isPresent()) {
            child = left.findByPosition(p, level);
        }
        if (right != null && !child.isPresent()) {
            child = right.findByPosition(p, level);
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

        if (up != null) {
            up.setSteps(childSteps);
        }
        if (down != null) {
            down.setSteps(childSteps);
        }
        if (left != null) {
            left.setSteps(childSteps);
        }
        if (right != null) {
            right.setSteps(childSteps);
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
                ", up=" + (up != null ? up.pos.toString() : "-") +
                ", down=" + (down != null ? down.pos.toString() : "-") +
                ", left=" + (left != null ? left.pos.toString() : "-") +
                ", right=" + (right != null ? right.pos.toString() : "-") +
                ", teleport=" + (teleport != null ? teleport.pos.toString() : "-") +
                '}';
    }
}
