
package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Esta clase se encarga de leer el texto de un archivo que contiene el codigo en lisp, separarlo en diferentes
 * instrucciones y tokenizarlas para que puedan ser interpretadas por un Evaluator
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class Parser {
    private Evaluator evaluator;
    public Parser(){
        evaluator = new Evaluator();
    }
    public void runProgram(){
        String fileText = readFile();
        ArrayList<String> instructions = separate(fileText);
        for (String instruction : instructions){
            ArrayList<String> tokenList = tokenize(instruction);
            evaluator.eval(tokenList);
        }
    }
    /**
     * Esta funcion separa una instruccion (con parentesis validos) dentro de lisp
     * @param input la instruccion por tokenizar
     * @return un ArrayList de Strings con la instruccion tokenizada
     */
    public ArrayList<String> tokenize(String input){
        /**
         * Crea un nuevo ArrayList para los Tokens, agrega los caracteres especiales a un token especifico
         * y luego separa palabras por espacios en blanco.
         */
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
    /**
     * Metodo para verificar si un caracter se encuentra dentro de un array
     * @param p el caracter que se desea verificar
     * @param array el array en el que se verifica se encuentra el caracter
     * @return true si se encuentra el caracter, false de lo contrario
     */
    public boolean contains(char p, char[] array){
        for (char x : array){
            if (x == p){
                return true;
            }
        }
        return false;
    }

    /**
     * Separa el texto leido del archivo dependiendo de parentesis abiertos / cerrados
     * @param text el texto para separar
     * @return un arraylist conteniendo cada string con un parentesis abierto / cerrado valido
     */
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

    /**
     * Metodo para leer el archivo que contiene el programa en Lisp
     * @return un string con el texto del archivo
     */
    private String readFile(){
        String line;
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("LispScript.txt"))){
            while ((line = br.readLine()) != null){
                output.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return output.toString();
    }

}
