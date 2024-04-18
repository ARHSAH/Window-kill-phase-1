package model.collision;

import model.charactersModel.BulletModel;
import model.charactersModel.EpsilonModel;
import view.panelsView.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static controller.Constants.FRAME_SHRINK_AMOUNT;
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
                value.clip.stop();
                bulletModels.remove(value);
            }
        }

    }
}
