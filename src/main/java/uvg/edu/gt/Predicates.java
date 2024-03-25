package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de evaluar predicados
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class Predicates {
    /**
     * Evalua predicados simples, es decir un parentesis abierto y un solo parentesis cerrando la expresion.
     * @param input un ArrayList con los tokens por evaluar
     * @return true o false, dependiendo de la evaluacion
     */
    public boolean eval(ArrayList<String> input) {
        //Encuentra el operador y los operandos
        String operator = input.get(1);
        List<String> subList = input.subList(2, input.size() - 1);
        ArrayList<String> operatorList = new ArrayList<>(subList);
        switch (operator) {
            case "<":
                for (int i = 1; i < operatorList.size(); i++) {
                    double currentOperator = Double.parseDouble(operatorList.get(i));
                    double previousOperator = Double.parseDouble(operatorList.get(i-1));
                    if (previousOperator >= currentOperator) {
                        return false;
                    }
                }
                return true;
            case ">":
                for (int i = 1; i < operatorList.size(); i++) {
                    double currentOperator = Double.parseDouble(operatorList.get(i));
                    double previousOperator = Double.parseDouble(operatorList.get(i-1));
                    if (previousOperator <= currentOperator) {
                        return false;
                    }
                }
                return true;
            case "=":
                for (int i = 1; i < operatorList.size(); i++) {
                    double currentOperator = Double.parseDouble(operatorList.get(i));
                    double previousOperator = Double.parseDouble(operatorList.get(i-1));
                    if (previousOperator != currentOperator) {
                        return false;
                    }
                }
                return true;
        }
        return false;
    }
}