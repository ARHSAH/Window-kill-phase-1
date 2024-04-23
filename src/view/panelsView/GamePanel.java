package view.panelsView;

import controller.Variables;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.charactersView.enemies.SquareView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import static controller.Variables.*;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.enemies.SquareView.squareViews;

public class GamePanel extends JPanel {
    private static GamePanel INSTANCE;
    JLabel hpLabel;
    ImageIcon heartIcon = new ImageIcon("heartimage.png");

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

        heartIcon = new ImageIcon("heartimage.png");
        hpLabel = new JLabel(hp + "");
        hpLabel.setIcon(heartIcon);
        hpLabel.setSize(70, 28);
        hpLabel.setFont(new Font(null, Font.PLAIN,18));
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setLocation(0, 0);
        this.add(hpLabel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        hpLabel.setIcon(heartIcon);
        hpLabel.setText(hp + "");
        hpLabel.setLocation(0, 0);
        g2.setColor(Color.WHITE);
        EpsilonView.getINSTANCE().draw(g2);

        for (BulletView value : bulletViews) {
            value.draw(g2);
        }
        g2.setColor(Color.green);
        for (SquareView value : squareViews) {
            value.draw(g2);
            this.repaint();
            this.revalidate();
        }
    }
}
