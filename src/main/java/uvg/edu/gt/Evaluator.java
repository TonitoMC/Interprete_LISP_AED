
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
    public Evaluator(){
        arithmetic = new Arithmetic();
        dataManager = new DataManager();
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
                    double operationValue = (double) eval(subExpression);
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
        else if (keyword.equals("DEFUN")){
            //Agrega una funcion al data manager, se compone por nombre e instrucciones.
            ArrayList<String> instructionList = new ArrayList<String>();
            for (int i = 3; i < tokenList.size()-3; i++)
                instructionList.add(tokenList.get(i));
            dataManager.newFunction(tokenList.get(2),instructionList);
        } else if (keyword.equals("COND")){
            //Encuentra las condicionales, las evalua y ejecuta las acciones en caso de ser verdaderas, pendiente
            //agregarlos a un loop para que se puedan ejecutar la cantidad de condicionales necesarias, por el momento
            //Unicamente evalua 1.
            int lastIndex = findClosingParenthesis(tokenList, 4);
            boolean conditionValue = (boolean) eval((ArrayList<String>) tokenList.subList(4, lastIndex));
            if (conditionValue){
                int lastIndexAction = findClosingParenthesis(tokenList, lastIndex + 1);
                eval((ArrayList<String>) tokenList.subList(lastIndex + 1, lastIndexAction));
            }
        } else if (keyword.equals("SETQ")){

        } else if (isFunctionCall(keyword)){
            ArrayList<String> functionCallInstrucions = dataManager.getFunction(keyword);
            int paramEndIndex = findClosingParenthesis(functionCallInstrucions, 0);
            List<String> subList = functionCallInstrucions.subList(1, paramEndIndex - 1);
            ArrayList<String> params = new ArrayList<>(subList);
            functionCallInstrucions.subList(0, paramEndIndex).clear();
            for (String parameter : params){
                for (int i = 0; i < functionCallInstrucions.size(); i++){
                    String instruction = functionCallInstrucions.get(i);
                    if (parameter.equals(instruction)){
                        functionCallInstrucions.set(i, parameter);
                    }
                }
            }  return eval(functionCallInstrucions);
        }
        /**
         * Si no coincide con alguna de las palabras "clave" es una funcion o una variable, falta implementar
         * estos ultimos casos. La evaluacion de funciones y variables debe tomar precedencia antes de los demas calculos
         * para "limpiar" el input y que las demas partes del programa funcionen de manera correcta
         */
        return null;
    }
    private boolean isFunctionCall(String functionName){
        return dataManager.hasFunction(functionName);
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
