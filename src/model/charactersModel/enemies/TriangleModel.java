package model.charactersModel.enemies;

import controller.Variables;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Controller.createSquareView;
import static controller.Controller.createTriangleView;
import static controller.Utils.multiplyVector;

public class TriangleModel implements Movable {
    private Point2D A;
    private int length;
    private int hp;
    private int damage;
    private int id;
    private double speed;
    private Point2D direction;
    private Point2D center;
    public static ArrayList<TriangleModel> triangleModels = new ArrayList<>();
    private boolean impact;
    public TriangleModel(Point2D A, int length, int hp, int damage, double speed){
        Variables.trianglesNumber ++;
        this.A = A;
        this.length = length;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        direction = new Point2D.Double(0, 0);
        this.id = Variables.trianglesNumber;
        triangleModels.add(this);
        createTriangleView(id);
    }

    @Override
    public void move(Direction direction, double speed) {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {
        Point2D vector = multiplyVector(direction, getSpeed());
        setA(new Point2D.Double(getA().getX() + vector.getX(), getA().getY() + vector.getY() ));
    }
    public ArrayList<Point2D> getVertices(){
        ArrayList<Point2D> vertices = new ArrayList<>();
        vertices.add(getA());
        vertices.add(new Point2D.Double(A.getX() - (double) length / 2, A.getY() +
                (double) length * Math.sqrt(3) * (0.5)));
        vertices.add(new Point2D.Double(A.getX() + (double) length / 2, A.getY() +
                (double) length * Math.sqrt(3) * (0.5)));
        return vertices;
    }

    public Point2D getA() {
        return A;
    }

    public void setA(Point2D A) {
        this.A = A;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public boolean isImpact() {
        return impact;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }

    public Point2D getCenter() {
        center = new Point2D.Double(getA().getX(), A.getY() +
                (double) length * Math.sqrt(3) * (0.33));
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }
}
