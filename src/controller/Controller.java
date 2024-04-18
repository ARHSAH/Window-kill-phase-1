package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.panelsView.GameFrame;

import java.awt.*;

import static controller.Constants.MINIMUM_FRAME_SIZE;
import static controller.Constants.SHRINK_AMOUNT;
import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;
import static view.charactersView.BulletView.bulletViews;

public class Controller {
    public static void createBulletView(int id){
       new BulletView(id);
    }
    public static void setViewLocation(){
        EpsilonView.getINSTANCE().setX(EpsilonModel.getINSTANCE().getX());
        EpsilonView.getINSTANCE().setY(EpsilonModel.getINSTANCE().getY());
        EpsilonView.getINSTANCE().setRadius(EpsilonModel.getINSTANCE().getRadius());
        for (BulletView value : bulletViews) {
            BulletModel bulletModel = findModel(value.getId());
            if(bulletModel == null){
                bulletViews.remove(value);
                return;
            }
            value.setX(bulletModel.getX());
            value.setY(bulletModel.getY());
            value.setRadius(bulletModel.getRadius());
        }
    }
    public static BulletModel findModel(int id){
        for (BulletModel value: bulletModels){
            if (value.getId() == id){
                return value;
            }
        }
        return null;
    }

    public static void startShrinkage(){
        if(frameHeight > MINIMUM_FRAME_SIZE && frameWidth > MINIMUM_FRAME_SIZE){
            GameFrame.getINSTANCE().setLocation(new Point(GameFrame.getINSTANCE().getX() + SHRINK_AMOUNT,
                    GameFrame.getINSTANCE().getY() + SHRINK_AMOUNT));
            frameWidth -= 2 * SHRINK_AMOUNT;
            frameHeight -= 2 * SHRINK_AMOUNT;
            EpsilonModel.getINSTANCE().setX((double) frameWidth / 2);
            EpsilonModel.getINSTANCE().setY((double) frameHeight / 2);
            GameFrame.getINSTANCE().setSize(new Dimension(frameWidth, frameHeight));
        }else{
            firstOfGame = false;
        }
    }

    public static void gameShrinkage(){
        if(frameHeight > MINIMUM_FRAME_SIZE){
            
        }
    }


}
