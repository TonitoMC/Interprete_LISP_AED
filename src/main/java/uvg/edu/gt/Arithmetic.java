package uvg.edu.gt;

import java.util.ArrayList;
/**
 * Esta clase se encarga de evaluar operaciones aritmeticas, unicamente toma operaciones simplificadas
 * donde no hay operaciones dentro de otras.
 * @author Jose Merida - 201105
 * @author Adrian Lopez - 21357
 * @version 1.0
 * @since 13-03-2024
 */
public class Arithmetic {
    public double eval(ArrayList<String> input) {
        String operator = input.get(1);
        ArrayList<String> operatorList = (ArrayList<String>) input.subList(2, input.size() - 1);
        switch (operator) {
            case "+":
                double sumCount = 0;
                for (int i = 0; i < operatorList.size(); i++) {
                    sumCount += Double.parseDouble(operatorList.get(i));
                }
                return sumCount;
            case "-":
                break;
            case "*":
                break;
            case "/":
                break;
            //Pendiente implementar demas funciones, al igual que otros operadores aritmeticos
        }
        return 0;
    }
}
