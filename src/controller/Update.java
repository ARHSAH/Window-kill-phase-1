package controller;

import model.charactersModel.EpsilonModel;
import model.movement.Direction;
import view.charactersView.EpsilonView;
import view.panelsView.GameFrame;
import view.panelsView.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import static controller.Controller.setEpsilonViewLocation;
import static controller.Utils.multiplyVector;
import static java.nio.file.Files.move;



public class Update implements ActionListener, KeyListener {
    Timer timer;
    EpsilonModel epsilon = EpsilonModel.getINSTANCE();
    public Update(){
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateView();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            Direction direction = new Direction(new Point2D.Double(0,-1));
            EpsilonModel.getINSTANCE().move(direction);
        }else if(e.getKeyChar() == 's'){
            Direction direction = new Direction(new Point2D.Double(0,1));
            EpsilonModel.getINSTANCE().move(direction);
        }else if(e.getKeyChar() == 'a'){
            Direction direction = new Direction(new Point2D.Double(-1,0));
            EpsilonModel.getINSTANCE().move(direction);
        }else if(e.getKeyChar() == 'd'){
            Direction direction = new Direction(new Point2D.Double(1,0));
            EpsilonModel.getINSTANCE().move(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        epsilon.setSpeed(0);
    }
    public void updateView(){
        setEpsilonViewLocation();
        GamePanel.getINSTANCE().repaint();
        GamePanel.getINSTANCE().revalidate();
    }
    public void updateModel() {
    }
}
