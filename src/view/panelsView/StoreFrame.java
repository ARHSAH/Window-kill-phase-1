package view.panelsView;

import javax.swing.*;

public class StoreFrame extends JFrame {
    private static StoreFrame INSTANCE;


    public static StoreFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new StoreFrame();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(StoreFrame INSTANCE) {StoreFrame.INSTANCE = INSTANCE;
    }

    private StoreFrame() {
        this.setSize(587, 336);
        this.setLocation(400, 80);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }
}
