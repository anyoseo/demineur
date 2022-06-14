package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Represents the main menu screen that lets the user choose the settings for
 * the game
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class MainMenu extends VBox {

    private final ComboBox lines;
    private final ComboBox columns;
    private final ComboBox nbMine;
    private final Button validation;
    private HBox espaceName = new HBox();
    TextField name = new TextField();
    private final HBox hbox;
    private final int MINSIZE = 10;
    private final int MAXSIZE = 20;
    private final int MINMINES = 1;

    /**
     * Creates a display containing the lines, columns and bombs wanted and the
     * user name
     */
    public MainMenu() {
        this.lines = new ComboBox();
        this.columns = new ComboBox();
        this.nbMine = new ComboBox();
        name.setMinWidth(50);
        name.setMaxWidth(500);
        Label labelName = new Label("Veuillez entrer votre nom : ");
        labelName.setMinWidth(50);
        labelName.setMaxWidth(300);
        labelName.setTextFill(Color.WHITE);
        espaceName.getChildren().addAll(labelName, name);
        espaceName.setAlignment(Pos.CENTER);
        this.getChildren().add(espaceName);
        this.validation = new Button("Valider");
        this.hbox = new HBox();
        initBackground();
        initComboBox();
    }

    /**
     * Adds the background image for the main menu
     */
    private void initBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(600, 400, true, true, true, false);
        BackgroundImage backGround = new BackgroundImage(new Image("/image/Menu.png"), BackgroundRepeat.SPACE,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        this.setBackground(new Background(backGround));
        this.hbox.setSpacing(20);
        this.hbox.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Creates the combo boxes with the number of lines, columns and mines The
     * number of mines can only be up to 50% of the number of total squares
     */
    private void initComboBox() {
        for (int i = MINSIZE; i <= MAXSIZE; i++) {
            this.lines.getItems().add(i);
            this.columns.getItems().add(i);
        }
        lines.getSelectionModel().select(0);
        columns.getSelectionModel().select(0);

        for (int i = MINMINES; i <= ((lines.getSelectionModel().getSelectedIndex()
                + MINSIZE) * (columns.getSelectionModel().getSelectedIndex()
                + MINSIZE)) / 2; i++) {
            nbMine.getItems().add(i);
        }
        nbMine.getSelectionModel().select(6);

        hbox.getChildren().addAll(lines, columns, nbMine);

        lines.setOnAction((arg0) -> {
            nbMine.getItems().clear();
            for (int i = MINMINES; i <= ((lines.getSelectionModel().getSelectedIndex()
                    + MINSIZE) * (columns.getSelectionModel().getSelectedIndex()
                    + MINSIZE)) / 2; i++) {
                nbMine.getItems().add(i);
            }
            nbMine.getSelectionModel().select(0);
        });

        columns.setOnAction((arg0) -> {
            nbMine.getItems().clear();
            for (int i = MINMINES; i <= ((lines.getSelectionModel().getSelectedIndex()
                    + MINSIZE) * (columns.getSelectionModel().getSelectedIndex() + MINSIZE))
                    / 2; i++) {
                nbMine.getItems().add(i);
            }
            nbMine.getSelectionModel().select(0);
        });

        this.getChildren().addAll(hbox, validation);
    }

    /**
     * Gets the validation button
     *
     * @return the validation button
     */
    public Button getButton() {
        return validation;
    }

    /**
     * Gets the wanted number of lines
     *
     * @return the wanted number of lines for the game
     */
    public int getSelectedLines() {
        return lines.getSelectionModel().getSelectedIndex() + MINSIZE;
    }

    /**
     * Gets the wanted number of columns
     *
     * @return the wanted number of columns for the game
     */
    public int getSelectedColumns() {
        return columns.getSelectionModel().getSelectedIndex() + MINSIZE;
    }

    /**
     * Gets the number of bombs
     *
     * @return the wanted number of bombs for the game
     */
    public int getSelectedMines() {
        return nbMine.getSelectionModel().getSelectedIndex() + MINMINES;
    }

    /**
     * Gets the username
     *
     * @return the username
     */
    public String getName() {
        return name.getText();
    }
    
    /**
     * Sets the username to player when no username is entered
     */
    public void setDefaultName() {
        int rand = (int) (Math.random()* ( 999 - 1 ));
        name.setText("Player"+rand);
    }

}
