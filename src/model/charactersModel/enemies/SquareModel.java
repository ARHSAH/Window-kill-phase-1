package model.charactersModel.enemies;

import controller.Variables;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Controller.createSquarantineView;
import static controller.Utils.multiplyVector;

public class SquareModel implements Movable {
    private double x , y;
    private int hp;
    private int damage;
    private int id;
    private Point2D center ;



    private int length;
    Direction direction;
    public static ArrayList<SquareModel> squareModels = new ArrayList<>();
    public SquareModel(double x, double y, int length, int hp, int damage, Direction direction){
        Variables.squaresNumber ++;
        this.x = x;
        this.y = y;
        this.length = length;
        this.hp = hp;
        this.damage = damage;
        this.direction = direction;
        this.id = Variables.squaresNumber;
        center = new Point2D.Double(x + (length / 2), y + (length / 2));
        squareModels.add(this);
        createSquarantineView(id);
    }
    @Override
    public void move(Direction direction, double speed) {
        Point2D vector = multiplyVector(direction.getDirectionVector(), speed);
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Point2D getCenter() {return center;}

    public void setCenter(Point2D center) {this.center = center;}




    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {

    }

    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}
}
