package model.charactersModel.enemies;

import controller.Variables;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static controller.Controller.createSquareView;
import static controller.Utils.multiplyVector;

public class SquareModel implements Movable {
    private double x , y;
    private int hp;
    private int damage;
    private int id;
    private Point2D center ;

    private int length;
    private double speed;
    Point2D direction;
    public static ArrayList<SquareModel> squareModels = new ArrayList<>();
    private boolean impact;
    int dash = 1;
    public SquareModel(double x, double y, int length, int hp, int damage, double speed, Point2D direction){
        Variables.squaresNumber ++;
        this.x = x;
        this.y = y;
        this.length = length;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction;
        this.id = Variables.squaresNumber;
        center = new Point2D.Double(x + (double) (length / 2), y + (double) (length / 2));
        squareModels.add(this);
        createSquareView(id);
    }
    @Override
    public void move(Direction direction, double speed) {

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

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }
    public Point2D getCenter() {return center;}

    public void setCenter(Point2D center) {this.center = center;}




    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {
        Point2D vector = multiplyVector(direction, getSpeed() * dash);
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
    }

    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isImpact() {
        return impact;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }
}
