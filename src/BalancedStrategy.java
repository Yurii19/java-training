import java.util.*;

public class BalancedStrategy extends Strategy {

    ArrayList<String> operationsLog;

    @Override
    public LinkedHashMap<Integer, Integer> giveMoney(LinkedHashMap<Integer, Integer> billsBox, int amount) {
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<>(billsBox);
        int change = amount;
        LinkedHashMap<Integer, Integer> result = null;
        boolean stopper = true;
        while (stopper) {
            int copyOfChange = change;

            for (Map.Entry<Integer, Integer> entry : billsBoxCopy.entrySet()) {
                int nominal = entry.getKey();
                int billsAtATM = entry.getValue();
                if (change > nominal) {
                    change = change - nominal;
                    billsBoxCopy.put(nominal, (entry.getValue() - 1));
                }
            }
            if (change == 0) {
                System.out.println(makeGiveReport(billsBox, billsBoxCopy));
                stopper = false;
                result = billsBoxCopy;
            }
            if (change == copyOfChange) {
                stopper = false;
                System.out.println("There are no enough Bills !");
                result = billsBox;
            }
        }
        return result;
    }
}
