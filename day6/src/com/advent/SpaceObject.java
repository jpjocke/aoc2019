package com.advent;

import java.util.ArrayList;
import java.util.List;

public class SpaceObject {
    String id;
    List<SpaceObject> children;

    public SpaceObject(String id) {
        this.id = id;
        children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public boolean add(SpaceObject spaceObject) {
        return children.add(spaceObject);
    }

    public List<SpaceObject> getChildren() {
        return children;
    }
}
