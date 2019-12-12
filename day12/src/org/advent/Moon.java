package org.advent;

import org.advent.util.IntVector;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Objects.equals(position, moon.position) &&
                Objects.equals(velocity, moon.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }
}
