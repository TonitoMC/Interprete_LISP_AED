package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class PredicatesTest extends TestCase {

    public void testEval() {
        Predicates pred = new Predicates();
        String[] inputArrInc = {"(", "<", "2", "1", "3", "4", ")"};
        String[] inputArrDec = {"(", ">", "3", "2", "1", ")"};
        String[] inputArrEq = {"(", "=", "1", "1", "1", ")"};
        ArrayList<String> inputListInc = new ArrayList<>(Arrays.asList(inputArrInc));
        ArrayList<String> inputListDec = new ArrayList<>(Arrays.asList(inputArrDec));
        ArrayList<String> inputListEq = new ArrayList<>(Arrays.asList(inputArrEq));
        assertFalse(pred.eval(inputListInc));
        assertTrue(pred.eval(inputListDec));
        assertTrue(pred.eval(inputListEq));
    }
}