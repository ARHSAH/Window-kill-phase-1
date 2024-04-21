import controller.Update;
import model.charactersModel.enemies.SquareModel;
import model.movement.Direction;
import view.panelsView.MainView;

import java.awt.geom.Point2D;

import static controller.Constants.*;

public class Main {
    public static void main(String[] args) {

        new SquareModel(20, 20, SQUARE_LENGTH, 10, 6, 0.5 , new Point2D.Double(0, 0));
        new MainView();

    }
}