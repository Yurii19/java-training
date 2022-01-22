import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    Store theStore;

    public UserInterface(Store aStore) {
        this.theStore = aStore;
    }

    /**
     * @throws if user input value that do not match any of offered command
     */
    void askUser() {
        System.out.println(">> There are available commands : put 100, give 15, cash, stat, strategy. Type 'exit' for end the session ");
        serveNoMoney();
        Scanner sc = new Scanner(System.in);
        try {
            String userRequest = sc.nextLine();
            String[] inputtedData = Parser.getInput(userRequest);
            System.out.println(">> Commands : " + Arrays.toString(inputtedData));
            if (inputtedData[0].equals("put")) {
                servePutDialog(sc, Integer.parseInt(inputtedData[1]));
            }
            if (inputtedData[0].equals("cash")) {
                serveStatDialog(sc);
            }
            if (inputtedData[0].equals("stat")) {
                theStore.printStat();
            }
            if (inputtedData[0].equals("give")) {
                serveGiveDialog(sc, Integer.parseInt(inputtedData[1]));
            }
            if (inputtedData[0].equals("strategy")) {
                serveStrategyDialog(Integer.parseInt(inputtedData[1]));
            }
            if (userRequest.equals("exit")) {
                return;
            }
            askUser();
        } catch (Exception e) {
            System.err.println(e + ": You might have inputted wrong value.");
            askUser();
        }
    }


    private  void serveNoMoney() {
        if (!theStore.checkBills()) {
            System.out.println("There no money at the ATM !");
        }
    }

    /**
     * @param strategyVariant number associated with the specified strategy of giving money
     */
    private  void serveStrategyDialog(int strategyVariant) {
        theStore.changeStrategy(strategyVariant);
    }

    /**
     * @param sc instance of class Scanner
     */
    private void serveGiveDialog(Scanner sc, int amountOfMoney) {
        if (theStore.getStrategy() == 1) {
            theStore.giveMoney(amountOfMoney);
        }
        if (theStore.getStrategy() == 2) {
            theStore.giveMoney2(amountOfMoney);
        }
    }


    /**
     * @param sc instance of class Scanner
     */
    private  void serveStatDialog(Scanner sc) {
        theStore.printCash();
    }


    /**
     * @param sc   instance of class Scanner
     * @param bill nominal of the bill
     */
    private  void servePutDialog(Scanner sc, int bill) {
        if (bill == 1 || bill == 2 || bill == 5 || bill == 10 || bill == 20 || bill == 100) {
            theStore.addBill(bill);
        } else {
            System.out.println(">> Input a valid bill nominal.");
        }
    }
}
