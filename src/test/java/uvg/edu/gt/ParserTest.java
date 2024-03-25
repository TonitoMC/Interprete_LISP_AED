package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ParserTest extends TestCase {

    public void testTokenize() {
        Parser parser = new Parser();
        String text = "(text1 (text2 text3 (text5)))";
        ArrayList<String> expectedExpressions = new ArrayList<String>();
        expectedExpressions.add("(");
        expectedExpressions.add("text1");
        expectedExpressions.add("(");
        expectedExpressions.add("text2");
        expectedExpressions.add("text3");
        expectedExpressions.add("(");
        expectedExpressions.add("text5");
        expectedExpressions.add(")");
        expectedExpressions.add(")");
        expectedExpressions.add(")");
        ArrayList<String> actualExpressions = parser.tokenize(text);
        assertEquals(expectedExpressions.size(), actualExpressions.size());
        for (int i = 0; i < expectedExpressions.size(); i++){
            assertEquals(expectedExpressions.get(i), actualExpressions.get(i));
        }
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