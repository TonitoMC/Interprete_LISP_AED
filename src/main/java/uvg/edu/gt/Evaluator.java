
package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Esta clase se encarga de evaluar los inputs tokenizados por Parser
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class Evaluator {
    private Arithmetic arithmetic;
    private DataManager dataManager;
    private Predicates pred;
    public Evaluator(){
        arithmetic = new Arithmetic();
        dataManager = new DataManager();
        pred = new Predicates();
    }
    /**
     * Evalua un ArrayList de tokens para ejecutar instrucciones (pendiente agregar returns finales, terminar
     * de implementar condicionales y definicion de funciones. Tambien pendiente implementar los calculos en si
     * en demas clases).
     * @param tokenList la lista de Tokens por ejecutar
     * @return un objeto, el output puede ser de tipo string, int, double, entre otros
     */
    public Object eval(ArrayList<String> tokenList){
        //Toma una "palabra clave" que es el token que sigue inmediatamente al parentesdis abierto
        String keyword = tokenList.get(1);
        //Crea un Array de simbolos aritmeticos, se deben agregar todavia diferentes "palabras clave" para comparar
        String[] arithmeticSymbols = {"+", "-", "*", "/"};
        String[] predicates = {"ATOM","EQUAL", "<", ">"};
        for (int i = 1; i < tokenList.size(); i++){
            String currentToken = tokenList.get(i);
            String previousToken = tokenList.get(i-1);
            if (isVariable(currentToken) && !previousToken.equals("QUOTE")){
                tokenList.set(i, (String) dataManager.getVariable(currentToken));
            }
        }
        if (contains(keyword, arithmeticSymbols)){
            /**
             * Recorre el tokenList, evalua la funcion y al encontrar un parentesis abierto se llama
             * a si mismo sobre un nuevo tokenList partiendo del parentesis abierto hasta el siguiente parentesis
             * valido. Esto permite evaluar variables, funciones y operaciones mas complejas dentro del programa.
             */
            for (int i = 1; i < tokenList.size(); i++){
                String currentToken = tokenList.get(i);
                if (currentToken.equals("(")){
                    int firstIndex = i;
                    int lastIndex = findClosingParenthesis(tokenList,i) + 1;
                    List<String> subList = tokenList.subList(firstIndex, lastIndex);
                    ArrayList<String> subExpression = new ArrayList<>(subList);
                    Object operationValue =  eval(subExpression);
                    tokenList.subList(firstIndex, lastIndex).clear();
                    tokenList.add(firstIndex, String.valueOf(operationValue));
                    i--;
                }
            }
            return arithmetic.eval(tokenList);
        } else if (contains(keyword, predicates)){
            for (int i = 1; i < tokenList.size(); i++){
                String currentToken = tokenList.get(i);
                if (currentToken.equals("(")){
                    int firstIndex = i;
                    int lastIndex = findClosingParenthesis(tokenList,i) + 1;
                    List<String> subList = tokenList.subList(firstIndex, lastIndex);
                    ArrayList<String> subExpression = new ArrayList<>(subList);
                    double operationValue = (double) eval(subExpression);
                    tokenList.subList(firstIndex, lastIndex).clear();
                    tokenList.add(firstIndex, String.valueOf(operationValue));
                    i--;
                }
            }
        }
        else if (keyword.equals("defun")){
            String functionName = tokenList.get(2);
            ArrayList<String> parameterList = new ArrayList<>();
            ArrayList<String> instructionList = new ArrayList<>();
            int lastIndex = findClosingParenthesis(tokenList, 3);
            for (int i = 4; i < lastIndex; i++){
                parameterList.add(tokenList.get(i));
            }
            int firstIndex = lastIndex + 1;
            for (int i = firstIndex; i < tokenList.size() - 1; i++){
                instructionList.add(tokenList.get(i));
            }
            dataManager.newFunction(parameterList, functionName, instructionList);
        } else if (keyword.equals("COND")){
            for (int i = 2; i < tokenList.size();){
                int firstIndex = i+1;
                if (tokenList.get(firstIndex).equals("(")) {
                    int lastIndex = findClosingParenthesis(tokenList, firstIndex) + 1;
                    List<String> subList = tokenList.subList(firstIndex, lastIndex);
                    ArrayList<String> subExpression = new ArrayList<>(subList);
                    int firstIndexCond = lastIndex;
                    int lastIndexCond = findClosingParenthesis(tokenList, firstIndexCond) + 1;
                    List<String> subListCond = tokenList.subList(firstIndexCond, lastIndexCond);
                    ArrayList<String> subExpressionCond = new ArrayList<>(subListCond);
                    if (pred.eval(subExpression)) {
                        return eval(subExpressionCond);
                    } else {
                        i = lastIndexCond + 1;
                    }
                } else {
                    int firstIndexT = firstIndex + 1;
                    int lastIndexT = findClosingParenthesis(tokenList, firstIndexT) + 1;
                    List<String> subListT = tokenList.subList(firstIndexT, lastIndexT);
                    ArrayList<String> subExpressionT = new ArrayList<>(subListT);
                    return eval(subExpressionT);
                }
            }
        } else if (keyword.equals("SETQ")){
            String variableName = tokenList.get(2);
            String variableValue = tokenList.get(3);
            dataManager.newVariable(variableName, variableValue);
        } else if (isFunctionCall(keyword)){
            Function currentFunction = dataManager.getFunction(keyword);
            int paramSize = currentFunction.getParameterSize();
            for (int i = 2; i < tokenList.size(); i++){
                String currentToken = tokenList.get(i);
                if (currentToken.equals("(")){
                    int firstIndex = i;
                    int lastIndex = findClosingParenthesis(tokenList,i) + 1;
                    List<String> subList = tokenList.subList(firstIndex, lastIndex);
                    ArrayList<String> subExpression = new ArrayList<>(subList);
                    double operationValue = (double) eval(subExpression);
                    tokenList.subList(firstIndex, lastIndex).clear();
                    tokenList.add(firstIndex, String.valueOf(operationValue));
                    i--;
                }
            }
            ArrayList<String> inputParameterList = new ArrayList<>();
            for (int i = 2; i < paramSize + 2; i++){
                inputParameterList.add(tokenList.get(i));
            }
            ArrayList<String> mod = currentFunction.evalFunction(inputParameterList);
            return eval(mod);
        } else if (keyword.equals("print")){
            if (tokenList.get(2).equals("(")){
                int firstIndex = 2;
                int lastIndex = findClosingParenthesis(tokenList, firstIndex) + 1;
                List<String> subList = tokenList.subList(firstIndex, lastIndex);
                ArrayList<String> subExpression = new ArrayList<>(subList);
                System.out.println(eval(subExpression));
            } else {
                System.out.println(tokenList.get(2));
            }
        } else{
            return keyword;
        }
        /**
         * Si no coincide con alguna de las palabras "clave" es una funcion o una variable, falta implementar
         * estos ultimos casos. La evaluacion de funciones y variables debe tomar precedencia antes de los demas calculos
         * para "limpiar" el input y que las demas partes del programa funcionen de manera correcta
         */
        return null;
    }
    public boolean isFunctionCall(String functionName){
        return dataManager.getFunction(functionName) != null;
    }
    private boolean isVariable(String methodName){
        return dataManager.hasVariable(methodName);
    }
    /**
     * Este metodo se utiliza para obtener una funcion o un metodo dde la clase DataManager, normalmente para interpretar
     * tokens que no se han contemplado dentro del programa.
     * @param toGet el nombre o "key" bajo el cual se encuentra almacenada la variable o funcion
     * @return la funcion evaluada o la variable utilizada
     */
    private Object getUnknown(String toGet){
        return 1;
    }

    /**
     * Este metodo se utiliza para encontrar el siguiente parentesis valido dentro de un ArrayList, se utiliza
     * unicamente dentro del mismo Evaluator para determinar el intervalo de la siguiente funcion a evaluar
     * @param tokenList el ArrayList que se desea recorrer
     * @param startIndex el indice inicial
     * @return
     */
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

    /**
     * Metodo para verificar si un caracter se encuentra dentro de un array
     * @param p el caracter que se desea verificar
     * @param array el array en el que se verifica se encuentra el caracter
     * @return true si se encuentra el caracter, false de lo contrario
     */
    public boolean contains(String p, String[] array){
        for (String x : array){
            if (Objects.equals(x, p)){
                return true;
            }
        }
        return false;
    }
}
