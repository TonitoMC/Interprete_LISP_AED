package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;

public class ArithmeticTest extends TestCase {

    public void testEval() {
        Arithmetic arithmetic = new Arithmetic();
        ArrayList<String> tokenList = new ArrayList<>();
        tokenList.add("(");
        tokenList.add("+");
        tokenList.add("1");
        tokenList.add("2");
        tokenList.add("3");
        tokenList.add(")");
        assertEquals(6.0, arithmetic.eval(tokenList));
    }
}