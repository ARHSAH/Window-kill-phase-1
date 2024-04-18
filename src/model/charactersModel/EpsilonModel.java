package model.charactersModel;

import controller.Variables;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;

import static controller.Constants.BALL_SPEED;
import static controller.Utils.multiplyVector;
import static controller.Variables.*;

public class EpsilonModel implements Movable {
    private static EpsilonModel INSTANCE;
    private int radius, hp;
    private double x, y;

    double speed;

    public static EpsilonModel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new EpsilonModel((double) frameWidth / 2 ,
                    (double) frameHeight / 2, 25, 100, 0.5 );
        }
        return INSTANCE;
    }
    private EpsilonModel(double x, double y, int radius , int hp, double speed){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.hp = hp;
        this.speed = speed;
    }

    public static void setEpsilonSpeed(){
        if(getINSTANCE().getSpeed() < BALL_SPEED){
            getINSTANCE().setSpeed(getINSTANCE().getSpeed() + 3.5 + (double)sensitivity / 50);
        }
    }

    @Override
    public void move(Direction direction, double speed) {
    }

    @Override
    public void move(Direction direction){
        double limit = BALL_SPEED + INSTANCE.getRadius();
        if((direction.getDirectionVector().getY() < 0 && INSTANCE.getY() > limit) ||
                (direction.getDirectionVector().getY() > 0 && INSTANCE.getY() < frameHeight - limit - 26 ) ) {
            INSTANCE.setY(INSTANCE.getY() +
                    multiplyVector(direction.getDirectionVector(), INSTANCE.getSpeed()).getY());
            setEpsilonSpeed();
        }
        if((direction.getDirectionVector().getX() < 0 && INSTANCE.getX() > limit) ||
                (direction.getDirectionVector().getX() > 0 && INSTANCE.getX() < frameWidth - limit - 10 ) ) {
            INSTANCE.setX(INSTANCE.getX() +
                    multiplyVector(direction.getDirectionVector(), INSTANCE.getSpeed()).getX());
            setEpsilonSpeed();
        }
    }

    @Override
    public void move() {

    }

    public static void setINSTANCE(EpsilonModel INSTANCE) {
        EpsilonModel.INSTANCE = INSTANCE;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



}
