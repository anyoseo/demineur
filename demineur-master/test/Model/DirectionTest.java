package Model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author laity
 */
public class DirectionTest {
    
    public DirectionTest() {
    }

    @Test
    public void testGetRowUp() {
        Direction instance = Direction.UP;
        int expResult = -1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRow method, of class Direction.
     */
    @Test
    public void testGetRowDown() {
        Direction instance = Direction.DOWN;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowLeft() {
        Direction instance = Direction.LEFT;
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowRight() {
        Direction instance = Direction.RIGHT;
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRowUpRight() {
        Direction instance = Direction.UP_RIGHT;
        int expResult = -1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRowUpLeft() {
        Direction instance = Direction.UP_LEFT;
        int expResult = -1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRowDownRight() {
        Direction instance = Direction.DOWN_RIGHT;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRowDownLeft() {
        Direction instance = Direction.DOWN_LEFT;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetColumnUpRight() {
        Direction instance = Direction.UP_RIGHT;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetColumnUpLeft() {
        Direction instance = Direction.UP_LEFT;
        int expResult = -1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetColumnDownRight() {
        Direction instance = Direction.DOWN_RIGHT;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetColumnDownLeft() {
        Direction instance = Direction.DOWN_LEFT;
        int expResult = -1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn method, of class Direction.
     */
    @Test
    public void testGetColumnUp() {
        Direction instance = Direction.UP;
        int expResult = 0;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnDown() {
        Direction instance = Direction.DOWN;
        int expResult = 0;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnLeft() {
        Direction instance = Direction.LEFT;
        int expResult = -1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnRight() {
        Direction instance = Direction.RIGHT;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    
}
