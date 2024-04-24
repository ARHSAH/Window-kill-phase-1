import controller.Update;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.movement.Direction;
import view.panelsView.MainView;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Constants.*;

public class Main {
    public static void main(String[] args) {
        new TriangleModel(new Point(20, 20), TRIANGLE_LENGTH, TRIANGLE_HP,3, 0 );
        new SquareModel(40, 100, SQUARE_LENGTH, SQUARE_HP, SQUARE_DAMAGE, SQUARE_SPEED);
        new SquareModel(20, 20, SQUARE_LENGTH, SQUARE_HP, SQUARE_DAMAGE, 0.5 );
        new MainView();

    }
}