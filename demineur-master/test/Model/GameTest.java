package Model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author chetouani
 */
public class GameTest {
    
    public GameTest() {
    }

    /**
     * Test of getNbline method, of class Game.
     */
    @Test
    public void testGetNbline() {
        System.out.println("getNbline");
        Game instance = new Game(4, 7, 3);
        int expResult = 4;
        int result = instance.getNbline();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbColumn method, of class Game.
     */
    @Test
    public void testGetNbColumn() {
        System.out.println("getNbColumn");
        Game instance = new Game(4, 7, 3);
        int expResult = 7;
        int result = instance.getNbColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbBomb method, of class Game.
     */
    @Test
    public void testGetNbBomb() {
        System.out.println("getNbBomb");
        Game instance = new Game(4, 7, 3);
        int expResult = 3;
        int result = instance.getNbBomb();
        assertEquals(expResult, result);
    }

    /**
     * Test of init method, of class Game.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Game instance = new Game(4, 7, 3);
        instance.init();
    }

    /**
     * Test of isOverByBomb method, of class Game.
     */
    @Test
    public void testIsOverByBomb() {
        System.out.println("isOverByBomb");
        Game instance = new Game(4, 7, 3);
        instance.init();
        boolean expResult = false;
        boolean result = instance.isOverByBomb();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOverByFlag method, of class Game.
     */
    @Test
    public void testIsOverByFlag() {
        System.out.println("isOverByFlag");
        Game instance = new Game(4, 7, 3);
        instance.init();        
        boolean expResult = false;
        boolean result = instance.isOverByFlag();
        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlay00() {
        System.out.println("play00");
        int x = 1;
        int y = 4;
        Game instance = new Game(4, 7, 3);
        instance.play(x, y);
        boolean result = instance.getBoard().getSquareAt(new Position(x, y)).isHidden();
        assertFalse(result);
    }


    /**
     * Test of putFlag method, of class Game.
     */
    @Test
    public void testPutFlag00() {
        System.out.println("putFlag00");
        Position pos = new Position(1, 4);
        Game instance = new Game(4, 7, 3);
        instance.putFlag(pos);
        boolean result = instance.getBoard().getSquareAt(new Position(0, 0)).hasFlag();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of putFlag method, of class Game.
     */
    @Test
    public void testPutFlag01() {
        System.out.println("putFlag01");
        Position pos = new Position(1, 4);
        Game instance = new Game(4, 7, 3);
        instance.putFlag(pos);
        boolean result = instance.getBoard().getSquareAt(new Position(3, 4)).hasFlag();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of incScore method, of class Game.
     */
    @Test
    public void testIncScore() {
        System.out.println("incScore");
        Game instance = new Game(4, 7, 3);
        instance.incScore();
        int score = 1;
        assertEquals(score, instance.getScore());
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Game instance = new Game(4, 7, 3);
        int expResult = 3;
        instance.incScore();
        instance.incScore();
        instance.incScore();
        int result = instance.getScore();
        assertEquals(expResult, result);
    }
    
}