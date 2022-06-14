package View;

import Events.NullEvent;
import Events.PlayEvent;
import Model.Game;
import Model.Position;
import java.io.FileNotFoundException;
import javafx.scene.layout.GridPane;

/**
 * Represent the board view of the game
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class BoardView extends GridPane {

    private Game g;
    private SquareView[][] squareView;

    /**
     * Creates the board view of the game
     *
     * @param g the current game going on
     */
    public BoardView(Game g) {
        this.g = g;
        this.squareView = new SquareView[g.getBoard().getPlate().length][g.getBoard().getPlate()[0].length];
        for (int i = 0; i < g.getBoard().getPlate().length; i++) {
            for (int j = 0; j < g.getBoard().getPlate()[0].length; j++) {
                Position position = new Position(i, j);
                try {
                    squareView[i][j] = new SquareView(g.getBoard().getPlate()[i][j]);
                    squareView[i][j].setOnMouseClicked(new PlayEvent(g, position));
                    this.add(squareView[i][j], j, i);

                } catch (FileNotFoundException ex) {
                }
            }
        }
    }

    /**
     * Disables the events for the current game
     */
    public void disableEvent() {
        for (int i = 0; i < g.getBoard().getPlate().length; i++) {
            for (int j = 0; j < g.getBoard().getPlate()[0].length; j++) {
                squareView[i][j].setOnMouseClicked(new NullEvent());
            }
        }
    }

}
