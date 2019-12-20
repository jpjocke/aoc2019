package org.advent;

import org.advent.util.IntPoint;

public class DonutTeleporter {
    private String id;
    private IntPoint a;
    private IntPoint b;

    public DonutTeleporter(String id, IntPoint a) {
        this.id = id;
        this.a = a;
    }

    public void setB(IntPoint b) {
        this.b = b;
    }

    public String getId() {
        return id;
    }

    public IntPoint getA() {
        return a;
    }

    public IntPoint getB() {
        return b;
    }
}
