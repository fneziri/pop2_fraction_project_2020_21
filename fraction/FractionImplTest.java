package fraction;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionImplTest {
    @Test
    public void normalise(){
        //the method to find the greatest common divisor is included in the normalise method
        //therefore if the fractions normalise successfully we implicitly test whether the gcd method works correctly
        FractionImpl f1 = new FractionImpl(14,2);
        assertEquals("7",f1.toString());

        FractionImpl f2 = new FractionImpl("-6/-4");
        assertEquals("3/2",f2.toString());

        FractionImpl f3 = new FractionImpl(4,-10);
        assertEquals("-2/5",f3.toString());
    }

    @Test
    public void fractionInt() {
        FractionImpl f1 = new FractionImpl(4, 5);
        assertEquals("4/5", f1.toString());

        FractionImpl f2 = new FractionImpl(2, -5);
        assertEquals("-2/5", f2.toString());

        try{
            FractionImpl f3 = new FractionImpl(-3, 0);
            fail();
        }
        catch(ArithmeticException e){
            assertEquals("Denominator cannot be zero", e.getMessage());
        }
    }

    @Test
    public void fractionWhole(){
        FractionImpl f1 = new FractionImpl(3);
        assertEquals("3",f1.toString());

        FractionImpl f2 = new FractionImpl(-4);
        assertEquals("-4",f2.toString());
    }

    @Test
    public void fractionString(){
        FractionImpl f1 = new FractionImpl("4/5");
        assertEquals("4/5",f1.toString());

        FractionImpl f2 = new FractionImpl("2/-5");
        assertEquals("-2/5",f2.toString());

        try{
            FractionImpl f3 = new FractionImpl("-3/0");
            fail();
        }
        catch(ArithmeticException e){
            assertEquals("Denominator cannot be zero", e.getMessage());
        }

        try{
            FractionImpl f4 = new FractionImpl("1 4/2");
            fail();
        }
        catch(NumberFormatException e){
            assertEquals("Please enter a fraction or whole number in the correct format", e.getMessage());
        }
    }

    @Test
    public void add() {
        FractionImpl f1 = new FractionImpl("1/2");
        FractionImpl f2 = new FractionImpl(1);
        assertEquals("3/2",f1.add(f2).toString());

        FractionImpl f3 = new FractionImpl(-3,4);
        assertEquals("-1/4",f3.add(f1).toString());
        assertEquals("1/4",f3.add(f2).toString());

        FractionImpl f4 = new FractionImpl(-3);
        assertEquals("-2",f4.add(f2).toString());
    }

    @Test
    public void subtract() {
        FractionImpl f1 = new FractionImpl("5/6");
        FractionImpl f2 = new FractionImpl(4);
        assertEquals("-19/6",f1.subtract(f2).toString());

        FractionImpl f3 = new FractionImpl(-1,2);
        assertEquals("-4/3",f3.subtract(f1).toString());
        assertEquals("-9/2",f3.subtract(f2).toString());

        FractionImpl f4 = new FractionImpl(-3);
        assertEquals("-23/6",f4.subtract(f1).toString());
        assertEquals("-7",f4.subtract(f2).toString());
        assertEquals("7",f2.subtract(f4).toString());
    }

    @Test
    public void multiply() {
        FractionImpl f1 = new FractionImpl("4/7");
        FractionImpl f2 = new FractionImpl(5);
        assertEquals("20/7",f1.multiply(f2).toString());

        FractionImpl f3 = new FractionImpl(-6,4);
        assertEquals("-6/7",f3.multiply(f1).toString());
        assertEquals("-15/2",f3.multiply(f2).toString());

        FractionImpl f4 = new FractionImpl(-2);
        assertEquals("-8/7",f4.multiply(f1).toString());
        assertEquals("-10",f4.multiply(f2).toString());
        assertEquals("3",f4.multiply(f3).toString());
    }

    @Test
    public void divide() {
        FractionImpl f1 = new FractionImpl("3/5");
        FractionImpl f2 = new FractionImpl(3);
        assertEquals("1/5",f1.divide(f2).toString());

        FractionImpl f3 = new FractionImpl(-3,2);
        assertEquals("-5/2",f3.divide(f1).toString());
        assertEquals("-1/2",f3.divide(f2).toString());

        FractionImpl f4 = new FractionImpl(-4);
        assertEquals("-20/3",f4.divide(f1).toString());
        assertEquals("-4/3",f4.divide(f2).toString());
        assertEquals("8/3",f4.divide(f3).toString());
    }

    @Test
    public void abs() {
        FractionImpl f1 = new FractionImpl("1/2");
        assertEquals("1/2", f1.abs().toString());

        FractionImpl f2 = new FractionImpl(5,-4);
        assertEquals("5/4", f2.abs().toString());

        FractionImpl f3 = new FractionImpl(-3);
        assertEquals("3", f3.abs().toString());

        FractionImpl f4 = new FractionImpl(-1,-3);
        assertEquals("1/3", f4.abs().toString());
    }

    @Test
    public void negate() {
        FractionImpl f1 = new FractionImpl("2/3");
        assertEquals("-2/3", f1.negate().toString());

        FractionImpl f2 = new FractionImpl(6,-2);
        assertEquals("3", f2.negate().toString());

        FractionImpl f3 = new FractionImpl(-2);
        assertEquals("2", f3.negate().toString());

        FractionImpl f4 = new FractionImpl(-1,-3);
        assertEquals("-1/3", f4.negate().toString());
    }

    @Test
    public void testEquals() {
        FractionImpl f1 = new FractionImpl("3/4");
        FractionImpl f2 = new FractionImpl(3,4);
        assertTrue(f1.equals(f2));

        FractionImpl f3 = new FractionImpl(3);
        assertFalse(f3.equals(f1));

        FractionImpl f4 = new FractionImpl(-3,-4);
        assertTrue(f4.equals(f1));
        assertTrue(f4.equals(f2));
    }

    @Test
    public void inverse() {
        FractionImpl f1 = new FractionImpl("1/3");
        assertEquals("3",f1.inverse().toString());

        FractionImpl f2 = new FractionImpl(4);
        assertEquals("1/4",f2.inverse().toString());

        FractionImpl f3 = new FractionImpl(3,5);
        assertEquals("5/3",f3.inverse().toString());

        FractionImpl f4 = new FractionImpl(2,-3);
        assertEquals("-3/2",f4.inverse().toString());
    }

    @Test
    public void compareTo() {
        FractionImpl f1 = new FractionImpl("1/4");
        FractionImpl f2 = new FractionImpl(1,4);
        assertEquals(0,f1.compareTo(f2));

        FractionImpl f3 = new FractionImpl(2);
        assertEquals(1,f3.compareTo(f1));
        assertEquals(-1,f1.compareTo(f3));

    }

    @Test
    public void testToString() {
        FractionImpl f1 = new FractionImpl("1/2");
        assertEquals("1/2",f1.toString());

        FractionImpl f2 = new FractionImpl("-1/2");
        assertEquals("-1/2",f2.toString());

        FractionImpl f3 = new FractionImpl(-3,4);
        assertEquals("-3/4", f3.toString());

        FractionImpl f4 = new FractionImpl(3,-4);
        assertEquals("-3/4", f4.toString());

        FractionImpl f5 = new FractionImpl(-3);
        assertEquals("-3",f5.toString());

        FractionImpl f6 = new FractionImpl(3);
        assertEquals("3",f6.toString());
    }
}