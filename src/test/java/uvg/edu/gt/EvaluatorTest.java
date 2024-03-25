package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class EvaluatorTest extends TestCase {

    public void testEval() {
        Evaluator evaluator = new Evaluator();
        String[] defunArray = {"(", "defun", "sumar-tres-numeros", "(", "a", "b", "c", ")", "(", "+", "a", "b", "c", ")", ")"};
        ArrayList<String> defunList = new ArrayList<>(Arrays.asList(defunArray));
        String[] callFuncArray = {"(","sumar-tres-numeros", "1", "2", "3", ")"};
        ArrayList<String> callFuncList = new ArrayList<>(Arrays.asList(callFuncArray));
        evaluator.eval(defunList);
        assertTrue(evaluator.isFunctionCall("sumar-tres-numeros"));
        assertEquals(6.0, evaluator.eval(callFuncList));

        String[] condArray = {"(", "COND", "(", "(", "<", "1", "2", ")", "(", "+", "1", "2", ")", ")", ")"};
        ArrayList<String> condList = new ArrayList<>(Arrays.asList(condArray));
        assertEquals(3.0, evaluator.eval(condList));

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