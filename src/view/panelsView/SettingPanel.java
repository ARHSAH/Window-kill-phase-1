package view.panelsView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Variables.*;

public class SettingPanel extends JPanel {
    private static SettingPanel INSTANCE;


    JLabel helloSettingLabel, sensitivityLabel, difficultyLabel, volumeLabel,
            sensitivityPercentLabel, difficultyPercentLabel, volumePercentLabel ;
    JSlider sensitivitySlider, difficultySlider, volumeSlider;
    JButton exit;
    public static SettingPanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SettingPanel();
        }
        return INSTANCE;
    }
    private SettingPanel(){
        MainView.mainPanel.setSize(SETTING_FRAME_WIDTH, SETTING_FRAME_HEIGHT);
        MainView.mainFrame.setSize(SETTING_FRAME_WIDTH,SETTING_FRAME_HEIGHT);
        this.setSize(SETTING_FRAME_WIDTH, SETTING_FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(null);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("settingbackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        JLabel settingLabel = new JLabel(new ImageIcon(image));
        settingLabel.setSize(SETTING_FRAME_WIDTH, SETTING_FRAME_HEIGHT);
        settingLabel.setLocation(0, 0);
        this.add(settingLabel);

        helloSettingLabel = new JLabel("SETTINGS");
        helloSettingLabel.setSize(200, 50);
        helloSettingLabel.setFont(new Font(null, Font.PLAIN,40));
        helloSettingLabel.setForeground(Color.WHITE);
        helloSettingLabel.setLocation(280, 10);
        settingLabel.add(helloSettingLabel);

        //SENSITIVITY
        sensitivityLabel = new JLabel("SENSITIVITY ");
        sensitivityLabel.setSize(180, 50);
        sensitivityLabel.setFont(new Font(null, Font.PLAIN,25));
        sensitivityLabel.setForeground(Color.WHITE);
        sensitivityLabel.setLocation(65, 94);

        sensitivitySlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
        sensitivitySlider.setSize(new Dimension(300, 27));
        sensitivitySlider.setLocation(new Point(260,110));
        sensitivitySlider.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        sensitivitySlider.setForeground(SETTING_SLIDERS_FOREGROUND_COLOR);
        sensitivitySlider.addChangeListener(e -> {
            sensitivity = sensitivitySlider.getValue();
            sensitivityPercentLabel.setText(sensitivity + "%");
            settingLabel.repaint();
            settingLabel.revalidate();
        });

        sensitivityPercentLabel = new JLabel(50 + "%");
        sensitivityPercentLabel.setSize(180, 50);
        sensitivityPercentLabel.setFont(new Font(null, Font.PLAIN,27));
        sensitivityPercentLabel.setForeground(Color.WHITE);
        sensitivityPercentLabel.setLocation(600, 96);

        settingLabel.add(sensitivityLabel);
        settingLabel.add(sensitivitySlider);
        settingLabel.add(sensitivityPercentLabel);

        //DIFFICULTY
        difficultyLabel = new JLabel("DIFFICULTY ");
        difficultyLabel.setSize(180, 50);
        difficultyLabel.setFont(new Font(null, Font.PLAIN,25));
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setLocation(65, 194);

        difficultySlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
        difficultySlider.setSize(new Dimension(300, 27));
        difficultySlider.setLocation(new Point(260,210));
        difficultySlider.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        difficultySlider.setForeground(SETTING_SLIDERS_FOREGROUND_COLOR);
        difficultySlider.addChangeListener(e -> {
            difficulty = difficultySlider.getValue();
            difficultyPercentLabel.setText(difficulty + "%");
            settingLabel.repaint();
            settingLabel.revalidate();
        });

        difficultyPercentLabel = new JLabel(50 + "%");
        difficultyPercentLabel.setSize(180, 50);
        difficultyPercentLabel.setFont(new Font(null, Font.PLAIN,27));
        difficultyPercentLabel.setForeground(Color.WHITE);
        difficultyPercentLabel.setLocation(600, 196);

        settingLabel.add(difficultyLabel);
        settingLabel.add(difficultySlider);
        settingLabel.add(difficultyPercentLabel);

        //VOLUME
        volumeLabel = new JLabel("VOLUME ");
        volumeLabel.setSize(180, 50);
        volumeLabel.setFont(new Font(null, Font.PLAIN,25));
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setLocation(65, 294);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
        volumeSlider.setSize(new Dimension(300, 27));
        volumeSlider.setLocation(new Point(260,310));
        volumeSlider.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        volumeSlider.setForeground(SETTING_SLIDERS_FOREGROUND_COLOR);
        volumeSlider.addChangeListener(e -> {
            volume = volumeSlider.getValue();
            volumePercentLabel.setText(volume + "%");
            settingLabel.repaint();
            settingLabel.revalidate();
        });

        volumePercentLabel = new JLabel(50 + "%");
        volumePercentLabel.setSize(180, 50);
        volumePercentLabel.setFont(new Font(null, Font.PLAIN,27));
        volumePercentLabel.setForeground(Color.WHITE);
        volumePercentLabel.setLocation(600, 296);

        settingLabel.add(volumeLabel);
        settingLabel.add(volumeSlider);
        settingLabel.add(volumePercentLabel);

        //EXIT
        exit = new JButton("MAIN MENU");
        exit.setSize(250, 30);
        exit.setFont(new Font(null, Font.PLAIN,20));
        exit.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        exit.setForeground(Color.WHITE);
        exit.setLocation(0, 432);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            this.setVisible(false);
            MenuPanel.getINSTANCE();
        });
        settingLabel.add(exit);


        this.repaint();
        this.revalidate();
        MainView.mainPanel.add(this);
        MainView.mainPanel.repaint();
        MainView.mainPanel.revalidate();
    }

    public static int getSensitivity() {
        return sensitivity;
    }
    public static int getDifficulty() {
        return sensitivity;
    }
    public static int getVolume() {
        return sensitivity;
    }
}
