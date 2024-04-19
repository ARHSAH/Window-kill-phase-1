package model.collision;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.SquareModel;
import view.panelsView.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.FRAME_SHRINK_AMOUNT;
import static controller.Utils.distance;
import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;

public class Collision {
    static int frameTimer;

    public static void checkBulletFrame(){
        ArrayList<BulletModel> bulletModels1 = new ArrayList<>();
        for(BulletModel value : bulletModels) {
            if (value.getX() < 2 || value.getX() > frameWidth ||
                    value.getY() < 2 || value.getY() > frameHeight) {
                bulletModels1.add(value);
            }
        }
        for(BulletModel value : bulletModels1) {
            if(value.getX() > frameWidth){
                frameExtendingDirection = "right";
            }else if(value.getX() < 1){
                frameExtendingDirection = "left";
            }else if(value.getY() > frameHeight){
                frameExtendingDirection = "bottom";
            }else if(value.getY() < 1){
                frameExtendingDirection = "top";
            }
            value.clip.stop();
            bulletModels.remove(value);
        }
    }

    public static boolean checkCircleSquare(Point2D.Double center, double radius,
                                         Point2D.Double leftTopV, int length) {
        return (center.getX() > leftTopV.getX() + length &&
                center.getY() > leftTopV.getY() &&
                center.getY() < leftTopV.getY() + length
                && center.getX() - (leftTopV.getX() + length) < radius)
                || (center.getX() < leftTopV.getX() &&
                center.getY() > leftTopV.getY() &&
                center.getY() < leftTopV.getY() + length
                && center.getX() - (leftTopV.getX() + length) < radius) ||
                (center.getY() < leftTopV.getY() &&
                        center.getX() > leftTopV.getX() &&
                        center.getX() < leftTopV.getX() + length
                        && center.getY() - (leftTopV.getY() + length) < radius) ||
                (center.getY() > leftTopV.getY() + length &&
                        center.getX() > leftTopV.getX() &&
                        center.getX() < leftTopV.getX() + length
                        && center.getY() - (leftTopV.getY() + length) < radius)
                || (distance(center, leftTopV) < radius)
                || (distance(center, new Point2D.Double
                (leftTopV.getX() + length , leftTopV.getY())) < radius) ||
                (distance(center, new Point2D.Double
                        (leftTopV.getX(), leftTopV.getY() + length)) < radius) ||
                (distance(center, new Point2D.Double
                        (leftTopV.getX() + length, leftTopV.getY() + length)) < radius);
    }
}
