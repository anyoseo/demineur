package DataBase;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Record {

    private SimpleStringProperty id;
    private SimpleStringProperty score;
    private SimpleStringProperty date;
    private SimpleStringProperty id_game;
    private SimpleStringProperty name;

    public Record() {

    }

    public void setId(int id) {
        this.id = new SimpleStringProperty(String.valueOf(id));
    }

    public void setScore(int score) {
        this.score = new SimpleStringProperty(String.valueOf(score));
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public void setId_game(int id_game) {
        this.id_game = new SimpleStringProperty(String.valueOf(id_game));
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public int getId() {
        return Integer.parseInt(id.get());
    }

    public int getScore() {
        return Integer.parseInt(score.get());
    }

    public String getDate() {
        return date.get();
    }

    public int getId_game() {
        return Integer.parseInt(id_game.get());
    }

    public String getName() {
        return name.get();
    }

}
