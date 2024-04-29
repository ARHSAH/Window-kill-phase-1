package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.CollectibleModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import view.panelsView.SettingPanel;
import view.panelsView.SkillTreePanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.BULLET_DAMAGE;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.CollectibleModel.collectibleModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.charactersModel.enemies.TriangleModel.triangleModels;


public class Variables {
    public static int sensitivity = 50, difficulty = 50,
            volume = 50;

    public static int allXp = 0;
    public static int xp = 0;
    public static double frameWidth = 700, frameHeight = 700;

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
    public static int frameShrinkAmount = 1;
    public static boolean activeGAbility;
    public static ArrayList<BulletModel> removedBullets;
    public static ArrayList<SquareModel> removedSquares;
    public static ArrayList<TriangleModel> removedTriangles;
    public static ArrayList<CollectibleModel> removedCollectibles;
    public static int eRight, eLeft, eUp, eDown;
    public static boolean a;
    public static int hp = 100;
    public static int epsilonVertices = 0;
    public static int trianglesNumber = 0;
    public static int collectiblesNumber = 0;
    public static int acesoHp = 0;
    public static int wave = 1;
    public static int elapsedTime, minutes1, minutes2, seconds1, seconds2;
    public static int total;
    public static int waveTimer;
    public static int waveEnemiesNumber;
    public static boolean gameFinished;
    public static void reset(){

        xp = 0;
        frameWidth = 700 ;
        frameHeight = 700;
        mouseLocation = new Point2D.Double(0, 0);
        firstOfGame = true;
        bulletTimer = 0;
        frameExtendingTimer = 0;
        frameExtendingDirection = "";
        bulletNumbers = 0;
        damage = BULLET_DAMAGE;
        squaresNumber = 0;
        abilityCoolDown = 30000;
        frameShrinkAmount = 1;
        activeGAbility = false;
        a = false;
        hp = 100;
        epsilonVertices = 0;
        trianglesNumber = 0;
        collectiblesNumber = 0;
        acesoHp = 0;
        wave = 1;
        elapsedTime= 0;
        minutes1 = 0;
        minutes2 = 0;
        seconds1 = 0;
        seconds2 = 0;
        waveTimer = 0;
        waveEnemiesNumber = 0;
        gameFinished = false;
    }
}
