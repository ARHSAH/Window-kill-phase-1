package model.collision;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
import view.panelsView.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.FRAME_SHRINK_AMOUNT;
import static controller.Utils.distance;
import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;

public class Collision {
    static int frameTimer;

    public static void checkBulletFrame() {
        ArrayList<BulletModel> bulletModels1 = new ArrayList<>();
        for (BulletModel value : bulletModels) {
            if (value.getX() < 2 || value.getX() > frameWidth ||
                    value.getY() < 2 || value.getY() > frameHeight) {
                bulletModels1.add(value);
            }
        }
        for (BulletModel value : bulletModels1) {
            if (value.getX() > frameWidth) {
                frameExtendingDirection = "right";
            } else if (value.getX() < 1) {
                frameExtendingDirection = "left";
            } else if (value.getY() > frameHeight) {
                frameExtendingDirection = "bottom";
            } else if (value.getY() < 1) {
                frameExtendingDirection = "top";
            }
            value.clip.stop();
            bulletModels.remove(value);
        }
    }

    public static Point2D collisionCircleSquare(Point2D.Double center, double radius, SquareModel squareModel) {
        ArrayList<Point2D> vertices = new ArrayList<>();
        vertices.add(new Point2D.Double(squareModel.getX(), squareModel.getY()));
        vertices.add(new Point2D.Double(squareModel.getX() + squareModel.getLength(),
                squareModel.getY()));
        vertices.add(new Point2D.Double(squareModel.getX() + squareModel.getLength(),
                squareModel.getY() + squareModel.getLength()));
        vertices.add(new Point2D.Double(squareModel.getX(),
                squareModel.getY() + squareModel.getLength()));
        return closestPointOnPolygon(center, vertices);
    }
    public static Point2D closestPointOnPolygon(Point2D point, ArrayList<Point2D> vertices) {
        double minDistance = Double.MAX_VALUE;
        Point2D closest = null;
        for (int i = 0; i < vertices.size(); i++) {
            Point2D temp = getClosestPointOnSegment(vertices.get(i), vertices.get((i + 1) % vertices.size()), point);
            double distance = temp.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                closest = temp;
            }
        }
        return closest;
    }

    public static Point2D getClosestPointOnSegment(Point2D head1, Point2D head2, Point2D point) {
        double u = ((point.getX() - head1.getX()) * (head2.getX() - head1.getX()) + (point.getY() - head1.getY()) * (head2.getY() - head1.getY())) / head2.distanceSq(head1);
        if (u > 1.0) return (Point2D) head2.clone();
        else if (u <= 0.0) return (Point2D) head1.clone();
        else
            return new Point2D.Double(head2.getX() * u + head1.getX() * (1.0 - u) + 0.5, head2.getY() * u + head1.getY() * (1.0 - u) + 0.5);
    }
}
