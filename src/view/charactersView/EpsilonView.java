package view.charactersView;

import java.awt.*;

import static controller.Constants.VERTICES_DISTANCE;
import static controller.Constants.VERTICES_RADIUS;
import static controller.Variables.epsilonVertices;

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
        for (int i = 0; i < epsilonVertices ; i++) {
            int x = (int) (getX() + (radius + VERTICES_DISTANCE) * Math.cos(2 * Math.PI * i / epsilonVertices ));
            int y = (int) (getY() + (radius + VERTICES_DISTANCE) * Math.sin(2 * Math.PI * i / epsilonVertices ));
            g2D.fillOval(x - VERTICES_RADIUS, y - VERTICES_RADIUS, 2 * VERTICES_RADIUS
                    , 2 * VERTICES_RADIUS);
        }
    }
}
