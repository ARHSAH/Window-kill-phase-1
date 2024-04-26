package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.CollectibleModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.collision.Collision;
import model.movement.Direction;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.panelsView.GameFrame;
import view.panelsView.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.*;
import static controller.Variables.*;
import static java.nio.file.Files.move;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.CollectibleModel.collectibleModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.charactersModel.enemies.TriangleModel.triangleModels;
import static model.collision.Collision.*;
import static model.sounds.Sounds.*;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.enemies.SquareView.squareViews;


public class Update implements ActionListener, KeyListener, MouseMotionListener{
    Timer timer;
    EpsilonModel epsilon = EpsilonModel.getINSTANCE();
    public Update(){
        xp = 0;
        damage = BULLET_DAMAGE;
        acesoHp = 0;
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
        GameFrame.getINSTANCE().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        waveTimer ++;
        elapsedTime ++;
        bulletTimer ++;
        if(frameExtendingTimer < 5 && !frameExtendingDirection.isEmpty()){
            frameExtendingTimer ++;
            frameExtending(frameExtendingDirection);
        }else{
            frameExtendingTimer = 0;
            frameExtendingDirection = "";
        }
        if(activeGAbility) {
            if (activeAbility.equals("ares")) {
                damage += 2;
                activeGAbility = false;
            }else if(activeAbility.equals("aceso")){
                if (abilityCoolDown % 100 == 0 && hp + acesoHp <= 100) {
                    hp += acesoHp;
                }else if(abilityCoolDown % 100 == 0 && hp + acesoHp > 100){
                    hp = 100;
                }
            }else if(activeAbility.equals("proteus")){
                epsilonVertices ++;
                activeGAbility = false;
            }
        }
        abilityCoolDown ++;
        if(!firstOfGame) {
            updateModel();
            if(elapsedTime % 10 == 0){
                frameShrinkAmount ++;
                if(frameShrinkAmount == 5){
                    frameShrinkAmount = 1;
                }
                gameShrinkage();
            }
        }
        updateCollectiblesTimer();
        updateView();
    }

