package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import model.movement.Direction;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.panelsView.GameFrame;
import view.panelsView.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import static controller.Constants.*;
import static controller.Controller.setViewLocation;
import static controller.Controller.startShrinkage;
import static controller.Utils.multiplyVector;
import static controller.Variables.*;
import static java.nio.file.Files.move;
import static model.charactersModel.BulletModel.bulletModels;
import static model.collision.Collision.checkBulletFrame;


public class Update implements ActionListener, KeyListener, MouseMotionListener{
    Direction bulletDirection;
    Timer timer;
    EpsilonModel epsilon = EpsilonModel.getINSTANCE();
    public Update(){
        //xp = 0;
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
        GameFrame.getINSTANCE().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(damage);
        bulletTimer ++;
        updateModel();
        updateView();
        if(firstOfGame){
            startShrinkage();
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
    }

    public void updateView(){



        setViewLocation();
        GamePanel.getINSTANCE().repaint();
        GamePanel.getINSTANCE().revalidate();
    }
    public void updateModel(){
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
            System.out.println("salam");
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
