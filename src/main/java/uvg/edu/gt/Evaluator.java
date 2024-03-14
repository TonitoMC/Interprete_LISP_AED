
package uvg.edu.gt;

import java.util.ArrayList;
import java.util.Objects;

public class Evaluator {
    private Arithmetic arithmetic;
    private DataManager dataManager;
    public Evaluator(){
        arithmetic = new Arithmetic();
        dataManager = new DataManager();
    }
    public Object eval(ArrayList<String> tokenList){
        String keyword = tokenList.get(1);
        int startingPoint = 0;
        String[] arithmeticSymbols = {"+", "-", "*", "/"};
        if (contains(keyword, arithmeticSymbols)){
            for (int i = 1; i < tokenList.size(); i++){
                String currentToken = tokenList.get(i);
                if (currentToken.equals("(")){
                    int firstIndex = i;
                    int lastIndex = findClosingParenthesis(tokenList,i);
                    double operationValue = (double) eval((ArrayList<String>) tokenList.subList(firstIndex, lastIndex));
                    tokenList.subList(i, findClosingParenthesis(tokenList, i)).clear();
                    tokenList.add(i, String.valueOf(operationValue));
                    i--;
                }
            }
        }
        for (int i = 1; i < tokenList.size() - 1; i++){
            String currentToken = tokenList.get(i-1);
            String nextToken = tokenList.get(i);
            if (currentToken.equals("(")){

            }
            //Identify the type of token
            //If it's an arithmetic operator
            if (contains(keyword, arithmeticSymbols)){

            } else if (dataManager.hasVariable)
        }
    }
    private int findClosingParenthesis(ArrayList<String> tokenList, int startIndex){
        int openParenCount = 1;
        for (int i = startIndex + 1; i < tokenList.size(); i++){
            String token = tokenList.get(i);
            if (token.equals("(")){
                openParenCount++;
            } else if (token.equals(")")){
                openParenCount--;
            } if (openParenCount == 0){
                return i;
            }
        }
        return openParenCount;
    }
    public String getVar(){

    }
    public boolean contains(String p, String[] array){
        for (String x : array){
            if (Objects.equals(x, p)){
                return true;
            }
        }
        return false;
    }
}