    public void updateView(){
        if(firstOfGame){
            startShrinkage();
        }
        updateTimer();
        setViewLocation();
        GamePanel.getINSTANCE().repaint();
        GamePanel.getINSTANCE().revalidate();
    }
    public void updateModel() {
        wave();
        if(squareModels.isEmpty() && triangleModels.isEmpty() && wave < 3 && waveTimer > 1700){
            waveSound();
            wave ++;
            waveTimer = 0;
        }
        removedSquares = new ArrayList<>();
        removedBullets = new ArrayList<>();
        removedTriangles = new ArrayList<>();
        removedCollectibles = new ArrayList<>();
        
        //EPSILON SQUARE
        for (SquareModel squareModel : squareModels) {
            Point2D point = circlePolygonCollision(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()),
                    squareModel.getVertices());
            if (distance(point,
                    new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY())) <
                    EpsilonModel.getINSTANCE().getRadius()) {
                if (verticesEpsilonCollision(point, squareModel.getVertices())) {
                    hp -= squareModel.getDamage();
                    damageSound();
                }
                if (verticesEpsilonCollision(point, EpsilonModel.getINSTANCE().getVertices())) {
                    squareModel.setHp(squareModel.getHp() - 10);
                    damageSound();
                }
                double epsilonSpeed = EpsilonModel.getINSTANCE().getSpeed();
                double squareSpeed = squareModel.getSpeed();
                Point2D effectVector = new Point2D.Double(point.getX() - EpsilonModel.getINSTANCE().getX(),
                        point.getY() - EpsilonModel.getINSTANCE().getY());
                Direction directionSquare = new Direction(addVectors(effectVector, squareModel.getDirection()));
                squareModel.setDirection(directionSquare.getDirectionVector());
                squareModel.setSpeed(3 + squareSpeed + (int) epsilonSpeed);
                squareModel.setImpact(true);
                Direction directionEpsilon = new Direction(addVectors(EpsilonModel.getINSTANCE().
                        getDirection(), reverseVector(effectVector)));
                EpsilonModel.getINSTANCE().setDirection(directionEpsilon.getDirectionVector());
                EpsilonModel.getINSTANCE().setSpeed(3 + squareSpeed + (int) epsilonSpeed);
                EpsilonModel.getINSTANCE().setImpact(true);
                impact(point);
            }
        }

        //EPSILON TRIANGLE
        for (TriangleModel triangleModel : triangleModels) {
            Point2D point = circlePolygonCollision(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()),
                    triangleModel.getVertices());
            if (distance(point,
                    new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY())) <
                    EpsilonModel.getINSTANCE().getRadius()) {
                if (verticesEpsilonCollision(point, triangleModel.getVertices())) {
                    hp -= triangleModel.getDamage();
                    damageSound();
                }
                if (verticesEpsilonCollision(point, EpsilonModel.getINSTANCE().getVertices())) {
                    triangleModel.setHp(triangleModel.getHp() - 10);
                    damageSound();
                }
                double epsilonSpeed = EpsilonModel.getINSTANCE().getSpeed();
                double triangleSpeed = triangleModel.getSpeed();
                Point2D effectVector = new Point2D.Double(point.getX() - EpsilonModel.getINSTANCE().getX(),
                        point.getY() - EpsilonModel.getINSTANCE().getY());
                Direction directionSquare = new Direction(addVectors(effectVector, triangleModel.getDirection()));
                triangleModel.setDirection(directionSquare.getDirectionVector());
                triangleModel.setSpeed(3 + triangleSpeed + (int) epsilonSpeed);
                triangleModel.setImpact(true);
                Direction directionEpsilon = new Direction(addVectors(EpsilonModel.getINSTANCE().
                        getDirection(), reverseVector(effectVector)));
                EpsilonModel.getINSTANCE().setDirection(directionEpsilon.getDirectionVector());
                EpsilonModel.getINSTANCE().setSpeed(3 + triangleSpeed + (int) epsilonSpeed);
                EpsilonModel.getINSTANCE().setImpact(true);
                impact(point);
            }
        }

        //EPSILON COLLECTIBLE
        for (CollectibleModel value : collectibleModels){
            if(value.getTimer() > 600){
                removedCollectibles.add(value);
            }
            else if(value.getCenter().distance(EpsilonModel.getINSTANCE().getCenter()) <
            EpsilonModel.getINSTANCE().getRadius() + value.getRadius()){
                xp += value.getXp();
                removedCollectibles.add(value);
            }
        }

        //SQUARE BULLET
        for (SquareModel squareModel : squareModels) {
            for (BulletModel bulletModel : bulletModels) {
                if (distance(circlePolygonCollision(new Point2D.Double(bulletModel.getX(), bulletModel.getY()),
                                squareModel.getVertices()),
                        new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                        bulletModel.getRadius()) {
                    Point2D effectVector = reverseVector(squareModel.getDirection());
                    squareModel.setImpact(true);
                    squareModel.setDirection(effectVector);
                    squareModel.setSpeed(bulletModel.getSpeed() / 5);
                    squareModel.setHp(squareModel.getHp() - bulletModel.getDamage());
                    damageSound();
                    removedBullets.add(bulletModel);
                }
            }
        }

        //TRIANGLE BULLET
        for (TriangleModel triangleModel : triangleModels) {
            for (BulletModel bulletModel : bulletModels) {
                if (distance(circlePolygonCollision(new Point2D.Double(bulletModel.getX(), bulletModel.getY()),
                                triangleModel.getVertices()),
                        new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                        bulletModel.getRadius()) {
                    Point2D effectVector = reverseVector(triangleModel.getDirection());
                    triangleModel.setImpact(true);
                    triangleModel.setDirection(effectVector);
                    triangleModel.setSpeed(bulletModel.getSpeed() / 5);
                    triangleModel.setHp(triangleModel.getHp() - bulletModel.getDamage());
                    damageSound();
                    removedBullets.add(bulletModel);
                }
            }
        }

        //TRIANGLE SQUARE
        for (TriangleModel triangleModel : triangleModels) {
            for (SquareModel squareModel : squareModels) {
                if (polygonsCollision(triangleModel.getVertices(),
                        squareModel.getVertices()) != null) {
                    int speedT = (int) triangleModel.getSpeed();
                    int speedS = (int)squareModel.getSpeed();
                    Point2D point = polygonsCollision(triangleModel.getVertices(), squareModel.getVertices());
                    Point2D effectVector = new Point2D.Double(point.getX() -
                            squareModel.getCenter().getX(),
                            point.getY() - squareModel.getCenter().getY());
                    Direction directionTriangle = new Direction(addVectors(effectVector, triangleModel.getDirection()));
                    triangleModel.setDirection(directionTriangle.getDirectionVector());
                    triangleModel.setSpeed(1 + speedS);
                    triangleModel.setImpact(true);
                    Direction directionSquare = new Direction(addVectors(reverseVector(effectVector), squareModel.getDirection()));
                    squareModel.setDirection(directionSquare.getDirectionVector());
                    squareModel.setSpeed(1 + speedT);
                    squareModel.setImpact(true);
                    impact(point);
                }
            }
        }

        //SQUARE SQUARE
        for(int i = 0 ; i < squareModels.size() ; i++){
            assert i + 1 != (squareModels.size() - 1);
            for(int j = i + 1 ; j < squareModels.size() ; j++){
                if(polygonsCollision(squareModels.get(i).getVertices(), squareModels.get(j).getVertices()) != null){
                    double speedI = squareModels.get(i).getSpeed();
                    double speedJ = squareModels.get(j).getSpeed();
                    Point2D point = polygonsCollision(squareModels.get(i).getVertices(), squareModels.get(j).getVertices());
                    Point2D effectVector = new Point2D.Double(point.getX() - squareModels.get(i).getCenter().getX(),
                            point.getY() - squareModels.get(i).getCenter().getY());
                    Direction directionSquare1 = new Direction(addVectors(effectVector,
                            squareModels.get(j).getDirection()));
                    squareModels.get(j).setDirection(directionSquare1.getDirectionVector());
                    squareModels.get(j).setSpeed(speedI + 1);
                    squareModels.get(j).setImpact(true);
                    Direction directionSquare = new Direction(addVectors(reverseVector(effectVector), squareModels.get(j).getDirection()));
                    squareModels.get(i).setDirection(directionSquare.getDirectionVector());
                    squareModels.get(i).setSpeed(speedJ + 1);
                    squareModels.get(i).setImpact(true);
                    impact(point);
                }
            }
        }

        //TRIANGLE TRIANGLE
        for(int i = 0 ; i < triangleModels.size() ; i++){
            assert i + 1 != (triangleModels.size() - 1);
            for(int j = i + 1 ; j < triangleModels.size() ; j++){
                if(polygonsCollision(triangleModels.get(i).getVertices(), triangleModels.get(j).getVertices()) != null){
                    double speedI = triangleModels.get(i).getSpeed();
                    double speedJ = triangleModels.get(j).getSpeed();
                    Point2D point = polygonsCollision(triangleModels.get(i).getVertices(), triangleModels.get(j).getVertices());
                    Point2D effectVector = new Point2D.Double(point.getX() - triangleModels.get(i).getCenter().getX(),
                            point.getY() - triangleModels.get(i).getCenter().getY());
                    Direction directionSquare1 = new Direction(addVectors(effectVector, triangleModels.get(j).getDirection()));
                    triangleModels.get(j).setDirection(directionSquare1.getDirectionVector());
                    triangleModels.get(j).setSpeed(speedI + 1);
                    triangleModels.get(j).setImpact(true);
                    Direction directionSquare = new Direction(addVectors(reverseVector(effectVector), triangleModels.get(j).getDirection()));
                    triangleModels.get(i).setDirection(directionSquare.getDirectionVector());
                    triangleModels.get(i).setSpeed(speedJ + 1);
                    triangleModels.get(i).setImpact(true);
                    impact(point);
                }
            }
        }

        //BULLET FRAME
        checkBulletFrame();

        //EPSILON MOVEMENT
        if(!EpsilonModel.getINSTANCE().isImpact()){
            Direction direction = new Direction(new Point2D.Double(eRight + eLeft, eUp + eDown));
            EpsilonModel.getINSTANCE().setDirection(direction.getDirectionVector());
        }
        else{
            if(EpsilonModel.getINSTANCE().getSpeed() > 0) {
                EpsilonModel.getINSTANCE().setSpeed(EpsilonModel.getINSTANCE().getSpeed() - 0.5);
            }else{
                EpsilonModel.getINSTANCE().setImpact(false);
                Direction direction = new Direction(new Point2D.Double(eRight + eLeft, eUp + eDown));
                EpsilonModel.getINSTANCE().setDirection(direction.getDirectionVector());
            }
        }
        EpsilonModel.getINSTANCE().move();


        //BULLET MOVEMENT
        for (BulletModel value : bulletModels) {
            value.move();
        }

        //SQUARE MOVEMENT
        for(SquareModel value : squareModels){
            if(value.getHp() > 0) {
                if(!value.isImpact()) {
                    Direction direction = new Direction(new
                            Point2D.Double(EpsilonModel.getINSTANCE().getX() - value.getX(),
                            EpsilonModel.getINSTANCE().getY() - value.getY()));
                    value.setDirection(direction.getDirectionVector());
                    if(value.getSpeed() < 0.5 ){
                        value.setSpeed(value.getSpeed() + 0.1);
                    }else if(value.getSpeed() > 0.5){
                        value.setSpeed(value.getSpeed() - 0.5);
                    }
                }else if(value.getSpeed() > 0){
                    value.setSpeed(value.getSpeed() - 0.5);
                }else{
                    value.setImpact(false);
                }
                value.move();
            }else{
                removedSquares.add(value);
            }
        }

        //TRIANGLE MOVEMENT
        for(TriangleModel value : triangleModels){
            if(value.getHp() > 0) {
                if(!value.isImpact()) {
                    Direction direction = new Direction(new
                            Point2D.Double(EpsilonModel.getINSTANCE().getX() - value.getA().getX(),
                            EpsilonModel.getINSTANCE().getY() - value.getA().getY()));
                    value.setDirection(direction.getDirectionVector());
                    if(value.getSpeed() < 0.5 ){
                        value.setSpeed(value.getSpeed() + 0.1);
                    }else if(value.getCenter().distance(new Point2D.Double(EpsilonModel.getINSTANCE().getX(),
                            EpsilonModel.getINSTANCE().getY())) > 130){
                        value.setSpeed(5);
                    }else{
                        value.setSpeed(0.5);
                    }

                }else if(value.getSpeed() > 0){
                    value.setSpeed(value.getSpeed() - 0.5);
                }else{
                    value.setImpact(false);
                }
                value.move();
            }else{
                removedTriangles.add(value);
            }
        }

        //SQUARE REMOVE
        for(SquareModel value : removedSquares){
            new CollectibleModel(value.getCenter(), COLLECTIBLE_RADIUS);
            squareModels.remove(value);
            collapseSound();
        }

        //TRIANGLE REMOVE
        for(TriangleModel value : removedTriangles){
            new CollectibleModel(value.getCenter(), COLLECTIBLE_RADIUS);
            new CollectibleModel(value.getA(), COLLECTIBLE_RADIUS);
            triangleModels.remove(value);
            collapseSound();
        }

        //BULLET REMOVE
        for(BulletModel value : removedBullets){
            value.clip.stop();
            bulletModels.remove(value);
        }

        //COLLECTIBLE REMOVE
        for(CollectibleModel collectibleModel : removedCollectibles){
            collectibleModels.remove(collectibleModel);
        }




    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            eUp = -1;
        }
        if(e.getKeyChar() == 's'){
            eDown = 1;
        }
        if(e.getKeyChar() == 'a'){
            eRight = -1;
        }
        if(e.getKeyChar() == 'd'){
           eLeft = 1;

        }
        if(e.getKeyChar() == 'x'){
            if(bulletTimer > 7) {
                bulletTimer = 0;
                Direction bulletDirection = new Direction(new Point2D.Double(
                        (mouseLocation.getX() - EpsilonModel.getINSTANCE().getX()),
                        mouseLocation.getY() - EpsilonModel.getINSTANCE().getY()));
                new BulletModel(EpsilonModel.getINSTANCE().getX(),
                        EpsilonModel.getINSTANCE().getY(), 4, damage, BULLET_SPEED,
                        bulletDirection.getDirectionVector());

            }
        }

        if(!activeAbility.isEmpty() && e.getKeyChar() == 'k' && abilityCoolDown >= 30000 && xp >= 100){
            xp -= 100;
            activeGAbility = true;
            abilityCoolDown = 0;
            acesoHp ++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            eUp = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 's'){
            eDown = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 'a'){
            eRight = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 'd'){
            eLeft = 0;
            epsilon.setSpeed(0.5);
        }

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseLocation = new Point2D.Double(e.getX(), e.getY());
    }



}
