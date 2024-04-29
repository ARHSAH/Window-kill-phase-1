package view.panelsView;

import controller.Constants;
import controller.Controller;
import controller.Update;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Variables.allXp;

public class MenuPanel extends JPanel {
    private static MenuPanel INSTANCE;

    public static MenuPanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new MenuPanel();
        }
        return INSTANCE;
    }

    public static final int STANDARD_FRAME_SIZE = Constants.MENU_FRAME_SIZE;
    JButton start, skillTree, settings, tutorial, exit;
    JLabel helloMenuLabel;
    private static Font loadFont(String filename) {
        try { File fontFile = new File(filename);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 12); }
        catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null; } }
    private MenuPanel(){
        MainView.mainPanel.setSize(MENU_FRAME_SIZE, MENU_FRAME_SIZE);
        MainView.mainFrame.setSize(MENU_FRAME_SIZE,MENU_FRAME_SIZE);
        this.setSize(STANDARD_FRAME_SIZE, STANDARD_FRAME_SIZE);
        this.setLayout(null);
        this.setLocation(0, 0);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("menubackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        JLabel menuLabel = new JLabel(new ImageIcon(image));
        menuLabel.setSize(STANDARD_FRAME_SIZE, STANDARD_FRAME_SIZE);
        menuLabel.setLocation(0, 0);
        this.add(menuLabel);

        Font customFont = loadFont("blooods_.ttf");

        helloMenuLabel = new JLabel("WINDOW KILL");
        helloMenuLabel.setSize(400, 50);
        assert customFont != null;
        helloMenuLabel.setFont(customFont.deriveFont(45f));
        //helloMenuLabel.setFont(customFont);

        helloMenuLabel.setForeground(Color.RED);
        helloMenuLabel.setLocation(150, 10);
        menuLabel.add(helloMenuLabel);

        start = new JButton("Start");
        start.setBorderPainted(false);
        start.setLocation(new java.awt.Point(225, 100));
        start.setSize(new Dimension(170, 47));
        start.setBackground(MENU_BUTTON_BACKGROUND_COLOR);
        start.setForeground(MENU_BUTTON_FOREGROUND_COLOR);
        start.setFocusable(false);
        start.setFont(new Font(null,Font.PLAIN, 20));
        start.addActionListener(e -> {
            MainView.mainFrame.setState(JFrame.ICONIFIED);
            GamePanel.getINSTANCE();
            Update.getINSTANCE();
        });
        menuLabel.add(start);

        skillTree = new JButton("Skill Tree");
        skillTree.setBorderPainted(false);
        skillTree.setLocation(new java.awt.Point(225, 200));
        skillTree.setSize(new Dimension(170, 47));
        skillTree.setBackground(MENU_BUTTON_BACKGROUND_COLOR);
        skillTree.setForeground(MENU_BUTTON_FOREGROUND_COLOR);
        skillTree.setFocusable(false);
        skillTree.setFont(new Font(null,Font.PLAIN, 20));
        skillTree.addActionListener(e -> {
            this.setVisible(false);
            INSTANCE = null;
            MainView.mainPanel.setSize(SKILLTREE_FRAME_WIDTH, SKILLTREE_FRAME_HEIGHT);
            MainView.mainFrame.setSize(SKILLTREE_FRAME_WIDTH,SKILLTREE_FRAME_HEIGHT);
            SkillTreePanel.getINSTANCE().xpLabel.setText(String.valueOf(allXp));
            SkillTreePanel.getINSTANCE().setVisible(true);
        });
        menuLabel.add(skillTree);

        settings = new JButton("Settings");
        settings.setLocation(new java.awt.Point(225, 300));
        settings.setSize(new Dimension(170, 47));
        settings.setBorderPainted(false);
        settings.setBackground(MENU_BUTTON_BACKGROUND_COLOR);
        settings.setForeground(MENU_BUTTON_FOREGROUND_COLOR);
        settings.setFocusable(false);
        settings.setFont(new Font(null,Font.PLAIN, 20));
        settings.addActionListener(e -> {
            this.setVisible(false);
            INSTANCE = null;
            SettingPanel.getINSTANCE();

        });
        menuLabel.add(settings);

        tutorial = new JButton("Tutorial");
        tutorial.setBorderPainted(false);
        tutorial.setLocation(new java.awt.Point(225, 400));
        tutorial.setSize(new Dimension(170, 47));
        tutorial.setBackground(MENU_BUTTON_BACKGROUND_COLOR);
        tutorial.setForeground(MENU_BUTTON_FOREGROUND_COLOR);
        tutorial.setFocusable(false);
        tutorial.setFont(new Font(null,Font.PLAIN, 20));
        tutorial.addActionListener(e -> {
            this.setVisible(false);
            INSTANCE = null;
            TutorialPanel.getINSTANCE();

        });
        menuLabel.add(tutorial);

        exit = new JButton("Exit");
        exit.setBorderPainted(false);
        exit.setLocation(new java.awt.Point(45, 490));
        exit.setSize(new Dimension(120, 43));
        exit.setBackground(MENU_BUTTON_BACKGROUND_COLOR);
        exit.setForeground(MENU_BUTTON_FOREGROUND_COLOR);
        exit.setFocusable(false);
        exit.setFont(new Font(null,Font.PLAIN, 20));
        exit.addActionListener(e -> {
            System.exit(0);
        });
        menuLabel.add(exit);

        this.repaint();
        this.revalidate();
        MainView.mainFrame.setVisible(true);
        MainView.mainPanel.add(this);
        MainView.mainPanel.repaint();
        MainView.mainPanel.revalidate();
    }
}

