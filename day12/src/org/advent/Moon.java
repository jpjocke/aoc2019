package org.advent;

import org.advent.util.IntVector;

public class Moon {
    IntVector position;
    IntVector velocity;

    public Moon(IntVector position) {
        this.position = position;
        velocity = new IntVector(0, 0, 0);
    }

    public void applyMoonGravityOnVelocity(Moon other) {
        if (other.position.x > position.x) {
            velocity.x++;
        }
        if (other.position.x < position.x) {
            velocity.x--;
        }

        if (other.position.y > position.y) {
            velocity.y++;
        }
        if (other.position.y < position.y) {
            velocity.y--;
        }

        if (other.position.z > position.z) {
            velocity.z++;
        }
        if (other.position.z < position.z) {
            velocity.z--;
        }
    }

    public void applyVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
        position.z += velocity.z;
    }

    public int potentialEnergy() {
        return Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);
    }

    public int kineticEnergy() {
        return Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
    }

    @Override
    public String toString() {
        return "Moon{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }
}
