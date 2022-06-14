package View;

import DataBase.Data;
import Model.Game;
import static View.ScoreTableView.secondes;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class represents the display of the timer
 * @author TheHotManZ
 */
public class ScoresView extends VBox {
    private final ScoreTableView scoreView;
    private final Label timer;
    private final Game game;
    boolean endGame = false;
    

    /**
     Constructor for the class ScoresView which creates the thread which displays the timer
     * @param game the game to display
     * @param data the datatable to display
     */
    public ScoresView(Game game, Data data) {
        this.game= game;
        scoreView = new ScoreTableView(game, data);
        timer = new Label();
        timer.setMinWidth(50);
        timer.setMinHeight(50);
        timer.setTextFill(Color.color(1, 1, 1));
        final double MAX_FONT_SIZE = 20.0; // define max font size you need
        timer.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, MAX_FONT_SIZE));//.setFont(new Font(MAX_FONT_SIZE));
        
        this.getChildren().addAll(scoreView, timer);
        
        timer.setText(String.format("  Temps : %ds", 0));
        timer.setStyle("-fx-background-color: black;");
        Thread t = new Thread(() -> {
            for (secondes = 0; !Thread.currentThread().isInterrupted() && !endGame ; secondes++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException z) {
                    // Log ou afficher l'erreur !!!
                }
                Platform.runLater(() -> {
                    timer.setText(String.format("Temps : %ds", secondes));
                });
            }
        });
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }
    
    /**
     * Method which stops the timer in the thread
     */
    public void stopTimer(){
        endGame=true;
    }
    

    
    
    
}
