package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CollectibleView {
    private double radius;
    private Point2D center = new Point2D.Double(0, 0);
    private int id;
    public static ArrayList<CollectibleView> collectibleViews = new ArrayList<>();
    public CollectibleView(int id){
        this.id = id;
        collectibleViews.add(this);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void draw(Graphics2D g2D){
        g2D.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius),
                (int)(2 * radius), (int)(2 * radius));
    }
}
