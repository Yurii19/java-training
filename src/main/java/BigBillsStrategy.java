//import hibernate.models.Atm;
//
//import javax.swing.text.html.parser.Entity;
//import java.lang.reflect.Field;
//import java.util.*;
//
//public class BigBillsStrategy extends Strategy {
//
//    // ArrayList<String> operationsLog;
//    private Atm atm;
//    private String[] keys = new String[]{
//            "slot1Uah", "slot2Uah", "slot5Uah", "slot10Uah", "slot20Uah", "slot50Uah", "slot100Uah"
//    };
//    private int[] bills = new int[]{1, 2, 5, 10, 20, 50, 100};
//
//    @Override
//    public LinkedHashMap<Integer, Integer> giveMoney(LinkedHashMap<Integer, Integer> billsBox, int amount) {
//        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<Integer, Integer>(billsBox);
//        int change = amount;
//
//        // String s = this.atm[keys[0]];
//        List<Integer> nominalKeys = new ArrayList(billsBox.keySet());
//        Collections.reverse(nominalKeys);
//        Field[] fields = atm.getClass().getFields();
//       // LinkedHashMap<String,Integer> aList  = entities.
//
//        for (Integer nominal : nominalKeys) {
//            int billsAtATM = billsBoxCopy.get(nominal);
//            int amountRequiredBills = change / nominal;
//            boolean isEnoughBillsAtATM = amountRequiredBills <= billsAtATM;
//            if (billsAtATM == 0) {
//                continue;
//            } else if (isEnoughBillsAtATM) {
//                change = change - nominal * amountRequiredBills;
//                billsBoxCopy.put(nominal, (billsAtATM - amountRequiredBills));
//
//            } else if (!isEnoughBillsAtATM) {
//                change = change - nominal * billsAtATM;
//                billsBoxCopy.put(nominal, 0);
//            }
//        }
//        if (change == 0) {
//            System.out.println(makeGiveReport(billsBox, billsBoxCopy));
//            return billsBoxCopy;
//        } else {
//            System.out.println("There are no enough bills !");
//            return new LinkedHashMap<>();
//        }
//    }
//}
