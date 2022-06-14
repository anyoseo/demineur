package Model;

import java.util.Arrays;

/**
 * Represent the field of war that contains the bombs
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Board {

    private final Square[][] plate;

    /**
     * Create a Board of width X and height Y
     *
     * @param x the width of the board
     * @param y the height of the board
     */
    public Board(int x, int y) {
        plate = new Square[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                plate[i][j] = new Square();
            }
        }
    }

    /**
     * Get the plate of the board
     *
     * @return a 2D Array of Square
     */
    public Square[][] getPlate() {
        return plate;
    }

    /**
     * Gets a square in a specific position in the plate
     *
     * @param pos the position of the wanted square
     * @return the wanted square
     */
    public Square getSquareAt(Position pos) {
        return plate[pos.getX()][pos.getY()];
    }

    /**
     * Checks if a Position received in parameter is inside the plate or not
     *
     * @param position the position of the square to check
     * @return true if the square is inside the plate
     */
    public boolean isInside(Position position) {
        return !(position.getX() < 0 || position.getY() < 0
                || position.getX() >= plate.length || position.getY() >= plate[0].length);
    }
    
    

}
