package Model;

import Controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Contains all the logical needed component to correctly play the game
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public final class Game extends Observable {

    private final Board board;
    private final Controller controller;
    private int score;
    private final int nbBomb;
    private final int nbline;
    private final int nbColumn;

    private final List<Position> MinePos;
    
    
    /**
     * Create a Game with a board of x lines and y columns
     *
     * @param x the height of the board
     * @param y the width of the board
     * @param nbBomb number of bombs present
     */
    public Game(int x, int y, int nbBomb) {
        board = new Board(x, y);
        this.controller = null;
        MinePos = generateMinePositions(nbBomb);
        this.nbBomb = nbBomb;
        nbline = x;
        nbColumn = y;
        this.score = 0;
        init();
    }

    /**
     * Create a Game with a board of x lines and y columns
     *
     * @param x the height of the board
     * @param y the width of the board
     * @param nbBomb number of bombs present
     * @param controller the controller
     */
    public Game(int x, int y, int nbBomb, Controller controller) {
        board = new Board(x, y);
        this.controller = controller;
        MinePos = generateMinePositions(nbBomb);
        this.nbBomb = nbBomb;
        nbline = x;
        nbColumn = y;
        this.score = 0;
        init();
    }

    /**
     * Gets the number of lines of the game
     *
     * @return the number of lines of the game
     */
    public int getNbline() {
        return nbline;
    }

    /**
     * Gets the number of columns of the game
     *
     * @return the number of columns of the game
     */
    public int getNbColumn() {
        return nbColumn;
    }

    /**
     * Initializes the game by putting randomly the needed amount of bombs
     */
    public void init() {
        MinePos.stream().map((pos) -> {
            board.getSquareAt(pos).setState(State.BOMB);
            return pos;
        }).forEachOrdered((pos) -> {
            for (Direction direction : Direction.values()) {
                Position posi = pos.next(direction);
                if (board.isInside(posi)) {
                    if (!board.getSquareAt(posi).isBomb()) {
                        Square next = board.getSquareAt(posi);
                        next.setState(State.NUMBER);
                        next.incValue();
                    }

                }
            }
        });
    }

    /**
     * Checks if a bomb has been uncovered
     *
     * @return true if a bomb has been uncovered
     */
    public boolean isOverByBomb() {
        for (Position MinePo : MinePos) {
            if (!board.getSquareAt(MinePo).isHidden()) {
                controller.endIfLost();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all the mines have been covered by flags
     *
     * @return true if they have
     */
    public boolean isOverByFlag() {
        for (int i = 0; i < board.getPlate().length; i++) {
            for (int j = 0; j < board.getPlate()[0].length; j++) {
                Square square = board.getSquareAt(new Position(i,j));
                if(square.isBomb() && !square.hasFlag()) {
                    return false;
                } if(!square.isBomb() && square.isHidden()) {
                return false;
            }
            }
        }
        if (isOverByBomb()) {
            return false;
        }
        controller.endIfWon();
        notifyObservers();
        return true;
    }

    /**
     * Uncovers the square at a certain position and all other neighboring
     * squares with no bombs around if this position has no bombs around
     *
     * @param x the current line in the plate
     * @param y the current column in the plate
     */
    public void play(int x, int y) {
        Position p = new Position(x, y);
        if (board.getSquareAt(p).hasFlag()) {
            return;
        }
        if (board.getSquareAt(p).isVoid() && board.getSquareAt(p).isHidden()) {
            board.getSquareAt(p).reveal();
            if (board.isInside(new Position(x + 1, y))) {
                play(x + 1, y);
            }
            if (board.isInside(new Position(x - 1, y))) {
                play(x - 1, y);
            }
            if (board.isInside(new Position(x, y + 1))) {
                play(x, y + 1);
            }
            if (board.isInside(new Position(x, y - 1))) {
                play(x, y - 1);
            }
        } else {
            if (board.getSquareAt(p).isBomb()) {
                revealAllBombs();
            } else {
                board.getSquareAt(p).reveal();
            }

        }
    }

    /**
     * Puts or removes a flag in a given position
     *
     * @param pos the position to put a flag or remove one if there's already a
     * flag there
     */
    public void putFlag(Position pos) {
        if (!board.getSquareAt(pos).hasFlag()) {
            board.getSquareAt(pos).setFlag(true);
        } else {
            board.getSquareAt(pos).setFlag(false);
        }
    }

    /**
     * Increments the score
     */
    public void incScore() {
        score++;
        setChanged();
        notifyObservers();
    }

    /**
     * Gets the score for this game
     *
     * @return the score for this game
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of bombs in the game
     *
     * @return the number of bombs in the game
     */
    public int getNbBomb() {
        return nbBomb;
    }

    /**
     * Generate nbMines random position
     *
     * @param nbMines the number of position that we want
     * @return a List of position
     */
    private List<Position> generateMinePositions(int nbMines) {
        List<Position> pos = new ArrayList<>();
        for (int i = 0; i < nbMines; i++) {
            int x = 0 + (int) (Math.random() * (board.getPlate().length));
            int y = 0 + (int) (Math.random() * (board.getPlate()[0].length));
            Position position = new Position(x, y);
            if (!pos.contains(position)) {
                pos.add(position);
            } else {
                i--;
            }
        }
        return pos;
    }

    /**
     * Reveals all the bombs in the plate
     */
    private void revealAllBombs() {
        MinePos.forEach((MinePo) -> {
            board.getSquareAt(MinePo).reveal();
        });
    }

    /**
     * Get the board
     *
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }

}
