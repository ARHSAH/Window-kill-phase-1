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

import static controller.Constants.SHRINK_AMOUNT;
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
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
        GameFrame.getINSTANCE().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bulletTimer ++;
        updateModel();
        updateView();
        if(firstOfGame){
            startShrinkage();
        }

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
                        EpsilonModel.getINSTANCE().getY(), 4);
                bulletDirection = new Direction(new Point2D.Double(
                         (mouseLocation.getX() - EpsilonModel.getINSTANCE().getX()),
                        mouseLocation.getY() - EpsilonModel.getINSTANCE().getY()));
            }
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
