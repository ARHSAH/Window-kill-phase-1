package controller;

import model.charactersModel.EpsilonModel;
import view.charactersView.EpsilonView;

public class Controller {
    public static void setEpsilonViewLocation(){
        EpsilonView.getINSTANCE().setX(EpsilonModel.getINSTANCE().getX());
        EpsilonView.getINSTANCE().setY(EpsilonModel.getINSTANCE().getY());
        EpsilonView.getINSTANCE().setRadius(EpsilonModel.getINSTANCE().getRadius());

    }

}
