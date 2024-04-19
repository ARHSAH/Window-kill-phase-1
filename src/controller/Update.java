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
import static controller.Utils.multiplyVector;
import static controller.Variables.*;
import static java.nio.file.Files.move;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.collision.Collision.checkBulletFrame;
import static model.collision.Collision.checkCircleSquare;
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
        for(SquareModel squareModel : squareModels){
            for(BulletModel bulletModel : bulletModels){
                if(checkCircleSquare(new Point2D.Double(bulletModel.getX(), bulletModel.getY() ), bulletModel.getRadius(),
                        new Point2D.Double(squareModel.getX(), squareModel.getY()), squareModel.getLength())){
                    squareModel.setHp(squareModel.getHp() - 5);
                    removedBullets.add(bulletModel);
                }
            }
        }
        for(SquareModel value : squareModels){
            if(value.getHp() > 0) {
                Direction direction = new Direction(new Point2D.Double(EpsilonModel.getINSTANCE().getX() - value.getX(),
                        EpsilonModel.getINSTANCE().getY() - value.getY()));
                value.setDirection(direction);
                value.move(direction, SQUARE_SPEED);
            }else{
                removedSquares.add(value);
            }
        }
        for(SquareModel value : removedSquares){
            squareModels.remove(value);
        }
        for(BulletModel value : removedBullets){
            value.clip.stop();
            bulletModels.remove(value);
        }

        for (BulletModel value : bulletModels) {
            value.move(bulletDirection, Variables.bulletSpeed);
        }

        checkBulletFrame();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            Direction direction = new Direction(new Point2D.Double(0,-1));
            EpsilonModel.getINSTANCE().move(direction);
        }
        if(e.getKeyChar() == 's'){
            Direction direction = new Direction(new Point2D.Double(0,1));
            EpsilonModel.getINSTANCE().move(direction);
        }
        if(e.getKeyChar() == 'a'){
            Direction direction = new Direction(new Point2D.Double(-1,0));
            EpsilonModel.getINSTANCE().move(direction);
        }
        if(e.getKeyChar() == 'd'){
            Direction direction = new Direction(new Point2D.Double(1,0));
            EpsilonModel.getINSTANCE().move(direction);
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
        epsilon.setSpeed(0.5);
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseLocation = new Point2D.Double(e.getX(), e.getY());
    }



}
