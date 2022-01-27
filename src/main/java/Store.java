import java.text.SimpleDateFormat;
import java.util.*;

public class Store {

    List<String> operationsLog;
    private int strategy = 1;


    LinkedHashMap<Integer, Integer> billsBox;

    public Store(LinkedHashMap<Integer, Integer> bills) {
        this.billsBox = bills;
        this.operationsLog = new ArrayList<String>();
    }

    public void writeTheLog(){

    }

    /**
     *
     * @param anATM - ATM instance that serve the account
     * @param operationType - type operation which user required
     * @param money amount of money added or got from the account
     */
    private void addToLog(ATM anATM, String operationType, Integer money) {
        String entry = " - "+ anATM.getCurrentAccount().getOWNER_ID()+ " " + operationType +" "+ money+" UAH, " + anATM;
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        this.operationsLog.add(timeStamp + entry);
        WriteToFile.writeToFile(timeStamp + entry);
    }

    /**
     *
     * @param amount amount of money which user wish to withdraw
     * @param theAtm - the instance of ATM object which serve accounts
     */
    public void giveMoney(int amount, ATM theAtm) {
        Account currentAccount = theAtm.getCurrentAccount();
        if (currentAccount.getMoney() <= amount) {
            BigBillsStrategy theStrategy1 = new BigBillsStrategy();
            BalancedStrategy theStrategy2 = new BalancedStrategy();

            LinkedHashMap<Integer, Integer> billsBoxCopy = null;
            if (strategy == 1) {
                billsBoxCopy = theStrategy1.giveMoney(billsBox, amount);
            } else if (strategy == 2) {
                billsBoxCopy = theStrategy2.giveMoney(billsBox, amount);
            }

            if (billsBoxCopy != null) {
                billsBox = billsBoxCopy;
                addToLog(theAtm, "receive", amount);
                currentAccount.takeMoney(amount);
            }
        } else {
            System.out.println(">> There are not enough money !");
        }

    }

    public boolean checkBills() {
        Integer res = billsBox.values().stream().reduce(0, Integer::sum);
        return res > 0;
    }

    /**
     * @param nominal number nominal value of the bill
     */
    public void addBill(int nominal, ATM theAtm) {
        billsBox.replace(nominal, billsBox.get(nominal) + 1);
        addToLog(theAtm, "put", nominal);
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

    public void printCash() {
        billsBox.forEach((key, value) -> System.out.println(">> Bills of nominal " + key + " : " + value));
    }

    /**
     * @param strategyVariant number associated with the specified strategy of giving money
     */
    public void changeStrategy(int strategyVariant) {
        strategy = strategyVariant;
    }
}