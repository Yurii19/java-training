import java.text.SimpleDateFormat;
import java.util.*;

public class Store {

    ArrayList<String> operationsLog;
    private int strategy = 1;


    LinkedHashMap<Integer, Integer> billsBox;

    public Store(LinkedHashMap<Integer, Integer> bills) {
        this.billsBox = bills;
        this.operationsLog = new ArrayList<String>();
    }

    /**
     * @param entry string which describe operation with bills in the ATM
     */
    private void addToLog(String entry) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        this.operationsLog.add(timeStamp + entry);
    }

    /**
     * @param amount - amount of money which user wish to withdraw
     */
    public void giveMoney2(int amount) {
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<Integer, Integer>(billsBox);
        int change = amount;
        System.out.println("give Money 2");
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
            }//end of for loop
            if (change == 0) {
                System.out.println(makeGiveReport(this.billsBox, billsBoxCopy));
                billsBox = billsBoxCopy;
                stopper = false;
                addToLog(" Were given " + amount + " UAH");
            }
            if (change == copyOfChange) {
                stopper = false;
                System.out.println("There are no enough Bills !");
            }
        }
    }

    /**
     * @param amount - amount of money which user wish to withdraw
     */
    public void giveMoney(int amount) {
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
            billsBox = billsBoxCopy;
            addToLog(" Were given " + amount + " UAH");
        } else {
            System.out.println("There are no enough Bills !");
        }
    }

    /**
     * @param billsBoxBefore array contains information about different ATM bills before operation
     * @param billsBoxAfter  newArray array contains information about different ATM bills after operation
     * @return string the contains information about how much and what nominal where given
     */
    private static String makeGiveReport(LinkedHashMap<Integer, Integer> billsBoxBefore, LinkedHashMap<Integer, Integer> billsBoxAfter) {
        StringBuilder report = new StringBuilder("");
        billsBoxBefore.entrySet().forEach(el -> {
            int diff = el.getValue() - billsBoxAfter.get(el.getKey());
            if (diff > 0) {
                String logRow = " " + diff + " bills of " + el.getKey() + ";";
                report.append(logRow);
            }
        });
        return "Were given: " + report.toString();
    }

    public boolean checkBills() {
        Integer res = billsBox.values().stream().reduce(0, Integer::sum);
        return res > 0;
    }

    /**
     * @param nominal number nominal value of the bill
     */
    public void addBill(int nominal) {
        billsBox.replace(nominal, billsBox.get(nominal) + 1);
        addToLog(" Was added " + nominal + " UAH to the ATM");
    }

    public void printStat() {
        if (operationsLog.size() < 1) {
            System.out.println(">> There are no records yet");
        }
        for (String bill : operationsLog) {
            System.out.println(">> " + bill);
        }
        Integer moneyAmount = billsBox.entrySet().stream().map(entry -> entry.getValue() * entry.getKey()).reduce(0, Integer::sum);
        System.out.println(">> Total amount of cash in the ATM - " + moneyAmount + " UAH");
    }

//    void getCash(){
//        //Integer res = billsBox.values().stream().reduce(0, Integer::sum);
//
//        Integer res = billsBox.entrySet().stream().map(entry -> entry.getValue() * entry.getKey()).reduce(0,Integer::sum);
//
//    }

    public void printCash() {
        billsBox.forEach((key, value) -> System.out.println(">> Bills of nominal " + key + " : " + value));
    }

    /**
     * @param strategyVariant number associated with the specified strategy of giving money
     */
    public void changeStrategy(int strategyVariant) {
        strategy = strategyVariant;
    }

    public int getStrategy() {
        return this.strategy;
    }
}