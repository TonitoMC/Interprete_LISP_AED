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
    private ArrayList<Function> functions;
    public DataManager(){
        variables = new HashMap<>();
        functions = new ArrayList<>();
    }

    public void newFunction(ArrayList<String> parameters, String name, ArrayList<String> instructions){
        Function newFunction = new Function(parameters, name, instructions);
        functions.add(newFunction);
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
    public boolean hasFunction(String name){
        boolean isInFunctions = false;
        for(int i =  0; i < functions.size(); i++){
            String functionName = functions.get(i).getName();
            if (functionName.equals(name)){
                isInFunctions = true;
            }
        }
        return isInFunctions;
    }

    public Function getFunction(String name){
        boolean isInFunctions = false;
        for(int i = 0; i < functions.size(); i++){
            String functionName = functions.get(i).getName();
            if (functionName.equals(name)){
                return functions.get(i);
            }
        }
        return null;
    }
    public void removeVariable(String name){
        variables.remove(name);
    }
}
