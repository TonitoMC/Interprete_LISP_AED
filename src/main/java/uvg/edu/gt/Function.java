package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
/**
 * Esta clase se utiliza para manejar los datos relacionados a funciones
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
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
     * Getter para el nombre de la funcion
     * @return el nombre de la funcion
     */
    public String getName(){
        return name;
    }

    /**
     * Retorna la cantidad de parametros que toma
     * @return la cantidad de parametros que toma
     */
    public int getParameterSize(){
        return parameters.size();
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
