package Model;

/**
 * Represents the possible directions relative to a square
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), DOWN_LEFT(1, -1), DOWN_RIGHT(1, 1), UP_LEFT(-1, -1), UP_RIGHT(-1, 1);

    private final int row;
    private final int column;

    /**
     * Constructor who initialize the row and the column
     *
     * @param row the row of the direction
     * @param column the column of the direction
     */
    private Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the column of the direction
     *
     * @return the column of the direction
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the row of the direction
     *
     * @return the row of the direction
     */
    public int getRow() {
        return row;
    }

}
