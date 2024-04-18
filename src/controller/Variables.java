package controller;

import view.panelsView.SettingPanel;
import view.panelsView.SkillTreePanel;

import java.awt.geom.Point2D;


public class Variables {
    public static int sensitivity = -80, difficulty = -80,
            volume = -80;

    public static int allXp;
    public static int xp = 200;
    public static int frameWidth = 700, frameHeight = 700;
    public static double bulletSpeed = 10;
    public static Point2D mouseLocation;
    public static boolean firstOfGame= true;

    public static int bulletTimer;
    public static int frameExtendingTimer = 0;
    public static String frameExtendingDirection = "";
    public static int  bulletNumbers;
    public static int damage = 5;
    public  static int squarantinesNumber;
    public static String activeAbility = "";
    public static int abilityCoolDown = 30000;
    public static boolean activeGAbility;
}
