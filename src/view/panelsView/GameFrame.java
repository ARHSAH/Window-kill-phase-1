package view.panelsView;

import controller.Variables;
import view.charactersView.EpsilonView;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.SHRINK_AMOUNT;

public class GameFrame extends JFrame {
    private static GameFrame INSTANCE;
    public JFrame gameFrame;
    public JPanel gamePanel;
    Timer timer;
    public static GameFrame getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new GameFrame();
        }
        return INSTANCE;
    }
    private GameFrame(){
        this.setSize(Variables.frameWidth, Variables.frameHeight);
        this.setLocation(300, 70);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        try {
            Thread.sleep(500);
        }catch(Exception e){

        }
//        timer = new Timer(50, e -> {
//            if(Variables.frameWidth > 350 ) {
//                Variables.frameWidth -= (2 * SHRINK_AMOUNT);
//                Variables.frameHeight -= (2 * SHRINK_AMOUNT);
//                updateSize();
//            }else{
//                timer.stop();
//            }
//        });
//        timer.start();
    }
    public void updateSize(){
        this.setLocation(new Point(gameFrame.getX() + SHRINK_AMOUNT,
                gameFrame.getY() + SHRINK_AMOUNT));
        this.setSize(Variables.frameWidth, Variables.frameHeight);
        this.repaint();
        this.revalidate();
    }



}
