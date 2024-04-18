package controller;

import view.panelsView.SettingPanel;

import java.awt.geom.Point2D;

public class Variables {
    public static int sensitivity = SettingPanel.getSensitivity(), difficulty = SettingPanel.getDifficulty(),
            volume = SettingPanel.getVolume();
    public static int xp;
    public static int frameWidth = 700, frameHeight = 700;
    public static double bulletSpeed = 10;
    public static Point2D mouseLocation;
    public static boolean firstOfGame= true;

    public static int bulletTimer;
    public static int  bulletNumbers;
}
