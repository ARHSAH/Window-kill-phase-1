package model.movement;

import model.movement.Direction;

public interface Movable {
    void move(Direction direction, double speed);
    void move(Direction direction);
    void move();
}
