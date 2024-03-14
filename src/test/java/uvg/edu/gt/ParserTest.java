package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ParserTest extends TestCase {

    public void testEval() {
    }

    public void testTokenize() {
    }

    public void testSeparate() {
        Parser parser = new Parser();
        String text = "(text1 (text2 text3 (text5))) (text4(text5))";
        ArrayList<String> expectedExpressions = new ArrayList<>();
        expectedExpressions.add("(text1 (text2 text3 (text5)))");
        expectedExpressions.add("(text4(text5))");

        ArrayList<String> actualExpressions = parser.separate(text);

        assertEquals(expectedExpressions.size(), actualExpressions.size());
        for (int i = 0; i < expectedExpressions.size(); i++){
            assertEquals(expectedExpressions.get(i), actualExpressions.get(i));
        }

    }
}