package Model;

import java.util.Objects;
import java.util.Observable;

/**
 * Represents a Square of the plate
 *
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Square extends Observable {

    private boolean hidden;
    private boolean flag;
    private State state;
    private int value;

    /**
     * Constructs a default square with no state
     */
    public Square() {
        hidden = true;
        flag = false;
        state = State.VOID;
        value = 0;
    }

    /**
     * Reveals the hidden Square
     */
    public void reveal() {
        if (!flag) {
            this.hidden = false;
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Changes the state of the Square
     *
     * @param state The new state of the Square
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Checks if the Square is Hidden
     *
     * @return true if the square is not revealed
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Checks if the square contains a flag
     *
     * @return true if there's a flag in this square
     */
    public boolean hasFlag() {
        return flag == true;
    }

    /**
     * Sets the flag attribute to the value of the parameter
     *
     * @param flag the new value for the attribute flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the square contains a bomb
     *
     * @return true if it does
     */
    public boolean isBomb() {
        return state == State.BOMB;
    }

    /**
     * Checks if the state of the square is a number
     *
     * @return true if it is
     */
    public boolean isNumber() {
        return state == State.NUMBER;
    }

    /**
     * Checks if the state of the square is void
     *
     * @return true if it is void
     */
    public boolean isVoid() {
        return state == State.VOID;
    }

    /**
     * Get the number of mines around the square
     *
     * @return the number of bombs around the square
     */
    public int getValue() {
        return value;
    }

    /**
     * Increment by one the value of the Square
     */
    void incValue() {
        if (state == State.NUMBER) {
            value++;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.hidden ? 1 : 0);
        hash = 79 * hash + (this.flag ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + this.value;
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
        final Square other = (Square) obj;
        if (this.hidden != other.hidden) {
            return false;
        }
        if (this.flag != other.flag) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }
    
    

}
