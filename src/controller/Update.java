package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
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
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.collision.Collision.*;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.enemies.SquareView.squareViews;


public class Update implements ActionListener, KeyListener, MouseMotionListener{
    Direction bulletDirection;
    Timer timer;
    EpsilonModel epsilon = EpsilonModel.getINSTANCE();
    public Update(){
        xp = 0;
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
        GameFrame.getINSTANCE().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                if (abilityCoolDown > 1000) {
                    damage = BULLET_DAMAGE;
                    activeGAbility = false;
                }else{
                    damage = BULLET_DAMAGE + 2;
                }
            }
        }
        abilityCoolDown ++;

        updateModel();
        updateView();
    }

    public void updateView(){
        if(firstOfGame){
            startShrinkage();
        }
        setViewLocation();
        GamePanel.getINSTANCE().repaint();
        GamePanel.getINSTANCE().revalidate();
    }
    public void updateModel(){
        removedSquares = new ArrayList<>();
        removedBullets = new ArrayList<>();

        //EPSILON SQUARE
        for(SquareModel squareModel : squareModels){
            Point2D point = collisionCircleSquare(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()),
                    EpsilonModel.getINSTANCE().getRadius(), squareModel);
            if(distance(point,
                    new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY())) <
            EpsilonModel.getINSTANCE().getRadius()){
                Point2D effectVector = new Point2D.Double(point.getX() - EpsilonModel.getINSTANCE().getX(),
                        point.getY() - EpsilonModel.getINSTANCE().getY());
                Direction directionSquare = new Direction(addVectors(effectVector, squareModel.getDirection()));
                squareModel.setDirection(directionSquare.getDirectionVector());
                squareModel.setSpeed(6 + (int)EpsilonModel.getINSTANCE().getSpeed());
                squareModel.setImpact(true);
                Direction directionEpsilon = new Direction(addVectors(EpsilonModel.getINSTANCE().
                        getDirection(), reverseVector(effectVector)));
                EpsilonModel.getINSTANCE().setDirection(directionEpsilon.getDirectionVector());
                EpsilonModel.getINSTANCE().setSpeed(EpsilonModel.getINSTANCE().getSpeed() + 0.5);
                EpsilonModel.getINSTANCE().setImpact(true);
            }
        }

        //SQUARE BULLET
        for(SquareModel squareModel : squareModels){
            for(BulletModel bulletModel : bulletModels){
                if(distance(collisionCircleSquare(new Point2D.Double(bulletModel.getX(), bulletModel.getY()),
                                bulletModel.getRadius(), squareModel),
                        new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                        bulletModel.getRadius()){
                    squareModel.setHp(squareModel.getHp() - 5);
                    removedBullets.add(bulletModel);
                }
            }
        }

        //BULLET FRAME
        checkBulletFrame();

        //EPSILON MOVEMENT
        if(!EpsilonModel.getINSTANCE().isImpact()){
            Direction direction = new Direction(new Point2D.Double(eUp + eDown, eRight + eLeft));
            EpsilonModel.getINSTANCE().setDirection(direction.getDirectionVector());
        }

        //BULLET MOVEMENT
        for (BulletModel value : bulletModels) {
            value.move(bulletDirection, Variables.bulletSpeed);
        }

        //SQUARE MOVEMENT
        for(SquareModel value : squareModels){
            if(value.getHp() > 0) {
                if(!value.isImpact()) {
                    Direction direction = new Direction(new
                            Point2D.Double(EpsilonModel.getINSTANCE().getX() - value.getX(),
                            EpsilonModel.getINSTANCE().getY() - value.getY()));
                    value.setDirection(direction.getDirectionVector());
                    if(value.getSpeed() != 0.5){
                        value.setSpeed(value.getSpeed() + 0.1);
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

        //SQUARE REMOVE
        for(SquareModel value : removedSquares){
            squareModels.remove(value);
        }

        //BULLET REMOVE
        for(BulletModel value : removedBullets){
            value.clip.stop();
            bulletModels.remove(value);
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
            if(bulletTimer > 15) {
                bulletTimer = 0;
                new BulletModel(EpsilonModel.getINSTANCE().getX(),
                        EpsilonModel.getINSTANCE().getY(), 4, damage);
                bulletDirection = new Direction(new Point2D.Double(
                         (mouseLocation.getX() - EpsilonModel.getINSTANCE().getX()),
                        mouseLocation.getY() - EpsilonModel.getINSTANCE().getY()));
            }
        }

        if(!activeAbility.isEmpty() && e.getKeyChar() == 'k' && abilityCoolDown >= 30000 && xp > 100){
            xp -= 100;
            activeGAbility = true;
            abilityCoolDown = 0;
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
