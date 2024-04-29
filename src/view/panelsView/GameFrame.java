package view.panelsView;

import controller.Variables;
import view.charactersView.EpsilonView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import static controller.Constants.MINIMUM_FRAME_SIZE;
import static controller.Variables.*;

public class GameFrame extends JFrame {
    private static GameFrame INSTANCE;

    public static GameFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameFrame();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GameFrame INSTANCE) {
        GameFrame.INSTANCE = INSTANCE;
    }


    private GameFrame() {
        this.setSize((int)Variables.frameWidth, (int)Variables.frameHeight);
        this.setLocation(300, 70);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

}