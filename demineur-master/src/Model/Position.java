package Model;

/**
 * Represents the position of a square
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor of the Position class for creating the Position of a Square
     *
     * @param x deltaX
     * @param y deltaY
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the line of the position
     *
     * @return the line of the position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the column of the position
     *
     * @return the column of the position
     */
    public int getY() {
        return y;
    }

    /**
     * Gives the next position in a given direction
     *
     * @param direction the direction where to get the next square from
     * @return the next position in the given direction
     */
    public Position next(Direction direction) {
        return new Position(x + direction.getRow(), y + direction.getColumn());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
