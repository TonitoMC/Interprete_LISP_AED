package uvg.edu.gt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase se encarga de manejar las variables y las funciones dentro del programa
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class DataManager {
    private HashMap<String, Object> variables;
    private ArrayList<Function> functions;
    public DataManager(){
        variables = new HashMap<>();
        functions = new ArrayList<>();
    }
    /**
     * Crea una nueva funcion
     * @param parameters los parametros de la funcion
     * @param name el nombre de la funcion
     * @param instructions las instrucciones por ejecutar de la funcion (un tokenList)
     */
    public void newFunction(ArrayList<String> parameters, String name, ArrayList<String> instructions){
        Function newFunction = new Function(parameters, name, instructions);
        functions.add(newFunction);
    }
    /**
     * Crea una nueva variable
     * @param name el nombre de la variable, con la que se accede a su valor
     * @param value el valor de la variable
     */
    public void newVariable(String name, Object value){
        variables.put(name,value);
    }

    /**
     * Regresa el valor de una variable basado en el nombre
     * @param name el nombre / llave de la variable
     * @return el objeto / value de la variable
     */
    public Object getVariable(String name){
        return variables.get(name);
    }

    /**
     * Verifica si existe una variable
     * @param name El nombre de la variable
     * @return True si existe, False de lo contrario
     */
    public boolean hasVariable(String name){
        return variables.containsKey(name);
    }

    /**
     * Regresa una funcion basada en el nombre
     * @param name el nombre de la funcion
     * @return la funcion
     */
    public Function getFunction(String name){
        for(int i = 0; i < functions.size(); i++){
            String functionName = functions.get(i).getName();
            if (functionName.equals(name)){
                return functions.get(i);
            }
        }
        return null;
    }
}
