package View;

import DataBase.Data;
import DataBase.Record;
import Model.Game;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Represents the displays of the scores obtained for the current game
 * configuration
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public final class ScoreTableView extends HBox implements Observer {

    private final Game game;
    Group displayBox;
    TableView<Record> table;
    ScrollPane sp;
    Data data;
    TableColumn<Record, String> indexCol;
    TableColumn<Record, String> NameCol;
    TableColumn<Record, String> ScoreCol;
    static int secondes = 0;

    /**
     * Create the info view class who observe the observable game
     * https://www.geeksforgeeks.org/inheritance-in-java/
     *
     * @param g the game
     * @param data
     */
    public ScoreTableView(Observable g, Data data) {
        this.game = (Game) g;
        this.data = data;
        table = new TableView<>();
        data.addObserver(this);
        this.setWidth(300);
        this.setMaxHeight(250);
        indexCol = new TableColumn<>("Classement");
        NameCol = new TableColumn<>("Nom       ");
        ScoreCol = new TableColumn<>("Score     ");

        sp = new ScrollPane(table);

        this.getChildren().addAll(sp);
        initialize();
        load();

    }

    /**
     * Initializes the scores table with the three parameters
     */
    private void initialize() {
        indexCol.setPrefWidth(80);
        indexCol.setCellValueFactory(new PropertyValueFactory<>("id_game"));

        NameCol.setPrefWidth(80);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        ScoreCol.setPrefWidth(80);
        ScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(indexCol, NameCol, ScoreCol);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHmax(1);
    }

    /**
     * Loads the differents records for the game
     */
    public void load() {
        List<Record> record = data.getListRecords(data.getGameId(game.getNbline(), game.getNbColumn(), game.getNbBomb()));
        ObservableList<Record> dataRecords
                = FXCollections.observableArrayList();
        for (Record reco : record) {
            dataRecords.add(reco);
        }

        table.setItems(dataRecords);
    }

    /**
     * Loads the scores when notified
     */
    @Override
    public void update(Observable o, Object arg) {
        load();
    }

}
