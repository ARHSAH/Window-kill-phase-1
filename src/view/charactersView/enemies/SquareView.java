package view.charactersView.enemies;

import java.awt.*;
import java.util.ArrayList;

public class SquareView {
    private double x, y;
    private int id;
    private int length;



    public static ArrayList<SquareView> squareViews = new ArrayList<>();
    public SquareView(int id){
        this.id = id;
        squareViews.add(this);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}
    public void draw(Graphics2D g2D){
        //System.out.println(length);
        g2D.drawRect((int)x, (int)y, length, length);
    }

}
