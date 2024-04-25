package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import view.panelsView.SettingPanel;
import view.panelsView.SkillTreePanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Variables {
    public static int sensitivity = 50, difficulty = 50,
            volume = 50;

    public static int allXp = 1000;
    public static int xp = 200;
    public static int frameWidth = 700, frameHeight = 700;

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
    public static ArrayList<TriangleModel> removedTriangles;
    public static int eRight, eLeft, eUp, eDown;
    public static boolean a;
    public static int hp = 100;
    public static int epsilonVertices = 0;
    public static int triangleNumbers = 0;
    public static int acesoHp = 0;
    public static int wave = 1;
    public static int elapsedTime, minutes1, minutes2, seconds1, seconds2;
}
