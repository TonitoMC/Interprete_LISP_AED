package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;

public class EvaluatorTest extends TestCase {

    public void testEval() {
        Evaluator evaluator = new Evaluator();
        ArrayList<String> tokenList = new ArrayList<>();
        tokenList.add("(");
        tokenList.add("*");
        tokenList.add("3");
        tokenList.add("(");
        tokenList.add("+");
        tokenList.add("4");
        tokenList.add("1");
        tokenList.add(")");
        tokenList.add("4");
        tokenList.add(")");

        assertEquals(60.0, evaluator.eval(tokenList));
    }
}