package view.panelsView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static controller.Constants.*;

public class TutorialPanel extends JPanel {
    JLabel helloTutorialLabel;
    private static TutorialPanel INSTANCE;

    public static TutorialPanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new TutorialPanel();
        }
        return INSTANCE;
    }

    private TutorialPanel(){
        MainView.mainPanel.setSize(TUTORIAL_FRAME_WIDTH, TUTORIAL_FRAME_HEIGHT);
        MainView.mainFrame.setSize(TUTORIAL_FRAME_WIDTH, TUTORIAL_FRAME_HEIGHT);
        this.setSize(TUTORIAL_FRAME_WIDTH, TUTORIAL_FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(null);

        helloTutorialLabel = new JLabel("TUTORIAL");
        helloTutorialLabel.setSize(200, 50);
        helloTutorialLabel.setFont(new Font(null, Font.PLAIN,35));
        helloTutorialLabel.setForeground(Color.BLACK);
        helloTutorialLabel.setLocation(250, 0);
        this.add(helloTutorialLabel);

        JLabel tutorial = new JLabel();
        tutorial.setText("Windowkill is a twin-stick shooter, but the game window ");
        tutorial.setFont(new Font(null, Font.PLAIN,25));
        tutorial.setForeground(Color.BLACK);
        tutorial.setLocation(30, 50);
        tutorial.setSize(1000,50);
        this.add(tutorial);


        JLabel tutorial1 = new JLabel();
        tutorial1.setText(
                "itself is constantly closing in on you. " +
                "Shoot the window edges");
        tutorial1.setFont(new Font(null, Font.PLAIN,25));
        tutorial1.setForeground(Color.BLACK);
        tutorial1.setLocation(15, 100);
        tutorial1.setSize(1000,50);
        this.add(tutorial1);

        JLabel tutorial2 = new JLabel();
        tutorial2.setText(
                "to push it around your screen as you fight and dodge enemies");
        tutorial2.setFont(new Font(null, Font.PLAIN,24));
        tutorial2.setForeground(Color.BLACK);
        tutorial2.setLocation(10, 150);
        tutorial2.setSize(1000,50);
        this.add(tutorial2);

        JLabel tutorial3 = new JLabel();
        tutorial3.setText(
                " and bosses in their own windows.");
        tutorial3.setFont(new Font(null, Font.PLAIN,25));
        tutorial3.setForeground(Color.BLACK);
        tutorial3.setLocation(10, 200);
        tutorial3.setSize(1000,50);
        this.add(tutorial3);

        JLabel movement = new JLabel();
        movement.setText(
                " Movement : W,A,S,D");
        movement.setFont(new Font(null, Font.PLAIN,25));
        movement.setForeground(Color.BLACK);
        movement.setLocation(10, 250);
        movement.setSize(1000,50);
        this.add(movement);

        JLabel shooting = new JLabel();
        shooting.setText(
                " Shooting : X");
        shooting.setFont(new Font(null, Font.PLAIN,25));
        shooting.setForeground(Color.BLACK);
        shooting.setLocation(10, 300);
        shooting.setSize(1000,50);
        this.add(shooting);

        JLabel ability = new JLabel();
        ability.setText(
                " Ability : K");
        ability.setFont(new Font(null, Font.PLAIN,25));
        ability.setForeground(Color.BLACK);
        ability.setLocation(10, 350);
        ability.setSize(1000,50);
        this.add(ability);

        JLabel store = new JLabel();
        store.setText(
                " Store : P");
        store.setFont(new Font(null, Font.PLAIN,25));
        store.setForeground(Color.BLACK);
        store.setLocation(10, 400);
        store.setSize(1000,50);
        this.add(store);

        JButton exit = new JButton("MAIN MENU");
        exit.setSize(200, 40);
        exit.setFont(new Font(null, Font.PLAIN,25));
        exit.setContentAreaFilled(false);
        exit.setFocusable(false);
        exit.setForeground(Color.BLACK);
        exit.setLocation(270, 473);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            this.setVisible(false);
            INSTANCE = null;
            MenuPanel.getINSTANCE();
        });
        this.add(exit);


        this.repaint();
        this.revalidate();
        MainView.mainPanel.add(this);
        MainView.mainPanel.repaint();
        MainView.mainPanel.revalidate();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("tutorialbackground.jpg");
        Image img = imageIcon.getImage();
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(img, 0 , 0, this);
        g2D.setColor(Color.BLACK);
    }

}
