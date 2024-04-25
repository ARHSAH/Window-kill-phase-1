package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.movement.Direction;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.charactersView.enemies.SquareView;
import view.charactersView.enemies.TriangleView;
import view.panelsView.GameFrame;
import view.panelsView.GamePanel;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Constants.*;
import static controller.Utils.addVectors;
import static controller.Utils.reverseVector;
import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.charactersModel.enemies.TriangleModel.triangleModels;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.enemies.SquareView.squareViews;
import static view.charactersView.enemies.SquareView.squareViews;
import static view.charactersView.enemies.TriangleView.triangleViews;

public class Controller {
    public static void createBulletView(int id) {
        new BulletView(id);
    }

    public static void createSquareView(int id) {

        new SquareView(id);
    }

    public static void createTriangleView(int id) {
        new TriangleView(id);
    }

    public static void setViewLocation() {
        EpsilonView.getINSTANCE().setX(EpsilonModel.getINSTANCE().getX());
        EpsilonView.getINSTANCE().setY(EpsilonModel.getINSTANCE().getY());
        EpsilonView.getINSTANCE().setRadius(EpsilonModel.getINSTANCE().getRadius());
        for (BulletView value : bulletViews) {
            BulletModel bulletModel = findBulletModel(value.getId());
            if (bulletModel == null) {
                bulletViews.remove(value);
                return;
            }
            value.setX(bulletModel.getX());
            value.setY(bulletModel.getY());
            value.setRadius(bulletModel.getRadius());
        }

        for (SquareView value : squareViews) {
            SquareModel squareModel = findSquareModel(value.getId());
            if (squareModel == null) {
                squareViews.remove(value);
                return;
            }
            value.setX(squareModel.getX());
            value.setY(squareModel.getY());
            value.setLength(squareModel.getLength());
        }
        for (TriangleView value : triangleViews) {
            TriangleModel triangleModel = findTriangleModel(value.getId());
            if (triangleModel == null) {
                triangleViews.remove(value);
                return;
            }
            value.setVertices(triangleModel.getVertices());
        }

    }

    public static BulletModel findBulletModel(int id) {
        for (BulletModel value : bulletModels) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    public static SquareModel findSquareModel(int id) {
        for (SquareModel value : squareModels) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    public static TriangleModel findTriangleModel(int id) {
        for (TriangleModel value : triangleModels) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    public static void startShrinkage() {
        if (frameHeight > MINIMUM_FRAME_SIZE && frameWidth > MINIMUM_FRAME_SIZE) {
            GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX() + START_SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY() + START_SHRINK_AMOUNT));
            frameWidth -= 2 * START_SHRINK_AMOUNT;
            frameHeight -= 2 * START_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
            EpsilonModel.getINSTANCE().setX(170);
            EpsilonModel.getINSTANCE().setY(170);
        } else {
            firstOfGame = false;
        }
    }

    public static void gameShrinkage() {
        if (frameWidth > MINIMUM_FRAME_SIZE) {
            GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX() +
                    FRAME_SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY()));
            frameWidth -= 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        }
        if (frameHeight > MINIMUM_FRAME_SIZE) {
            GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX(),
                    GameFrame.getINSTANCE().getY() + FRAME_SHRINK_AMOUNT));
            frameHeight -= 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        }
    }

    public static void impact(Point2D point){
        if(EpsilonModel.getINSTANCE().getCenter().distance(point) > 50 &&
                EpsilonModel.getINSTANCE().getCenter().distance(point) < 130){
            Point2D effectVector = new Point2D.Double(point.getX() - EpsilonModel.getINSTANCE().getCenter().getX(),
                    point.getY() - EpsilonModel.getINSTANCE().getCenter().getY());
            Direction directionEpsilon = new Direction(reverseVector(effectVector));
            EpsilonModel.getINSTANCE().setDirection(directionEpsilon.getDirectionVector());
            EpsilonModel.getINSTANCE().setSpeed(3 + EpsilonModel.getINSTANCE().getSpeed());
            EpsilonModel.getINSTANCE().setImpact(true);
        }
        for(SquareModel squareModel : squareModels){
            if(squareModel.getCenter().distance(point) > 50 &&
            squareModel.getCenter().distance(point) < 200){
                Point2D effectVector = new Point2D.Double(point.getX() - squareModel.getCenter().getX(),
                        point.getY() - squareModel.getCenter().getY());
                Direction directionSquare = new Direction(reverseVector(effectVector));
                squareModel.setDirection(directionSquare.getDirectionVector());
                squareModel.setSpeed(3 + squareModel.getSpeed());
                squareModel.setImpact(true);
            }
        }
        for(TriangleModel triangleModel : triangleModels){
            if(triangleModel.getCenter().distance(point) > 50 &&
                    triangleModel.getCenter().distance(point) < 130){
                Point2D effectVector = new Point2D.Double(point.getX() - triangleModel.getCenter().getX(),
                        point.getY() - triangleModel.getCenter().getY());
                Direction directionSquare = new Direction(reverseVector(effectVector));
                triangleModel.setDirection(directionSquare.getDirectionVector());
                triangleModel.setSpeed(3 + triangleModel.getSpeed());
                triangleModel.setImpact(true);
            }
        }

    }

    public static void frameExtending(String direction) {
        if (direction.equals("right")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX() + FRAME_SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY()));
            frameWidth += 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        } else if (direction.equals("left")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX() - 3 * FRAME_SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY()));
            frameWidth += 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        } else if (direction.equals("bottom")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX(),
                    GameFrame.getINSTANCE().getY() + FRAME_SHRINK_AMOUNT));
            frameHeight += 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        } else if (direction.equals("top")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX(),
                    GameFrame.getINSTANCE().getY() - 3 * FRAME_SHRINK_AMOUNT));
            frameHeight += 2 * FRAME_SHRINK_AMOUNT;
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        }
        GamePanel.getINSTANCE().setSize(frameWidth, frameHeight);
    }

    public static void updateTimer() {
        minutes1 = ((elapsedTime / 60) / 60) / 10;
        minutes2 = ((elapsedTime / 60) / 60) % 10;
        seconds1 = ((elapsedTime / 60) / 10) % 6;
        seconds2 = (elapsedTime / 60) % 10 ;
    }
}
