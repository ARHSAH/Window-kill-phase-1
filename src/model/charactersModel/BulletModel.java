package model.charactersModel;

import controller.Variables;
import model.movement.Direction;
import model.movement.Movable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static controller.Controller.createBulletView;
import static controller.Utils.multiplyVector;
import static controller.Variables.*;

public class BulletModel implements Movable {
    private double x, y;
    private int radius;

    public static ArrayList<BulletModel> bulletModels = new ArrayList<>();
    private int id;
    public Clip clip;
    private int damage;
    private Point2D direction;
    private double speed;


    public BulletModel(double x , double y, int radius, int damage,double speed, Point2D direction) {
        try {
            File file = new File("bulletSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(1);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bulletNumbers ++;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction;
        bulletModels.add(this);
        this.id = bulletNumbers;
        createBulletView(id);
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void move(Direction direction, double speed) {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {
        Point2D vector = multiplyVector(direction, speed);
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
