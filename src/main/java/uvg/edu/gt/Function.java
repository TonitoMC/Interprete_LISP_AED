package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Function {
    private List<String> parameters;
    private String name;
    private List<String> instructions;
    public Function(List<String> parameters, String name, List<String> instructions){
        this.parameters = parameters;
        this.name = name;
        this.instructions = instructions;
    }

    /**
     * Este metodo regresa un ArrayList con las instrucciones por ejecutar, busca los parametros y los reemplaza
     * con los parametros de input dentro de la funcion.
     * @param inputParameters los parametros con los que se desea evaluar la funcion
     * @return la lista de instrucciones con los parametros reemplazados por inputParameters
     */
    public ArrayList<String> evalFunction(List<String> inputParameters){
        HashMap<String, String> parameterMap = new HashMap<>();
        ArrayList<String> modifiedInstructions = new ArrayList<String>(instructions);
        for (int i = 0; i < parameters.size(); i++){
            parameterMap.put(parameters.get(i), inputParameters.get(i));
        }
        for (int i = 0; i < modifiedInstructions.size(); i++){
            String instruction = modifiedInstructions.get(i);
            if (parameterMap.containsKey(instruction)){
                modifiedInstructions.set(i, parameterMap.get(instruction));
            }
        }
        return modifiedInstructions;
    }
}
