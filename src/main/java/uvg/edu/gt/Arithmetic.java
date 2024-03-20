package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;

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
        List<String> subList = input.subList(2, input.size() - 1);
        ArrayList<String> operatorList = new ArrayList<>(subList);
        switch (operator) {
            case "+":
                double sumCount = 0;
                for (int i = 0; i < operatorList.size(); i++) {
                    sumCount += Double.parseDouble(operatorList.get(i));
                }
                return sumCount;
            case "-":
                double subCount = Double.parseDouble(operatorList.get(0));
                for (int i = 1; i < operatorList.size(); i++){
                    subCount -= Double.parseDouble(operatorList.get(i));
                }
                return subCount;
            case "*":
                double count = 1;
                for (int i = 0; i < operatorList.size(); i++){
                    count *= Double.parseDouble(operatorList.get(i));
                }
                return count;
            case "/":
                double divCount = Double.parseDouble(operatorList.get(0));
                for (int i = 1; i < operatorList.size(); i++){
                    divCount /= Double.parseDouble(operatorList.get(i));
                }
                return divCount;
        }
        return 0;
    }
}
