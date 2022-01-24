import java.util.*;

public class BalancedStrategy extends Strategy {

  // public ArrayList<String> operationsLog;

    @Override
    public LinkedHashMap<Integer, Integer> giveMoney(LinkedHashMap<Integer, Integer> billsBox, int amount) {
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<>(billsBox);
        int change = amount;
        LinkedHashMap<Integer, Integer> result = null;
        //   boolean stopper = true;
        while (true) {
            int copyOfChange = change;

            for (Map.Entry<Integer, Integer> entry : billsBoxCopy.entrySet()) {
                int nominal = entry.getKey();
                //  int billsAtATM = entry.getValue();
                if (change > nominal) {
                    change = change - nominal;
                    billsBoxCopy.put(nominal, (entry.getValue() - 1));
                }
            }
            if (change == 0) {
                System.out.println(makeGiveReport(billsBox, billsBoxCopy));
                result = billsBoxCopy;
                break;
            }
            if (change == copyOfChange) {
                System.out.println("There are no enough Bills !");
                result = billsBox;
                break;
            }
        }
        return result;
    }
}
