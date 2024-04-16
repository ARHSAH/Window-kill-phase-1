package view.panelsView;

import javax.swing.*;

public class MainView{
    public static JFrame mainFrame;
    public static JPanel mainPanel;

    public MainView() {
        mainFrame = new JFrame();
        mainFrame.setSize(0, 0);
        mainFrame.setLocation(300, 80);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = (JPanel) mainFrame.getContentPane();
        MenuPanel.getINSTANCE();
    }
}

