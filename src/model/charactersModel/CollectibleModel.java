package model.charactersModel;

import controller.Variables;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Controller.createCollectibleView;

public class CollectibleModel {
    private double radius;
    private Point2D center;
    private int xp = 5;
    private int id;
    private int timer;
    public static ArrayList<CollectibleModel> collectibleModels = new ArrayList<>();
    public CollectibleModel(Point2D center, double radius){
        Variables.collectiblesNumber ++;
        this.radius = radius;
        this.center = center;
        this.id = Variables.collectiblesNumber;
        collectibleModels.add(this);
        createCollectibleView(id);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }


    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
