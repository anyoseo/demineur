package Model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author laity
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    Board board = new Board(10,10);

    /**
     * Test of getPlate method, of class Board.
     */
    @Test(expected = NullPointerException.class)
    public void testGetPlateWhenBoardNull() {
        Board instance = null;
        Square[][] result = instance.getPlate();
    }

    /**
     * Test of getSquareAt method, of class Board.
     */
    @Test
    public void testGetSquareAtWhenInside() {
        Position pos = new Position(5,5);
        Board instance = board;
        Square expResult = new Square();
        Square result = instance.getSquareAt(pos);
        assertTrue(expResult.equals(result));
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetSquareAtWhenNotInside() {
        Position pos = new Position(15,5);
        Board instance = board;
        Square result = instance.getSquareAt(pos);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInsideWhenInside() {
        Position position = new Position(5,5);
        Board instance = board;
        boolean expResult = true;
        boolean result = instance.isInside(position);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsInsideWhenNotInside() {
        Position position = new Position(11,5);
        Board instance = board;
        boolean expResult = false;
        boolean result = instance.isInside(position);
        assertEquals(expResult, result);
    }
}
