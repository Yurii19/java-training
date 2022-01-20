import java.text.SimpleDateFormat;
import java.util.*;

public class Store {

    static Bill oneUah = new Bill(1, 0);
    static Bill twoUah = new Bill(2, 0);
    static Bill fiveUah = new Bill(5, 0);
    static Bill tenUah = new Bill(10, 2);
    static Bill twentyUah = new Bill(20, 0);
    static Bill hundredUah = new Bill(100, 10);
    static Bill[] Bills = new Bill[]{oneUah, twoUah, fiveUah, tenUah, twentyUah, hundredUah};

    static ArrayList<String> operationsLog = new ArrayList<String>();
    static int strategy = 1;

    /**
     * @param entry string which describe operation with bills in the ATM
     */
    private static void addToLog(String entry) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        operationsLog.add(timeStamp + entry);
    }

    /**
     * @param amount - amount of money which user wish to withdraw
     */
    public static void giveMoney2(int amount) {
        Bill[] BillsCopy = new Bill[Bills.length];
        for (int i = 0; i < Bills.length; i++) {
            BillsCopy[i] = new Bill(Bills[i].Nominal, Bills[i].Amount);
        }

        int change = amount;
        boolean stopper = true;
        while (stopper) {
            int copyOfChange = change;
            for (int i = 5; i >= 0; i--) {
                Bill theBill = BillsCopy[i];
                if (change >= theBill.Nominal && theBill.Amount != 0) {
                    change = change - theBill.Nominal;
                    theBill.Amount -= 1;
                }
            }
            if (change == 0) {
                String localReport = makeGiveReport(Bills, BillsCopy);
                System.out.println(localReport);
                Bills = BillsCopy;
                stopper = false;
                addToLog(" Given " + amount + " UAH to a user");
            } else if (copyOfChange == change) {
                stopper = false;
                System.out.println("There are no enough Bills !");
            }
        }
    }

    /**
     * @param amount - amount of money which user wish to withdraw
     */
    public static void giveMoney(int amount) {
        Bill[] BillsCopy = new Bill[Bills.length];
        for (int i = 0; i < Bills.length; i++) {
            BillsCopy[i] = new Bill(Bills[i].Nominal, Bills[i].Amount);
        }
        int change = amount;
        for (int i = 5; i >= 0; i--) {
            Bill theBill = BillsCopy[i];
            int amountOfTheBills = change / theBill.Nominal;
            if (change >= theBill.Nominal && theBill.Amount != 0) {
                if (amountOfTheBills > theBill.Amount) {
                    change = change - theBill.Nominal * theBill.Amount;
                    theBill.Amount = 0;
                } else {
                    change = change - theBill.Nominal * amountOfTheBills;
                    theBill.Amount -= amountOfTheBills;
                }
            } else if (theBill.Amount != 0) {
                change = change - theBill.Nominal * amountOfTheBills;
                theBill.Amount -= amountOfTheBills;
            }
        }
        if (change == 0) {
            String localReport = makeGiveReport(Bills, BillsCopy);
            System.out.println(localReport);
            Bills = BillsCopy;
            addToLog(" Given " + amount + " UAH to a user");
        } else {
            System.out.println("There are no enough Bills !");
        }
    }

    /**
     * @param srcArray array contains information about different ATM bills before operation
     * @param newArray array contains information about different ATM bills after operation
     * @return string the contains information about how much and what nominal where given
     */
    private static String makeGiveReport(Bill[] srcArray, Bill[] newArray) {
        StringBuilder report = new StringBuilder("");
        for (int i = 0; i < srcArray.length; i++) {
            int diff = srcArray[i].Amount - newArray[i].Amount;
            if (diff > 0) {
                String logRow = " " + diff + " bills of " + srcArray[i].Nominal + ";";
                report.append(logRow);
            }
        }
        return "Were given: " + report.toString();
    }

    /**
     * @return the value which show if there is bills in the ATM
     */
    public static boolean checkBills() {
        boolean thereIsMoney = false;
        for (Bill bill : Bills) {
            if (bill.Amount != 0) {
                thereIsMoney = true;
                break;
            }
        }
        return thereIsMoney;
    }

    /**
     * @param nominal number nominal value of the bill
     */
    public static void addBill(int nominal) {
        for (int i = 0; i < 6; i++) {
            if (Bills[i].Nominal == nominal) {
                Bills[i].Amount += 1;
            }
        }
        addToLog(" Was added " + nominal + " UAH to the ATM");
    }

    public static void printStat() {
        if (operationsLog.size() < 1) {
            System.out.println(">> There are no records yet");
            return;
        }
        for (String bill : operationsLog) {
            System.out.println(">> " + bill);
        }
    }

    public static void printCash() {
        for (Bill bill : Bills) {
            System.out.println(">> Bills of nominal " + bill.Nominal + " - " + bill.Amount);
        }
    }

    /**
     * @param strategyVariant number associated with the specified strategy of giving money
     */
    public static void changeStrategy(int strategyVariant) {
        strategy = strategyVariant;
    }

    public static class Bill {
        public int Nominal;
        public int Amount;

        public Bill(int n, int a) {
            this.Nominal = n;
            this.Amount = a;
        }
    }
}