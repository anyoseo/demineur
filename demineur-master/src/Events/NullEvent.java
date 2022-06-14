package Events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Represents an event that does nothing. Used when we need to disable the event
 *
 * @author brarou
 */
public class NullEvent implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent t) {

    }

}
