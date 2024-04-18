package view.panelsView;

import controller.Variables;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import static controller.Variables.*;
import static view.charactersView.BulletView.bulletViews;

public class GamePanel extends JPanel {
    private static GamePanel INSTANCE;

    public static GamePanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GamePanel();
        }
        return INSTANCE;
    }

    private GamePanel() {
        this.setSize(frameWidth, frameHeight);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        GameFrame.getINSTANCE().getContentPane().add(this);
        GameFrame.getINSTANCE().setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        EpsilonView.getINSTANCE().draw(g2);
        for (BulletView value : bulletViews) {
            value.draw(g2);
        }
    }
}
