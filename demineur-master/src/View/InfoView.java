package View;

import Model.Game;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents the game's settings and score display
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class InfoView extends VBox implements Observer {

    private final Game game;
    private final Label Score;
    private final Label nbBomb;
    private final Label nbLines;
    private final Label nbCol;

    /**
     * Create the info view class who observe the observable game
     * https://www.geeksforgeeks.org/inheritance-in-java/
     *
     * @param obs the game to observe
     */
    public InfoView(Observable obs) {
        this.game = (Game) obs;
        this.Score = new Label("-");
        this.Score.setTextFill(Color.WHITE);
        this.Score.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        this.nbBomb = new Label(Integer.toString(game.getNbBomb()));
        this.nbBomb.setTextFill(Color.WHITE);
        this.nbBomb.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        this.nbLines = new Label(Integer.toString(game.getBoard().getPlate().length));
        this.nbLines.setTextFill(Color.WHITE);
        this.nbLines.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        this.nbCol = new Label(Integer.toString(game.getBoard().getPlate()[0].length));
        this.nbCol.setTextFill(Color.WHITE);
        this.nbCol.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        game.addObserver(this);
        initialize();
    }

    /**
     * Allow to initialize the VBOX with multiple HBox who describe the state of
     * the game
     */
    private void initialize() {
        HBox title = new HBox();
        title.setStyle("-fx-background-color: #000000;");
        title.setAlignment(Pos.CENTER);
        title.setSpacing(20);
        Text txtScore = new Text("Score");
        txtScore.setFill(Color.WHITE);
        txtScore.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Text txtBomb = new Text("nb. Bomb");
        txtBomb.setFill(Color.WHITE);
        txtBomb.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Text txtLine = new Text("nb. Line");
        txtLine.setFill(Color.WHITE);
        txtLine.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Text txtCol = new Text("nb. Col");
        txtCol.setFill(Color.WHITE);
        txtCol.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
        title.getChildren().addAll(txtScore, txtBomb, txtLine, txtCol);
        HBox value = new HBox();
        value.setAlignment(Pos.CENTER);
        value.setSpacing(70);
        value.getChildren().addAll(Score, nbBomb, nbLines, nbCol);
        this.getChildren().addAll(title, value);
    }

    /**
     * Changes the value of the score when notified by the game
     */
    @Override
    public void update(Observable o, Object arg) {
        Score.setText(String.valueOf(game.getScore()));
    }

}
