package model.movement;

import java.awt.*;
import java.awt.geom.Point2D;

public class Direction {
    boolean isUpward=false;
    boolean isDownward=false;
    double slope;
    DirectionState state = DirectionState.neutral;
    public Direction(Point2D point){
        if (point.getX() == 0 && point.getY() > 0) {
            isUpward = true;
            state = DirectionState.positive;
        }
        else if (point.getX() == 0 && point.getY() < 0) {
            isDownward=true;
            state = DirectionState.negative;
        }
        else if (point.getX() ==0 ) state=DirectionState.neutral;
        else {
            this.slope = point.getY() / point.getX();
            if (point.getX() > 0) this.state = DirectionState.positive;
            else this.state=DirectionState.negative;
        }
    }


    public Point2D getDirectionVector(){
        if (state == DirectionState.neutral) return new Point(0,0);
        if (isUpward) return new Point2D.Double(0,1);
        if (isDownward) return new Point2D.Double(0,-1);
        double magnitude=Math.sqrt(1 + slope * slope);
        Point2D.Double normalVector=new Point2D.Double(1 / magnitude,slope / magnitude);
        if (state == DirectionState.negative) normalVector = new Point2D.Double(-normalVector.x,-normalVector.y);
        return normalVector;
    }

    public enum DirectionState{
        negative,positive,neutral
    }
}
