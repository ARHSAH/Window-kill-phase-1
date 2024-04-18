package model.charactersModel;

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


    public BulletModel(double x , double y, int radius) {
        try {
            File file = new File("bulletSound.wav");
            ;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
//            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            gainControl.setValue(volume);
            clip.loop(1);
            clip.start();
        } catch (Exception e) {

        }
        bulletNumbers ++;
        this.x = x;
        this.y = y;
        this.radius = radius;
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
        Point2D vector = multiplyVector(direction.getDirectionVector(), speed);
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {

    }

}
