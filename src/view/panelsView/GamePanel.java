package view.panelsView;

import controller.Variables;
import view.charactersView.BulletView;
import view.charactersView.CollectibleView;
import view.charactersView.EpsilonView;
import view.charactersView.enemies.SquareView;
import view.charactersView.enemies.TriangleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import static controller.Variables.*;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.CollectibleView.collectibleViews;
import static view.charactersView.enemies.SquareView.squareViews;
import static view.charactersView.enemies.TriangleView.triangleViews;

public class GamePanel extends JPanel {
    private static GamePanel INSTANCE;
    JLabel hpLabel, xpLabel, waveLabel, TimerLabel;
    ImageIcon heartIcon = new ImageIcon("heartimage.png");

    public static GamePanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GamePanel();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GamePanel INSTANCE) {
        GamePanel.INSTANCE = INSTANCE;
    }

    private GamePanel() {
        this.setSize((int)frameWidth, (int)frameHeight);
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
        hpLabel.setFont(new Font(null, Font.PLAIN,20));
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setLocation(0, 0);
        this.add(hpLabel);
        xpLabel = new JLabel("+" + xp);
        xpLabel.setSize(70, 28);
        xpLabel.setFont(new Font(null, Font.PLAIN,20));
        xpLabel.setForeground(Color.WHITE);
        xpLabel.setLocation((int)(frameWidth - 60), 0);
        this.add(xpLabel);
        waveLabel = new JLabel(wave + "/" + "3");
        waveLabel.setSize(70, 28);
        waveLabel.setFont(new Font(null, Font.PLAIN,20));
        waveLabel.setForeground(Color.WHITE);
        waveLabel.setLocation((int)(frameWidth - 54), (int)(frameHeight - 20));
        this.add(waveLabel);
        TimerLabel = new JLabel(minutes1 + "" + minutes2
                + ":" + seconds1 + "" + seconds2);
        TimerLabel.setSize(new Dimension(70, 28));
        TimerLabel.setFont(new Font(null, Font.PLAIN, 20));
        TimerLabel.setLocation(new java.awt.Point(0, (int)(frameWidth - 63)));
        TimerLabel.setForeground(Color.WHITE);
        this.add(TimerLabel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        hpLabel.setIcon(heartIcon);
        hpLabel.setText(hp + "");
        hpLabel.setLocation(0, 0);
        xpLabel.setText("+" + xp);
        xpLabel.setLocation((int)(frameWidth - 70), 0);
        waveLabel.setText(wave + "/" + "3");
        waveLabel.setLocation((int)(frameWidth - 50) , (int)(frameHeight - 65));
        TimerLabel.setText(minutes1 + "" + minutes2
                + ":" + seconds1 + "" + seconds2);
        TimerLabel.setLocation(new java.awt.Point(5, (int)(frameHeight - 63)));

        g2.setColor(Color.WHITE);
        EpsilonView.getINSTANCE().draw(g2);
        for (BulletView value : bulletViews) {
            value.draw(g2);
        }
        g2.setColor(Color.green);
        for (SquareView value : squareViews) {
            value.draw(g2);
        }
        g2.setColor(Color.YELLOW);
        for(TriangleView value : triangleViews){
            value.draw(g2);
        }
        g2.setColor(Color.green);
        for(CollectibleView value : collectibleViews){
            value.draw(g2);
        }
        this.repaint();
        this.revalidate();
    }
}
