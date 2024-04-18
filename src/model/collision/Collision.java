package model.collision;

import model.charactersModel.BulletModel;

import java.util.ArrayList;

import static controller.Variables.*;
import static model.charactersModel.BulletModel.bulletModels;

public class Collision {
    public static void checkBulletFrame(){
        ArrayList<BulletModel> bulletModels1 = new ArrayList<>();
        for(BulletModel value : bulletModels) {
            if (value.getX() < 2 || value.getX() > frameWidth ||
                    value.getY() < 2 || value.getY() > frameHeight) {
                bulletModels1.add(value);
            }
        }
        for(BulletModel value : bulletModels1) {
           value.clip.stop();
           bulletModels.remove(value);
        }
    }
}
