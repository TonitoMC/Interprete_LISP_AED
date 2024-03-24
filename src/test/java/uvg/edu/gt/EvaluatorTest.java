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
    }
}