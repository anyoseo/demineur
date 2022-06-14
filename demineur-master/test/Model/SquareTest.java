package Model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author laity
 */
public class SquareTest {
    
    public SquareTest() {
    }

    /**
     * Test of reveal method, of class Square.
     */
    @Test
    public void testReveal() {
        Square instance = new Square();
        if(instance.isHidden()) {
            instance.reveal();
            assertFalse(instance.isHidden());
        } else {
            fail();
        }
    }

    /**
     * Test of setState method, of class Square.
     */
    @Test
    public void testSetStateToBomb() {
        State state = State.BOMB;
        Square instance = new Square();
        instance.setState(state);
        assertTrue(instance.isBomb());
    }
    
    @Test
    public void testSetStateToNumber() {
        State state = State.NUMBER;
        Square instance = new Square();
        instance.setState(state);
        assertTrue(instance.isNumber());
    }
    
    @Test
    public void testSetStateToVoid() {
        State state = State.VOID;
        Square instance = new Square();
        instance.setState(state);
        assertTrue(instance.isVoid());
    }

    /**
     * Test of isHidden method, of class Square.
     */
    @Test
    public void testIsHiddenWhenHidden() {
        Square instance = new Square();
        boolean expResult = true;
        boolean result = instance.isHidden();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsHiddenWhenNotHidden() {
        Square instance = new Square();
        instance.reveal();
        boolean expResult = false;
        boolean result = instance.isHidden();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFlag method, of class Square.
     */
    @Test
    public void testHasFlagWhenFalse() {
        Square instance = new Square();
        boolean expResult = false;
        boolean result = instance.hasFlag();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testHasFlagWhenTrue() {
        Square instance = new Square();
        instance.setFlag(true);
        boolean expResult = true;
        boolean result = instance.hasFlag();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBomb method, of class Square.
     */
    @Test
    public void testIsBombWhenFalse() {
        Square instance = new Square();
        boolean expResult = false;
        boolean result = instance.isBomb();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsBombWhenTrue() {
        Square instance = new Square();
        instance.setState(State.BOMB);
        boolean expResult = true;
        boolean result = instance.isBomb();
        assertEquals(expResult, result);
    }

    /**
     * Test of isNumber method, of class Square.
     */
    @Test
    public void testIsNumberWhenFalse() {
        Square instance = new Square();
        boolean expResult = false;
        boolean result = instance.isNumber();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsNumberWhenTrue() {
        Square instance = new Square();
        instance.setState(State.NUMBER);
        boolean expResult = true;
        boolean result = instance.isNumber();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isVoid method, of class Square.
     */
    @Test
    public void testIsVoidWhenFalse() {
        Square instance = new Square();
        instance.setState(State.NUMBER);
        boolean expResult = false;
        boolean result = instance.isVoid();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsVoidWhenTrue() {
        Square instance = new Square();
        boolean expResult = true;
        boolean result = instance.isVoid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Square.
     */
    @Test
    public void testGetValueWhenNumber() {
        Square instance = new Square();
        instance.setState(State.NUMBER);
        instance.incValue();
        int expResult = 1;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetValueWhenNotNumber() {
        Square instance = new Square();
        instance.setState(State.VOID);
        instance.incValue();
        int expResult = 0;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of incValue method, of class Square.
     */
    @Test
    public void testIncValue() {
        System.out.println("incValue");
        Square instance = new Square();
        instance.setState(State.NUMBER);
        instance.incValue();
        assertTrue(instance.getValue() == 1);
    }

    /**
     * Test of equals method, of class Square.
     */
    @Test
    public void testEqualsToSelf() {
        Square instance = new Square();
        Square other = instance;
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsToOtherWhenTrue() {
        Square instance = new Square();
        Square other = new Square();
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsToOtherWhenNull() {
        Square instance = new Square();
        Square other = null;
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsToOtherWhenNotSameClass() {
        Square instance = new Square();
        Square[] other = new Square[1];
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsToOtherWhenDifferent() {
        Square instance = new Square();
        Square other = new Square();
        other.setFlag(true);
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
}
