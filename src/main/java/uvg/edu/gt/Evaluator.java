
package uvg.edu.gt;

import java.util.ArrayList;
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
     * Evalua un ArrayList de tokens para ejecutar instrucciones
     * @param tokenList la lista de Tokens por ejecutar
     * @return un objeto, el output puede ser de tipo string, int, double, entre otros
     */
    public Object eval(ArrayList<String> tokenList){
        //Toma una "palabra clave" que es el token que sigue inmediatamente al parentesdis abierto
        String keyword = tokenList.get(1);
        //Crea un Array de simbolos aritmeticos, se deben agregar todavia diferentes "palabras clave" para comparar
        String[] arithmeticSymbols = {"+", "-", "*", "/"};
        if (contains(keyword, arithmeticSymbols)){
            /**
             * Recorre el tokenList, evalua la funcion (pendiente) y al encontrar un parentesis abierto se llama
             * a si mismo sobre un nuevo tokenList partiendo del parentesis abierto hasta el siguiente parentesis
             * valido. Esto permite evaluar variables, funciones y operaciones mas complejas dentro del programa.
             */
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
        return null;
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
