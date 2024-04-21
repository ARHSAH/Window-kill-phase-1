package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.enemies.SquareModel;
import view.panelsView.SettingPanel;
import view.panelsView.SkillTreePanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;


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
    public  static int squaresNumber = 0;
    public static String activeAbility = "";
    public static int abilityCoolDown = 30000;
    public static boolean activeGAbility;
    public static ArrayList<BulletModel> removedBullets;
    public static ArrayList<SquareModel> removedSquares;
    public static int eRight, eLeft, eUp, eDown;
    public static boolean a;
}
