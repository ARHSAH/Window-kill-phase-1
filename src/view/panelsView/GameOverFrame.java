package view.panelsView;

import javax.swing.*;

public class GameOverFrame extends JFrame {
    private static  GameOverFrame INSTANCE;

    public static GameOverFrame getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new GameOverFrame();
        }
        return INSTANCE;
    }
    public GameOverFrame(){

    }
}
