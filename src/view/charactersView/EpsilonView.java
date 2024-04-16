package view.charactersView;

import java.awt.*;

public class EpsilonView {
    private static EpsilonView INSTANCE;
    private EpsilonView(){}
    private double x, y;
    private int radius;

    public static EpsilonView getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new EpsilonView();
        }
        return INSTANCE;
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
    public void draw(Graphics2D g2D){
        g2D.drawOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
    }
}
