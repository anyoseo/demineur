package Controller;

import DataBase.ConfigManager;
import DataBase.Data;
import DataBase.Record;
import Model.Game;
import View.GameView;
import View.MainMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Controller extends Application {

    private Game g;
    private Data data;
    private final MainMenu menu;
    private GameView gameView;
    private Stage stage;

    /**
     * Create a window that will play the game and will manage the user input.
     */
    public Controller() {
        this.menu = new MainMenu();
        data = new Data();
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dbUrl = ConfigManager.getInstance().getProperties("db.url");
        data.connect(dbUrl);
    }

    /**
     * Shows the message that happens when the user clicks on a bomb and stops
     * him from performing further actions on the board
     */
    public void endIfLost() {
        gameView.getBoardView().disableEvent();
        gameView.showLoose();
    }

    /**
     * Shows the game winning message, saves the user score and shows the record
     */
    public void endIfWon() {
        gameView.getBoardView().disableEvent();
        gameView.showWin(menu.getName());

        Record bestRecord = data.getBestRecord(data.getGameId(menu.getSelectedLines(), menu.getSelectedColumns(), menu.getSelectedMines()));
        data.insertDataRecords(g.getScore(), data.getGameId(menu.getSelectedLines(), menu.getSelectedColumns(), menu.getSelectedMines()), menu.getName());
        if (bestRecord.getScore() > g.getScore()) {
            gameView.showMessage(menu.getName() + ", detient le nouveau record");
        }
    }

    /**
     * This method is called by the launch method, once called the window of the
     * game will show up.
     *
     * @param stage NaN
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(menu, 600, 400, Color.WHITE);
        stage.setResizable(false);
        initMainMenu();
        stage.setTitle("MineSweeper");
        stage.getIcons().add(new Image("/image/bomb.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the game window with the chosen parameters
     */
    public void initMainMenu() {

        menu.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (var event) -> {
            if (menu.getName().isEmpty()) {
                menu.setDefaultName();
            } else {
                int lines = menu.getSelectedLines();
                int columns = menu.getSelectedColumns();
                Image BackGround = new Image("https://data.whicdn.com/images/280664567/original.gif", columns * 25 + 300, lines * 25 + 150, false, true);
                g = new Game(lines, columns, menu.getSelectedMines(), this);
                gameView = new GameView(g, data);
                BackgroundImage background = new BackgroundImage(BackGround,
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                gameView.setBackground(new Background(background));
                Label timer = new Label();
                Scene scene = new Scene(gameView, columns * 25 + 300, lines * 25 + 150);
                gameView.getChildren().add(timer);
                stage.setTitle("MineSweeper");
                stage.setScene(scene);
                stage.show();
            }

        });
    }

    /**
     * Main method
     *
     * @param args NaN
     */
    public static void main(String[] args) {
        launch(args);
    }

}
