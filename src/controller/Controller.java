package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.CollectibleModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.movement.Direction;
import view.charactersView.BulletView;
import view.charactersView.CollectibleView;
import view.charactersView.EpsilonView;
import view.charactersView.enemies.SquareView;
import view.charactersView.enemies.TriangleView;
import view.panelsView.GameFrame;
import view.panelsView.GameOverPanel;
import view.panelsView.GamePanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static controller.Constants.*;
import static controller.Utils.addVectors;
import static controller.Utils.reverseVector;
import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.CollectibleModel.collectibleModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.charactersModel.enemies.TriangleModel.triangleModels;
import static model.sounds.Sounds.enemySpawnSound;
import static model.sounds.Sounds.gameOverSound;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.CollectibleView.collectibleViews;
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
    public static void createCollectibleView(int id) {
        new CollectibleView(id);
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
        for (CollectibleView value : collectibleViews) {
           CollectibleModel collectibleModel = findCollectibleModel(value.getId());
            if (collectibleModel == null) {
                collectibleViews.remove(value);
                return;
            }
            value.setCenter(collectibleModel.getCenter());
            value.setRadius(collectibleModel.getRadius());
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
    public static CollectibleModel findCollectibleModel(int id) {
        for (CollectibleModel value : collectibleModels) {
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
            GameFrame.getINSTANCE().setSize(new Dimension((int)frameWidth, (int)frameHeight));
            EpsilonModel.getINSTANCE().setX(150);
            EpsilonModel.getINSTANCE().setY(150);
        } else {
            firstOfGame = false;
        }
    }

    public static void gameShrinkage() {
        if(waveTimer > 500) {
            if (frameWidth > MINIMUM_FRAME_SIZE && !frameExtendingDirection.equals("left") && !frameExtendingDirection.equals("right")) {
                GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX() +
                        frameShrinkAmount,
                        GameFrame.getINSTANCE().getY()));
                frameWidth -= 2 * frameShrinkAmount;
                GameFrame.getINSTANCE().setSize(new Dimension((int) frameWidth, (int) frameHeight));
            }
            if (frameHeight > MINIMUM_FRAME_SIZE && !frameExtendingDirection.equals("top") && !frameExtendingDirection.equals("bottom")) {
                GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX(),
                        GameFrame.getINSTANCE().getY() + frameShrinkAmount));
                frameHeight -= 2 * frameShrinkAmount;
                GameFrame.getINSTANCE().setSize(new Dimension((int) frameWidth, (int) frameHeight));
            }
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

    public static void apolloImpact(Point2D point){
        for(SquareModel squareModel : squareModels){
            if(squareModel.getCenter().distance(point) > 50 &&
                    squareModel.getCenter().distance(point) < 200){
                Point2D effectVector = new Point2D.Double(point.getX() - squareModel.getCenter().getX(),
                        point.getY() - squareModel.getCenter().getY());
                Direction directionSquare = new Direction(reverseVector(effectVector));
                squareModel.setDirection(directionSquare.getDirectionVector());
                squareModel.setSpeed(15);
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
                triangleModel.setSpeed(15);
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
        } else if (direction.equals("left")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX() - 3 * FRAME_SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY()));
            frameWidth += 2 * FRAME_SHRINK_AMOUNT;
        } else if (direction.equals("bottom")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX(),
                    GameFrame.getINSTANCE().getY() + FRAME_SHRINK_AMOUNT));
            frameHeight += 2 * FRAME_SHRINK_AMOUNT;
        } else if (direction.equals("top")) {
            GameFrame.getINSTANCE().setLocation(new Point(
                    GameFrame.getINSTANCE().getX(),
                    GameFrame.getINSTANCE().getY() - 3 * FRAME_SHRINK_AMOUNT));
            frameHeight += 2 * FRAME_SHRINK_AMOUNT;;
        }
        GameFrame.getINSTANCE().setSize((int)frameWidth, (int)frameHeight);
        GamePanel.getINSTANCE().setSize((int)frameWidth, (int)frameHeight);
    }

    public static void updateTimer() {
        minutes1 = ((elapsedTime / 60) / 60) / 10;
        minutes2 = ((elapsedTime / 60) / 60) % 10;
        seconds1 = ((elapsedTime / 60) / 10) % 6;
        seconds2 = (elapsedTime / 60) % 10 ;
    }
    public static void updateCollectiblesTimer(){
        for (CollectibleModel collectibleModel : collectibleModels){
            collectibleModel.setTimer(collectibleModel.getTimer() + 1);
        }
    }
    public static void wave(){
        total = difficulty / 2 + 12;
        if(waveTimer >= 500 ){
            if(wave == 1 && waveTimer % 500 == 0 && waveTimer <= 1500){
                enemySpawnSound();
                for(int i = 0 ; i < total / 7 ; i ++){
                   randomTriSqa();
               }
            }else if(wave == 2 && waveTimer % 600 == 500 && waveTimer <= 1700){
                enemySpawnSound();
                for(int i = 0 ; i < total / 5 ; i ++){
                    randomTriSqa();
                }
            }else if(wave == 3 && waveTimer % 700 == 500  && waveTimer <= 1900){
                enemySpawnSound();
                for(int i = 0 ; i < total / 3 ; i ++){
                    randomTriSqa();
                }
            }
        }
    }
    public static void randomTriSqa(){
        int a = new Random().nextInt(1, 3);
        if(a == 1){
            int a1 = new Random().nextInt(1, 3);
            if(a1 == 1) {
                new SquareModel(new Random().nextInt(-20, (int) frameWidth + 20),
                        frameHeight + 50,
                        SQUARE_LENGTH, SQUARE_HP, SQUARE_DAMAGE, SQUARE_SPEED);
            }else{
                new SquareModel(new Random().nextInt(-20, (int) frameWidth + 20),
                        -50,
                        SQUARE_LENGTH, SQUARE_HP, SQUARE_DAMAGE, SQUARE_SPEED);
            }
        }else{
            int a1 = new Random().nextInt(1, 3);
            if(a1 == 1) {
                new TriangleModel(new Point2D.Double(frameWidth + 50
                        ,  new Random().nextInt(-20, (int)frameHeight + 20)),
                        TRIANGLE_LENGTH, TRIANGLE_HP,TRIANGLE_DAMAGE, 0 );
            }else{
                new TriangleModel(new Point2D.Double(-50
                        ,  new Random().nextInt(-20, (int)frameHeight + 20)),
                        TRIANGLE_LENGTH, TRIANGLE_HP,TRIANGLE_DAMAGE, 0 );
            }

        }
    }
    public static void gameOver(){
        allXp += xp;
        gameOverSound();
        GameFrame.getINSTANCE().setVisible(false);
        GamePanel.getINSTANCE().setVisible(false);
        GameFrame.setINSTANCE(null);
        GamePanel.setINSTANCE(null);
        GameOverPanel.getINSTANCE();
        bulletModels = new ArrayList<>();
        squareModels = new ArrayList<>();
        triangleModels = new ArrayList<>();
        collectibleModels = new ArrayList<>();
        bulletViews = new ArrayList<>();
        squareViews = new ArrayList<>();
        triangleViews = new ArrayList<>();
        collectibleViews = new ArrayList<>();
        EpsilonModel.setINSTANCE(null);
       sensitivity = 50;
       difficulty = 50;
       volume = 50;
    }
}
