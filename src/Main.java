import controller.Update;
import model.charactersModel.enemies.SquareModel;
import view.panelsView.MainView;

import java.awt.geom.Point2D;

import static controller.Constants.*;

public class Main {
    public static void main(String[] args) {
        new SquareModel(20, 20, SQUARE_LENGTH, SQUARE_HP, SQUARE_DAMAGE,null);
        new MainView();

    }
}