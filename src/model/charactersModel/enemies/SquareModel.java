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
    private boolean dash;
    public SquareModel(double x, double y, int length, int hp, int damage, double speed){
        Variables.squaresNumber ++;
        this.x = x;
        this.y = y;
        this.length = length;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        direction = new Point2D.Double(0, 0);
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
    public Point2D getCenter() {
        return new Point2D.Double(x + (double) (length / 2), y + (double) (length / 2));}

    public void setCenter(Point2D center) {this.center = center;}




    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {
        if(new Random().nextInt(1, 400) == 5){
            setSpeed(7);
        }
        Point2D vector = multiplyVector(direction, getSpeed());
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

    public boolean isDash() {
        return dash;
    }

    public void setDash(boolean dash) {
        this.dash = dash;
    }

    public ArrayList<Point2D> getVertices(){
        ArrayList<Point2D> vertices = new ArrayList<>();
        vertices.add(new Point2D.Double(getX(), getY()));
        vertices.add(new Point2D.Double(getX() + getLength(),
                getY()));
        vertices.add(new Point2D.Double(getX() + getLength(),
                getY() + getLength()));
        vertices.add(new Point2D.Double(getX(),
                getY() + getLength()));
        return vertices;
    }

}
