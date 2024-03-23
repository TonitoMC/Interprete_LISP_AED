package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FunctionTest extends TestCase {

    public void testEvalFunction() {
        String[] parameterArray = {"a", "b"};
        String[] instructionsArray = {"(", "*", "a", "b", ")"};
        String[] inputParametersArray = {"2", "4"};
        String[] outArray = {"(", "*", "2", "4", ")"};
        ArrayList<String> parameterList = new ArrayList<>(Arrays.asList(parameterArray));
        ArrayList<String> instructionList = new ArrayList<>(Arrays.asList(instructionsArray));
        ArrayList<String> inputParametersList = new ArrayList<>(Arrays.asList(inputParametersArray));
        ArrayList<String> outListTest = new ArrayList<>(Arrays.asList(outArray));

        Function multiplyTwoNumbers = new Function(parameterList, "pepe", instructionList);
        ArrayList<String> outList = multiplyTwoNumbers.evalFunction(inputParametersList);
        assertEquals(outList, outListTest);
    }
}