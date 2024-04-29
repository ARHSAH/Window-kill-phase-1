package view.panelsView;

import javax.swing.*;
import java.awt.*;

import static controller.Variables.*;

public class GameOverPanel extends JPanel {
    private static GameOverPanel INSTANCE;

    JButton exit;
    JLabel xpLabel;

    public static GameOverPanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new GameOverPanel();
        }
        return INSTANCE;
    }
    public GameOverPanel(){
        this.setSize(626,438);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setVisible(true);
        GameOverFrame.getINSTANCE().getContentPane().add(this);
        GameOverFrame.getINSTANCE().setVisible(true);

        xpLabel= new JLabel("XP : " + xp);
        xpLabel.setSize(220, 50);
        xpLabel.setFont(new Font(null, Font.PLAIN,30));
        xpLabel.setForeground(Color.decode("#f542b9"));
        xpLabel.setLocation(250, 10);
        this.add(xpLabel);

        exit = new JButton("MAIN MENU");
        exit.setSize(200, 40);
        exit.setFont(new Font(null, Font.PLAIN,25));
        exit.setContentAreaFilled(false);
        exit.setFocusable(false);
        exit.setForeground(Color.decode("#4287f5"));
        exit.setLocation(4, 360);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            this.setVisible(false);
            GameOverFrame.getINSTANCE().setVisible(false);
            GameOverFrame.setINSTANCE(null);
            INSTANCE = null;
            MainView.mainFrame.setState(JFrame.NORMAL);
        });
        this.add(exit);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("gameOverBackground.jpg");
        Image img = imageIcon.getImage();
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(img, 0 , 0, this);
    }

}
