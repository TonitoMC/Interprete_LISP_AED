package uvg.edu.gt;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private HashMap<String, Object> variables;
    private HashMap<String, ArrayList<String>> functions;
    public DataManager(){
        variables = new HashMap<>();
        functions = new HashMap<>();
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
