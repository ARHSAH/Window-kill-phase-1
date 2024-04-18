package model.charactersModel.enemies;

import controller.Variables;
import model.movement.Direction;

import java.util.ArrayList;

import static controller.Controller.createSquarantineView;

public class SquareModel {
    private double x , y;
    private int hp;
    private int damage;
    private int id;
    private int length;
    Direction direction;
    public static ArrayList<SquareModel> squareModels = new ArrayList<>();
    public SquareModel(double x, double y, int length, int hp, int damage, Direction direction){
        Variables.squarantinesNumber ++;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
        this.direction = direction;
        this.id = Variables.squarantinesNumber;
        squareModels.add(this);
        createSquarantineView(id);
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


}
