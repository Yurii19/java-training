package hibernate.utils;

import hibernate.models.Atm;

import java.util.*;

public class BigBillsStrategy extends Strategy {

    ArrayList<String> operationsLog;
    private final LinkedHashMap<Integer, Integer> billsBox = new LinkedHashMap<Integer, Integer>() {{
        put(1, 0);
        put(2, 0);
        put(5, 0);
        put(10, 0);
        put(20, 0);
        put(50, 0);
        put(100, 0);
    }};

    public BigBillsStrategy(Atm atm) {
        this.atm = atm;
    }

    private Atm atm;

    @Override
    public void giveMoney(int amount) {
        initBoxes();
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<>(billsBox);
        int change = amount;
        List<Integer> nominalKeys = new ArrayList(billsBox.keySet());
        Collections.reverse(nominalKeys);

        for (Integer nominal : nominalKeys) {
            int billsAtATM = billsBoxCopy.get(nominal);
            int amountRequiredBills = change / nominal;
            boolean isEnoughBillsAtATM = amountRequiredBills <= billsAtATM;
            System.out.println(nominal + ">>>>" + billsAtATM);
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
            atm.updateSlots(billsBoxCopy);
        } else {
            System.err.println("There are no enough bills !");
        }
    }

    public void initBoxes() {
        billsBox.entrySet().forEach(e -> e.setValue(atm.getAmountBills(e.getKey())));
    }
}