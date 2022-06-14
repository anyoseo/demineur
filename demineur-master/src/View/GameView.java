package View;

import DataBase.Data;
import Model.Game;
import java.util.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Represents the global game view with the board view, score view and records
 * table
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class GameView extends HBox {

    private final Game game;
    private final BoardView boardView;
    private final InfoView infoView;
    private final VBox subRoot;
    private final ScoresView scoreView;

    /**
     * Constructor for the gameview
     *
     * @param g the game to display
     * @param data the datatable to display
     */
    public GameView(Observable g, Data data) {
        this.game = (Game) g;
        boardView = new BoardView(game);
        infoView = new InfoView(game);
        subRoot = new VBox();
        scoreView = new ScoresView(game, data);

        setPadding(new Insets(30));
        setSpacing(5);
        subRoot.setSpacing(15);

        subRoot.getChildren().addAll(boardView, infoView);
        this.getChildren().addAll(subRoot, scoreView);
    }

    /**
     * Displays the message that appears when the user wins the game
     *
     * @param name the name of the user
     */
    public void showWin(String name) {
        scoreView.stopTimer();
        Label win = new Label("Bravo " + name + ", tu as gagné !");
        win.setTextFill(Color.WHITE);
        win.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        win.setStyle("-fx-background-color: black;");
        subRoot.getChildren().add(win);
    }

    /**
     * Displays the message that appears when the user looses the game
     */
    public void showLoose() {
        scoreView.stopTimer();
        Label loose = new Label("Tu as perdu ! Dommage !");
        loose.setTextFill(Color.WHITE);
        loose.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        loose.setStyle("-fx-background-color: black;");
        subRoot.getChildren().add(loose);
    }

    /**
     * Displays the given message
     *
     * @param content the message to display
     */
    public void showMessage(String content) {
        Label loose = new Label(content);
        loose.setTextFill(Color.WHITE);
        loose.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        loose.setStyle("-fx-background-color: black;");

        subRoot.getChildren().add(loose);
    }

    /**
     * Gets the boardview
     *
     * @return the boardview
     */
    public BoardView getBoardView() {
        return boardView;
    }

}
