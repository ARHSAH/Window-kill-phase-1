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

    public static Point2D circlePolygonCollision(Point2D.Double center
            , ArrayList<Point2D> vertices) {
        return closestPointOnPolygon(center, vertices);
    }

    public static boolean verticesEpsilonCollision(){
        return false;
    }

    public static Point2D polygonsCollision(ArrayList<Point2D> vertices1, ArrayList<Point2D> vertices2) {
        for (int i = 0; i < vertices1.size(); i++) {
            Point2D current = vertices1.get(i);
            Point2D next = vertices1.get((i + 1) % vertices1.size());
            for (int j = 0; j < vertices2.size(); j++) {
                Point2D otherCurrent = vertices2.get(j);
                Point2D otherNext = vertices2.get((j + 1) % vertices2.size());
                if (findIntersection(current, next, otherCurrent, otherNext) != null) {
                    return findIntersection(current, next, otherCurrent, otherNext);
                }
            }
        }
        return null;
    }

    public static Point2D findIntersection(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        if (doLinesIntersect(p1, q1, p2, q2)) {
            double A1 = q1.getY() - p1.getY();
            double B1 = p1.getX() - q1.getX();
            double C1 = A1 * p1.getX() + B1 * p1.getY();
            double A2 = q2.getY() - p2.getY();
            double B2 = p2.getX() - q2.getX();
            double C2 = A2 * p2.getX() + B2 * p2.getY();
            double det = A1 * B2 - A2 * B1;
            if (det == 0) {
                return null;
            }
            else {
                double x = (B2 * C1 - B1 * C2) / det;
                double y = (A1 * C2 - A2 * C1) / det;
                return new Point2D.Double(x, y);
            }
        }
        return null;
    }
    public static boolean doLinesIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        return false;
    }
    private static int orientation(Point2D p, Point2D q, Point2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) {
            return 0; }
        return (val > 0) ? 1 : 2;
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
