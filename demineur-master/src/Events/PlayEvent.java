package Events;

import Model.Game;
import Model.Position;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Event handle manage the mouse click of the user.
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class PlayEvent implements EventHandler<MouseEvent> {

    private final Game game;
    private final Position pos;

    public PlayEvent(Game g, Position p) {
        this.game = g;
        this.pos = p;
    }

    /**
     * Handles the event trigerred by a click
     *
     * @param t The Mouse event that the user has done.
     */
    @Override
    public void handle(MouseEvent t) {
        if (MouseButton.SECONDARY == t.getButton()) {
            game.putFlag(pos);
            game.isOverByFlag();
        } else {
            if(!game.getBoard().getSquareAt(pos).hasFlag()) {
            game.play(pos.getX(), pos.getY());
            game.incScore();
            game.isOverByFlag();
            game.isOverByBomb();
            }
        }
    }
}
