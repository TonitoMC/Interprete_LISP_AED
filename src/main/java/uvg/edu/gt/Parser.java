package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Parser {
    private Evaluator evaluator;
    public Parser(){
        evaluator = new Evaluator();
    }
    public void eval(){
        System.out.println('1');
    }
    public ArrayList<String> separate(String text){
        Stack<Character> stack = new Stack<>();
        ArrayList<String> inputs = new ArrayList<>();
        StringBuilder currentExpression = new StringBuilder();
        int start = 0;
        for (int i = 0; i < text.length(); i++){
            char p = text.charAt(i);
            if (p == '('){
                if (stack.isEmpty()) {
                    start = i;
                    stack.push(p);
                } else {
                    stack.push(p);
                    continue;
                }
            } else if (p == ')'){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            } else if (p == ' '){
                continue;
            }
            if (stack.isEmpty()){
                inputs.add(text.substring(start, i + 1));
            }
        }
        return inputs;
    }
    private String readFile(){
        return "s";
    }

}
