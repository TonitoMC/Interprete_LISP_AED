package uvg.edu.gt;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Esta clase se encarga de manejar las variables y las funciones dentro del programa
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class DataManager {
    private HashMap<String, Object> variables;
    private HashMap<String, ArrayList<String>> functions;
    public DataManager(){
        variables = new HashMap<>();
        functions = new HashMap<>();
    }

    public void newFunction(String name, ArrayList<String> instructions){
        functions.put(name,instructions);
    }

    public void setVariable(String name, Object value){
        variables.put(name,value);
    }

    public Object getVariable(String name){
        return variables.get(name);
    }
    public boolean hasVariable(String name){
        return variables.containsKey(name);
    }
    public void removeVariable(String name){
        variables.remove(name);
    }
}
