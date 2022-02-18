package hibernate.utils;

import hibernate.models.Atm;

import java.util.*;

public class BigBillsStrategy extends Strategy {

    private final LinkedHashMap<Integer, Integer> billsBox ;
    private final Atm atm;

    public BigBillsStrategy(Atm atm) {
        this.atm = atm;
        this.billsBox = atm.getSlots();
    }

    @Override
    public void giveMoney(int amount) {
        //initBoxes();
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<>(billsBox);
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
            atm.updateSlots(billsBoxCopy);
        } else {
            System.err.println("There are no enough bills !");
            atm.updateSlots(null);
        }
    }

//    public void initBoxes() {
//        billsBox.entrySet().forEach(e -> e.setValue(atm.getAmountBills(e.getKey())));
//    }
}