
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
    public ArrayList<String> tokenize(String input){
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        char[] specialCharacters = {'(',')','+', '-', '*', '/', '<', '>'};
        for (char p : input.toCharArray()){
            if (contains(p, specialCharacters)){
                if (stringBuilder.length() > 0){
                    tokens.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
                tokens.add(String.valueOf(p));
            } else if (p == ' '){
                if (stringBuilder.length() > 0){
                    tokens.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            } else {
                stringBuilder.append(p);
            }
        }
        return tokens;
    }

    public boolean contains(char p, char[] array){
        for (char x : array){
            if (x == p){
                return true;
            }
        }
        return false;
    }
    public ArrayList<String> separate(String text){
        Stack<Character> stack = new Stack<>();
        ArrayList<String> inputs = new ArrayList<>();
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
