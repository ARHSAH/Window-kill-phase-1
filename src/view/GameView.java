package view;

import controller.Variables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.Constants.SHRINK_AMOUNT;

public class GameView extends JFrame {
    private static GameView INSTANCE;
    JFrame gameFrame;
    JPanel gamePanel;
    Timer timer;
    public static GameView getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new GameView();
        }
        return INSTANCE;
    }
    public GameView(){
        gameFrame = new JFrame();
        gameFrame.setSize(Variables.frameWidth, Variables.frameHeight);
        gameFrame.setLocation(300, 80);
        gameFrame.setLayout(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gamePanel = (JPanel) gameFrame.getContentPane();
        gamePanel.setBackground(Color.BLACK);
        try {
            Thread.sleep(500);
        }catch(Exception e){

        }
        timer = new Timer(50, e -> {
            if(Variables.frameWidth > 350 ) {
                Variables.frameWidth -= (2 * SHRINK_AMOUNT);
                Variables.frameHeight -= (2 * SHRINK_AMOUNT);
                updateSize();
            }else{
                timer.stop();
            }
        });
        timer.start();
        //timer.setCoalesce(true);
    }
    public void updateSize(){
        gameFrame.setLocation(new Point(gameFrame.getX() + SHRINK_AMOUNT,
                gameFrame.getY() + SHRINK_AMOUNT));
        gameFrame.setSize(Variables.frameWidth, Variables.frameHeight);
        gameFrame.repaint();
        gameFrame.revalidate();
    }

}
