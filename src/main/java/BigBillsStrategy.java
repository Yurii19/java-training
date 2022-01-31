import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class BigBillsStrategy extends Strategy {

    ArrayList<String> operationsLog;

    @Override
    public  LinkedHashMap<Integer, Integer> giveMoney(LinkedHashMap<Integer, Integer> billsBox, int amount) {
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<Integer, Integer>(billsBox);
        int change = amount;

        List<Integer> nominalKeys = new ArrayList(billsBox.keySet());
        Collections.reverse(nominalKeys);

        for (Integer nominal : nominalKeys) {
            int billsAtATM = billsBoxCopy.get(nominal);
            int amountRequiredBills = change / nominal;
            boolean isEnoughBillsAtATM = amountRequiredBills <= billsAtATM;
            if (billsAtATM == 0) {
                continue;
            } else if (isEnoughBillsAtATM) {
                change = change - nominal * amountRequiredBills;
                billsBoxCopy.put(nominal, (billsAtATM - amountRequiredBills));

            } else if (!isEnoughBillsAtATM) {
                change = change - nominal * billsAtATM;
                billsBoxCopy.put(nominal, 0);
            }
        }
        if (change == 0) {
            System.out.println(makeGiveReport(billsBox, billsBoxCopy));
            return billsBoxCopy;
        } else {
            System.out.println("There are no enough Bills !");
            return null;
        }
    }
}
