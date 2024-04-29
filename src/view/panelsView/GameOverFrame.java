package view.panelsView;

import controller.Variables;

import javax.swing.*;

public class GameOverFrame extends JFrame {
    private static GameOverFrame INSTANCE;


    public static GameOverFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameOverFrame();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GameOverFrame INSTANCE) {
        GameOverFrame.INSTANCE = INSTANCE;
    }

    private GameOverFrame() {
        this.setSize(626, 438);
        this.setLocation(400, 80);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

}