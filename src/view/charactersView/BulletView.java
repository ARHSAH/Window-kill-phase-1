package view.charactersView;

import model.charactersModel.BulletModel;

import java.awt.*;
import java.util.ArrayList;

import static controller.Variables.frameWidth;

public class BulletView {
    private double x, y;
    private int radius;
    public static ArrayList<BulletView> bulletViews = new ArrayList<>();
    private int id;
    public BulletView(int id){
        this.id = id;
        bulletViews.add(this);
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

    public double getRadius() {
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
    public void draw(Graphics2D g2D){

        g2D.fillOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
    }
}
