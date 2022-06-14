package Model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author laity
 */
public class PositionTest {
    
    public PositionTest() {
    }

    /**
     * Test of getX method, of class Position.
     */
    @Test
    public void testGetX() {
        Position instance = new Position(67, 42);
        int expResult = 67;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetY() {
        Position instance = new Position(34, 5);
        int expResult = 5;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    @Test
    public void equalsTrueMySelf() {
        Position position1 = new Position(-4, 7);
        assertTrue(position1.equals(position1));
        assertTrue(position1.hashCode() == position1.hashCode());
    }

    @Test
    public void equalsTrue() {
        Position position1 = new Position(4, 7);
        Position position2 = new Position(4, 7);
        assertTrue(position1.equals(position2));
        assertTrue(position1.hashCode() == position2.hashCode());
    }

    @Test
    public void equalsFalseDifferentRow() {
        Position position1 = new Position(4, 7);
        Position position2 = new Position(8, 7);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseDifferentColumn() {
        Position position1 = new Position(2, 7);
        Position position2 = new Position(2, 5);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Position position1 = new Position(2, 96);
        String position2 = "abcd";
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseNull() {
        Position position1 = new Position(2, 96);
        assertFalse(position1.equals(null));
    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNextUp() {
        Direction direction = Direction.UP;
        Position instance = new Position(2,2);
        Position expResult = new Position(1, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNextDown() {
        Direction direction = Direction.DOWN;
        Position instance = new Position(2,2);
        Position expResult = new Position(3, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNextLeft() {
        Direction direction = Direction.LEFT;
        Position instance = new Position(2,2);
        Position expResult = new Position(2, 1);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNextRight() {
        Direction direction = Direction.RIGHT;
        Position instance = new Position(2,2);
        Position expResult = new Position(2, 3);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }

    
}
