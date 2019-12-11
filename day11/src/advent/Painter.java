package advent;

import org.advent.util.IntPoint;
import org.advent.util.intmachine.IntCodes;
import org.advent.util.intmachine.IntMachine;

import java.util.List;

public class Painter {
    IntPoint position;
    Hull hull;
    IntMachine machine;
    Direction direction = Direction.UP;

    public Painter(IntPoint position, Hull hull, IntCodes instructions) {
        this.position = position;
        this.hull = hull;
        machine = new IntMachine(instructions);
    }

    public void start() {
        int iterations = 0;
        int input;
        System.out.println("STARTING");
        while (true) {
            input = hull.getValueAt(position);
            machine.addInput(input);

            machine.execute();

            // machine.getOutput().stream().forEach(o -> System.out.println(o));
            List<Long> output = machine.getOutput();

            long color = output.get(output.size() - 2);
            if(hull.setValueAt(position, (int) color)) {
                iterations++;
            }

            long dir = output.get(output.size() - 1);
            doDirectionMove((int) dir);


            System.out.println("input: " + input + "col: " + color + ", dir: " + dir);
            hull.print(this);
            System.out.println("-------------------------------------------");

            if (machine.isDone()) {
                break;
            }
        }

        System.out.println("iterations: " + iterations);
    }

    private void doDirectionMove(int dir) {
        switch (direction) {
            case UP:
                if (dir == 0) {
                    direction = Direction.LEFT;

                } else {
                    direction = Direction.RIGHT;
                }
                break;
            case DOWN:
                if (dir == 0) {
                    direction = Direction.RIGHT;

                } else {
                    direction = Direction.LEFT;
                }
                break;
            case LEFT:
                if (dir == 0) {
                    direction = Direction.DOWN;

                } else {
                    direction = Direction.UP;
                }
                break;
            case RIGHT:
            default:
                if (dir == 0) {
                    direction = Direction.UP;

                } else {
                    direction = Direction.DOWN;
                }
                break;
        }

        switch (direction) {
            case UP:
                position.y--;
                break;
            case DOWN:
                position.y++;
                break;
            case LEFT:
                position.x--;
                break;
            case RIGHT:
            default:
                position.x++;

        }
    }

    enum Direction {UP, LEFT, RIGHT, DOWN}
}
