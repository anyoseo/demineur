package View;

import Model.Square;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Represent a the view of one Square he is observing the observable "Square",
 * when ever the Square changing is characteristics the SquareView will update.
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class SquareView extends StackPane implements Observer {

    private static final int HEIGHT_WIDTH = 25;
    private final Square square;
    private ImageView im;

    /**
     * Allow to create a SquareView that will observe the Square(Observable)
     * https://www.geeksforgeeks.org/inheritance-in-java/
     *
     * @param obs the observable Square
     */
    public SquareView(Observable obs) throws FileNotFoundException {
        this.square = (Square) obs;
        this.square.addObserver(this);
        this.im = new ImageView(new Image(new FileInputStream("src\\image\\hidden.jpg")));
        im.setFitHeight(HEIGHT_WIDTH);
        im.setFitWidth(HEIGHT_WIDTH);
        this.getChildren().add(im);
    }

    /**
     * Allow to find all parameter of a Square like we can update the View of
     * the Square (Image)
     */
    private void changeSprite() throws FileNotFoundException {
        if (square.hasFlag() && square.isHidden()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\flag.jpg")));
        } else if (!square.hasFlag() && square.isHidden()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\hidden.jpg")));
        } else if (square.isNumber()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\" + square.getValue() + ".jpg")));
        } else if (square.isVoid()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\void.jpg")));
        } else if (square.isBomb()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\bomb.jpg")));
        } else if (square.isHidden()) {
            this.im = new ImageView(new Image(new FileInputStream("src\\image\\hidden.jpg")));
        }
        fit();
        this.getChildren().clear();
        this.getChildren().add(im);
    }

    /**
     * Allow to give a fixed HEIGHT and WIDTH for images
     */
    void fit() {
        im.setFitHeight(HEIGHT_WIDTH);
        im.setFitWidth(HEIGHT_WIDTH);
    }

    /**
     * When ever a observable notify us that there is change this method is
     * called to update the change in the SquareView.
     */
    @Override
    public void update(Observable o, Object o1) {
        try {
            changeSprite();
        } catch (FileNotFoundException ex) {
        }
    }
}
