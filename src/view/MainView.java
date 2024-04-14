package view;

import controller.Constants;

import javax.swing.*;

import static controller.Constants.MENU_FRAME_SIZE;

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

