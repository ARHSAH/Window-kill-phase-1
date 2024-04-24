package view.charactersView.enemies;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleView {
    private ArrayList<Point2D> vertices = new ArrayList<>();
    private int id;
    public static ArrayList<TriangleView> triangleViews = new ArrayList<>();
    public TriangleView(int id){
        this.id = id;
        triangleViews.add(this);
    }

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void draw(Graphics2D g2D){
        if(!vertices.isEmpty()) {
            int[] xPoints = {(int) vertices.get(0).getX(), (int) vertices.get(1).getX(), (int) vertices.get(2).getX()};
            int[] yPoints = {(int) vertices.get(0).getY(), (int) vertices.get(1).getY(), (int) vertices.get(2).getY()};
            g2D.drawPolygon(xPoints, yPoints, 3);
        }
    }
}
